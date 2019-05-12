package Mx_compiler.backend;

import Mx_compiler.IR.*;

import java.util.*;

import static Mx_compiler.nasm.NASMRegSet.*;

public class NASMTransform {
    private IRRoot irRoot;

    public NASMTransform(IRRoot irRoot){
        this.irRoot = irRoot;
    }

    public class FuncInfo{
        List<PhysicalReg> usedCallerRegs = new ArrayList<>();
        List<PhysicalReg> usedCalleeRegs = new ArrayList<>();
        Set<PhysicalReg> recursiveUsedRegs = new HashSet<>();
        Map<StackSlot , Integer> stackSlotOffsetMap = new HashMap<>();
        int numExtraArgs = 0;
        int numStackSlot = 0;
    }

    private Map<IRFunc , FuncInfo> funcInfoMap = new HashMap<>();

    public void run(){
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            FuncInfo funcInfo = new FuncInfo();
            for(PhysicalReg preg : irFunc.getUsedPhysicalGeneralRegs()) {
                if (preg.isCalleeSave())
                    funcInfo.usedCalleeRegs.add(preg);
                if (preg.isCallerSave())
                    funcInfo.usedCallerRegs.add(preg);
            }
            funcInfo.usedCalleeRegs.add(rbx);
            funcInfo.usedCalleeRegs.add(rbp);
            funcInfo.numStackSlot = irFunc.getStackSlots().size();
            for(int i = 0 ; i < funcInfo.numStackSlot ; ++i){
                funcInfo.stackSlotOffsetMap.put(irFunc.getStackSlots().get(i) , 8*i);
            }
            if((funcInfo.usedCalleeRegs.size() + funcInfo.numStackSlot) % 2 == 0){
                ++funcInfo.numStackSlot;
            }
            funcInfo.numExtraArgs = irFunc.getVirtualRegList().size() - 6;
            if(funcInfo.numExtraArgs < 0)
                funcInfo.numExtraArgs = 0;
            int extraArgOffset = (funcInfo.usedCalleeRegs.size() + funcInfo.numStackSlot + 1) * 8;
            for(int i = 6 ; i < irFunc.getVirtualRegList().size() ; ++i){
                funcInfo.stackSlotOffsetMap.put(irFunc.getArgStackSlotMap().get(irFunc.getVirtualRegList().get(i)), extraArgOffset);
                extraArgOffset += 8;
            }
            funcInfoMap.put(irFunc , funcInfo);
        }
        for(IRFunc builtInFunc : irRoot.getBuiltInFuncs().values()){
            funcInfoMap.put(builtInFunc , new FuncInfo());
        }
        for(IRFunc irFunc : funcInfoMap.keySet()){
            FuncInfo funcInfo = funcInfoMap.get(irFunc);
            funcInfo.recursiveUsedRegs.addAll(irFunc.getUsedPhysicalGeneralRegs());
            for(IRFunc calleeFunc : irFunc.recursiveCalleeSet){
                funcInfo.recursiveUsedRegs.addAll(calleeFunc.getUsedPhysicalGeneralRegs());
            }
        }
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            FuncInfo funcInfo = funcInfoMap.get(irFunc);
            BasicBlock entryBlock = irFunc.getStartBlock();
            IRInstruction firstInst = entryBlock.getFirstInst();
            for(PhysicalReg preg : funcInfo.usedCalleeRegs){
                firstInst.prependInst(new IRPush(entryBlock , preg));
            }
            if(funcInfo.numStackSlot > 0){
                firstInst.prependInst(new IRBinaryOperation(entryBlock , rsp , IRBinaryOperation.IRBinaryOp.SUB , rsp , new Imm(funcInfo.numStackSlot * 8)));
            }
            firstInst.prependInst(new IRMove(entryBlock , rbp , rsp));
            for(BasicBlock block : irFunc.getReversePostOrder()){
                for(IRInstruction inst = block.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                    if(inst instanceof IRFunctionCall){
                        IRFunc calleeFunc = ((IRFunctionCall) inst).getIrFunc();
                        FuncInfo calleeInfo = funcInfoMap.get(calleeFunc);
                        int numPushCallerSave = 0;
                        for(PhysicalReg preg : funcInfo.usedCallerRegs){
                            if(preg.isArg() && preg.getArgIndex() < irFunc.getVirtualRegList().size()) continue;
                            if(calleeInfo.recursiveUsedRegs.contains(preg)){
                                ++numPushCallerSave;
                                inst.prependInst(new IRPush(inst.getBlock() , preg));
                            }
                        }
                        int numPushArg;
                        if(irFunc.getVirtualRegList().size() <= 6) {
                            numPushArg = irFunc.getVirtualRegList().size();
                        }
                        else numPushArg = 6;
                        for(int i = 0 ; i < numPushArg ; ++i){
                            inst.prependInst(new IRPush(inst.getBlock() , arg6.get(i)));
                        }
                        numPushCallerSave += numPushArg;
                        boolean extraPush = false;
                        List<RegValue> args = ((IRFunctionCall) inst).getArgs();
                        List<Integer> arg6BakOffset = new ArrayList<>();
                        Map<PhysicalReg , Integer> arg6BakOffsetMap = new HashMap<>();
                        if((numPushCallerSave + calleeInfo.numExtraArgs) % 2 == 1){
                            extraPush = true;
                            inst.prependInst(new IRPush(inst.getBlock() , new Imm(0)));
                        }
                        for(int i = args.size() - 1 ; i > 5 ; --i){
                            if(args.get(i) instanceof  StackSlot){
                                inst.prependInst(new IRLoad(inst.getBlock() , rax , 8 , rbp , funcInfo.stackSlotOffsetMap.get(args.get(i))));
                                inst.prependInst(new IRPush(inst.getBlock() , rax));
                            }
                            else{
                                inst.prependInst(new IRPush(inst.getBlock() , args.get(i)));
                            }
                        }
                        int bakOffset = 0;
                        for(int i = 0 ; i < 6 ; ++i){
                            if(args.size() <= i) break;
                            if(args.get(i) instanceof  PhysicalReg && ((PhysicalReg) args.get(i)).isArg() && ((PhysicalReg) args.get(i)).getArgIndex() < args.size()){
                                PhysicalReg preg = (PhysicalReg) args.get(i);
                                if(arg6BakOffsetMap.containsKey(preg)){
                                    arg6BakOffset.add(arg6BakOffsetMap.get(preg));
                                }
                                else{
                                    arg6BakOffset.add(bakOffset);
                                    arg6BakOffsetMap.put(preg , bakOffset);
                                    inst.prependInst(new IRPush(inst.getBlock() , preg));
                                    ++bakOffset;
                                }
                            }
                            else{
                                arg6BakOffset.add(-1);
                            }
                        }
                        for(int i = 0 ; i < 6 ; ++i){
                            if(args.size() <= i) break;
                            if(arg6BakOffset.get(i) == -1){
                                if(args.get(i) instanceof StackSlot){
                                    if(args.get(i) instanceof StackSlot){
                                        inst.prependInst(new IRLoad(inst.getBlock() , rax , 8 , rbp , funcInfo.stackSlotOffsetMap.get(args.get(i))));
                                        inst.prependInst(new IRMove(inst.getBlock() , arg6.get(i) , rax));
                                    }
                                    else{
                                        inst.prependInst(new IRMove(inst.getBlock() , arg6.get(i) , args.get(i)));
                                    }
                                }
                                else{
                                    inst.prependInst(new IRLoad(inst.getBlock() , arg6.get(i) , 8 , rsp , 8 * (bakOffset - arg6BakOffset.get(i) - 1)));
                                }
                            }
                        }
                        if(bakOffset > 0)
                            inst.prependInst(new IRBinaryOperation(inst.getBlock() , rsp , IRBinaryOperation.IRBinaryOp.ADD , rsp , new Imm(bakOffset * 8)));
                        if (((IRFunctionCall) inst).getVreg() != null){
                            inst.appendInst(new IRMove(inst.getBlock() , ((IRFunctionCall) inst).getVreg() , rax));
                        }
                        for (PhysicalReg preg : funcInfo.usedCallerRegs){
                            if (preg.isArg() && preg.getArgIndex() < irFunc.getVirtualRegList().size()) continue;
                            if (calleeInfo.recursiveUsedRegs.contains(preg)) {
                                inst.appendInst(new IRPop(inst.getBlock() , preg));
                            }
                        }
                        for (int i = 0; i < numPushArg; ++i) {
                            inst.appendInst(new IRPop(inst.getBlock() , arg6.get(i)));
                        }
                        if (calleeInfo.numExtraArgs > 0 || extraPush) {
                            int num = extraPush ? calleeInfo.numExtraArgs + 1 : calleeInfo.numExtraArgs;
                            inst.appendInst(new IRBinaryOperation(inst.getBlock() , rsp , IRBinaryOperation.IRBinaryOp.ADD , rsp , new Imm(numPushArg * 8)));
                        }
                    }
                    else if (inst instanceof IRHeapAlloc) {
                        int numPushCallerSave = 0;
                        for (PhysicalReg preg : funcInfo.usedCallerRegs) {
                            ++numPushCallerSave;
                            inst.prependInst(new IRPush(inst.getBlock(), preg));
                        }
                        inst.prependInst(new IRMove(inst.getBlock(), rdi, ((IRHeapAlloc) inst).getAllocSize()));
                        if (numPushCallerSave % 2 == 1) {
                            inst.prependInst(new IRPush(inst.getBlock(), new Imm(0)));
                        }
                        inst.appendInst(new IRMove(inst.getBlock(), ((IRHeapAlloc) inst).getDest(), rax));
                        for (PhysicalReg preg : funcInfo.usedCallerRegs) {
                            inst.appendInst(new IRPop(inst.getBlock(), preg));
                        }
                        if (numPushCallerSave % 2 == 1) {
                            inst.appendInst(new IRBinaryOperation(inst.getBlock(), rsp, IRBinaryOperation.IRBinaryOp.ADD, rsp, new Imm(8)));
                        }
                    }
                    else if (inst instanceof IRLoad) {
                        if (((IRLoad) inst).getAddr() instanceof StackSlot) {
                            ((IRLoad) inst).setOffset(funcInfo.stackSlotOffsetMap.get(((IRLoad) inst).getAddr()));
                            ((IRLoad) inst).setAddr(rbp);
                        }
                    } else if (inst instanceof IRStore) {
                        if (((IRStore) inst).getAddr() instanceof StackSlot) {
                            ((IRStore) inst).setOffset(funcInfo.stackSlotOffsetMap.get(((IRStore) inst).getAddr()));
                            ((IRStore) inst).setAddr(rbp);
                        }
                    } else if (inst instanceof IRMove) {
                        if (((IRMove) inst).getLhs() == ((IRMove) inst).getRhs()) {
                            inst.remove();
                        }
                    }
                }
            }
            IRReturn retInst = irFunc.getReturnList().get(0);
            if(retInst.getRegValue() != null){
                retInst.prependInst(new IRMove(retInst.getBlock() , rax , retInst.getRegValue()));
            }
            BasicBlock endBlock = irFunc.getEndBlock();
            IRInstruction lastInst = endBlock.getLastInst();
            if(funcInfo.numStackSlot > 0){
                lastInst.prependInst(new IRBinaryOperation(entryBlock , rsp , IRBinaryOperation.IRBinaryOp.ADD , rsp , new Imm(funcInfo.numStackSlot * 8)));
            }
            for(int i = funcInfo.usedCalleeRegs.size() - 1 ; i >= 0 ; --i){
                lastInst.prependInst(new IRPop(entryBlock , funcInfo.usedCalleeRegs.get(i)));
            }
        }
    }
}
