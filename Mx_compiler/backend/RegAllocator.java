package Mx_compiler.backend;

import Mx_compiler.IR.*;
import Mx_compiler.nasm.NASMReg;
import Mx_compiler.nasm.NASMRegSet;
import org.antlr.v4.runtime.atn.ContextSensitivityInfo;

import java.util.*;

public class RegAllocator {
    private IRRoot irRoot;
    private List<PhysicalReg> physicalRegs;
    private PhysicalReg preg0 , preg1;
    private int numColors;

    public RegAllocator(IRRoot irRoot){
        this.irRoot = irRoot;
        this.physicalRegs = new ArrayList<>(NASMRegSet.generalRegs);
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            if(irFunc.getVirtualRegList().size() > irRoot.getMaxNumFuncArgs())
                irRoot.setMaxNumFuncArgs(irFunc.getVirtualRegList().size());
        }
        if(irRoot.getMaxNumFuncArgs() >= 5)
            physicalRegs.remove(NASMRegSet.r8);
        if(irRoot.getMaxNumFuncArgs() >= 6)
            physicalRegs.remove(NASMRegSet.r9);
        if(irRoot.isHasDivShiftInst()){
            preg0 = physicalRegs.get(0);
            preg1 = physicalRegs.get(1);
        }
        else{
            preg0 = NASMRegSet.rbx;
            preg1 = physicalRegs.get(0);
        }
        irRoot.setPreg0(preg0);
        irRoot.setPreg1(preg1);
        this.physicalRegs.remove(preg0);
        this.physicalRegs.remove(preg1);
        numColors = this.physicalRegs.size();
    }

    private Map<VirtualReg , VirtualRegInfo> vregInfoMap = new HashMap<>();
    private List<VirtualReg> vregOrder = new ArrayList<>();
    private Set<PhysicalReg> usedColors = new HashSet<>();
    private Set<VirtualReg> vregNodes = new HashSet<>();
    private Set<VirtualReg> degreeSmallVregNodes = new HashSet<>();
    private Map<IRReg , IRReg> renameMap = new HashMap<>();

    private class VirtualRegInfo{
        Set<VirtualReg> neighbours = new HashSet<>();
        boolean removed = false;
        IRReg color = null;
        int degree = 0;
        Set<VirtualReg> suggestSameRegs = new HashSet<>();
    }

    private VirtualRegInfo getVregInfo(VirtualReg vreg){
        VirtualRegInfo vregInfo = vregInfoMap.get(vreg);
        if(vregInfo == null){
            vregInfo = new VirtualRegInfo();
            vregInfoMap.put(vreg , vregInfo);
        }
        return vregInfo;
    }

    private void addEdge(VirtualReg a , VirtualReg b){
        getVregInfo(a).neighbours.add(b);
        getVregInfo(b).neighbours.add(a);
    }

    private void removeVregNode(VirtualReg vreg){
        VirtualRegInfo virtualRegInfo = vregInfoMap.get(vreg);
        VirtualRegInfo neighbourInfo;
        virtualRegInfo.removed = true;
        vregNodes.remove(vreg);
        for(VirtualReg neighbour : virtualRegInfo.neighbours){
            neighbourInfo = vregInfoMap.get(neighbour);
            if(neighbourInfo.removed)
                continue;
            --neighbourInfo.degree;
            if(neighbourInfo.degree <numColors)
                degreeSmallVregNodes.add(neighbour);
        }
    }

    private void updateInst(IRFunc irFunc){
        for(BasicBlock block : irFunc.getReversePostOrder()){
            for(IRInstruction inst = block.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                if(inst instanceof IRFunctionCall){
                    List<RegValue> args = ((IRFunctionCall) inst).getArgs();
                    for(int i = 0 ; i < args.size() ; ++i){
                        if(args.get(i) instanceof VirtualReg){
                            args.set(i , vregInfoMap.get(args.get(i)).color);
                        }
                    }
                }
                else{
                    Collection<IRReg> usedRegs = inst.getUsedRegs();
                    if(!usedRegs.isEmpty()){
                        boolean usedPreg0 = false;
                        renameMap.clear();
                        for(IRReg irReg : usedRegs){
                            if(irReg instanceof VirtualReg){
                                IRReg color = vregInfoMap.get(irReg).color;
                                if(color instanceof StackSlot){
                                    PhysicalReg preg;
                                    if(usedPreg0)
                                        preg = preg1;
                                    else{
                                        preg = preg0;
                                        usedPreg0 = true;
                                    }
                                    inst.prependInst(new IRLoad(block , preg , 8 , color , 0));
                                    renameMap.put(irReg , preg);
                                    irFunc.getUsedPhysicalGeneralRegs().add(preg);
                                }
                                else{
                                    renameMap.put(irReg , color);
                                    irFunc.getUsedPhysicalGeneralRegs().add((PhysicalReg) color);
                                }
                            }
                            else{
                                renameMap.put(irReg , irReg);
                            }
                        }
                        inst.setUsedRegs(renameMap);
                    }
                }
                IRReg definedReg = inst.getDefinedReg();
                if(definedReg instanceof VirtualReg){
                    IRReg color = vregInfoMap.get(definedReg).color;
                    if(color instanceof StackSlot){
                        inst.setDefinedReg(preg0);
                        inst.appendInst(new IRStore(block , preg0 , 8 , color , 0));
                        irFunc.getUsedPhysicalGeneralRegs().add(preg0);
                        inst = inst.getSuccInst();
                    }
                    else{
                        inst.setDefinedReg(color);
                        irFunc.getUsedPhysicalGeneralRegs().add((PhysicalReg) color);
                    }
                }
            }
        }
    }

    public void run(){
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            vregInfoMap.clear();
            vregNodes.clear();
            degreeSmallVregNodes.clear();
            for(VirtualReg arg : irFunc.getVirtualRegList()) {
                getVregInfo(arg);
            }
            for(BasicBlock block : irFunc.getReversePostOrder()){
                for(IRInstruction inst = block.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                    IRReg definedReg = inst.getDefinedReg();
                    if(!(definedReg instanceof VirtualReg))
                        continue;
                    VirtualRegInfo vregInfo = getVregInfo((VirtualReg) definedReg);
                    if(inst instanceof IRMove){
                        RegValue rhs = ((IRMove) inst).getRhs();
                        if(rhs instanceof VirtualReg){
                            vregInfo.suggestSameRegs.add((VirtualReg) rhs);
                            getVregInfo((VirtualReg) rhs).suggestSameRegs.add((VirtualReg) definedReg);
                        }
                        for(VirtualReg vreg : inst.liveOut){
                            if(vreg != rhs && vreg != definedReg)
                                addEdge(vreg , (VirtualReg) definedReg);
                        }
                    }
                    else{
                        for(VirtualReg vreg : inst.liveOut){
                            if(vreg != definedReg)
                                addEdge(vreg , (VirtualReg) definedReg);
                        }
                    }
                }
            }
            for(VirtualRegInfo vregInfo : vregInfoMap.values()){
                vregInfo.degree = vregInfo.neighbours.size();
            }
            vregNodes.addAll(vregInfoMap.keySet());
            for(VirtualReg vreg : vregNodes){
                if(vregInfoMap.get(vreg).degree < numColors)
                    degreeSmallVregNodes.add(vreg);
            }
            vregOrder.clear();
            while(!vregNodes.isEmpty()){
                while(!degreeSmallVregNodes.isEmpty()){
                    Iterator<VirtualReg> iterator = degreeSmallVregNodes.iterator();
                    VirtualReg vreg = iterator.next();
                    iterator.remove();
                    removeVregNode(vreg);
                    vregOrder.add(vreg);
                }
                if(vregNodes.isEmpty()) break;
                Iterator<VirtualReg> iterator = vregNodes.iterator();
                VirtualReg vreg = iterator.next();
                iterator.remove();
                removeVregNode(vreg);
                vregOrder.add(vreg);
            }
            Collections.reverse(vregOrder);
            for(VirtualReg vreg : vregOrder){
                VirtualRegInfo vregInfo = vregInfoMap.get(vreg);
                vregInfo.removed = false;
                usedColors.clear();
                for(VirtualReg neighbour : vregInfo.neighbours){
                    VirtualRegInfo neighbourInfo = vregInfoMap.get(neighbour);
                    if(!neighbourInfo.removed && neighbourInfo.color instanceof PhysicalReg){
                        usedColors.add((PhysicalReg) neighbourInfo.color);
                    }
                }
                PhysicalReg forcedPhysicalReg = vreg.getPhysicalReg();
                if(forcedPhysicalReg != null){
                    if(usedColors.contains(forcedPhysicalReg)){
                        throw new Error("forced physical reg used");
                    }
                    vregInfo.color = forcedPhysicalReg;
                }
                else{
                    for(VirtualReg suggestSameReg : vregInfo.suggestSameRegs){
                        IRReg color = getVregInfo(suggestSameReg).color;
                        if(color instanceof PhysicalReg && !usedColors.contains(color)){
                            vregInfo.color = color;
                            break;
                        }
                    }
                    if(vregInfo.color == null){
                        for(PhysicalReg physicalReg : physicalRegs){
                            if(!usedColors.contains(physicalReg)){
                                vregInfo.color = physicalReg;
                                break;
                            }
                        }
                        if(vregInfo.color == null){
                            vregInfo.color = irFunc.getArgStackSlotMap().get(vreg);
                            if(vregInfo.color == null)
                                vregInfo.color = new StackSlot(irFunc , vreg.getName() , false);
                        }
                    }
                }
            }
            updateInst(irFunc);
        }
    }
}
