package Mx_compiler.backend;

import Mx_compiler.IR.*;

import java.util.*;

public class RegLiveliness {
    private IRRoot irRoot;
    private boolean eliminationChanged;

    public RegLiveliness(IRRoot irRoot){
        this.irRoot = irRoot;
    }

    private void livelinessCheck(IRFunc irFunc){
        List<BasicBlock> reversePreOrder = irFunc.getReversePreOrder();
        for(BasicBlock block : reversePreOrder){
            for(IRInstruction inst = block.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                if(inst.liveIn == null)
                    inst.liveIn = new HashSet<>();
                else
                    inst.liveIn.clear();
                if(inst.liveOut == null)
                    inst.liveOut = new HashSet<>();
                else
                    inst.liveOut.clear();
            }
        }
        Set<VirtualReg> liveIn = new HashSet<>();
        Set<VirtualReg> liveOut = new HashSet<>();
        boolean converged = false;
        while(!converged){
            converged = true;
            for(BasicBlock block : reversePreOrder){
                for(IRInstruction inst = block.getLastInst() ; inst != null ; inst = inst.getPrevInst()){
                    liveIn.clear();
                    liveOut.clear();
                    if(inst instanceof IRJumpInst){
                        if(inst instanceof IRJump){
                            liveOut.addAll(((IRJump) inst).getTarBlock().getFirstInst().liveIn);
                        }
                        else if(inst instanceof IRBranch){
                            liveOut.addAll(((IRBranch) inst).getThenBlock().getFirstInst().liveIn);
                            liveOut.addAll(((IRBranch) inst).getElseBlock().getFirstInst().liveIn);
                        }
                    }
                    else{
                        if(inst.getSuccInst() != null){
                            liveOut.addAll(inst.getSuccInst().liveIn);
                        }
                    }
                    liveIn.addAll(liveOut);
                    IRReg definedReg = inst.getDefinedReg();
                    if(definedReg instanceof VirtualReg){
                        liveIn.remove(definedReg);
                    }
                    for(IRReg usedReg : inst.getUsedRegs()){
                        if(usedReg instanceof VirtualReg){
                            liveIn.add((VirtualReg) usedReg);
                        }
                    }
                    if(!inst.liveIn.equals(liveIn)){
                        converged = false;
                        inst.liveIn.clear();
                        inst.liveIn.addAll(liveIn);
                    }
                    if(!inst.liveOut.equals(liveOut)){
                        converged = false;
                        inst.liveOut.clear();
                        inst.liveOut.addAll(liveOut);
                    }
                }
            }
        }
    }

    void tryEliminate(IRFunc irFunc){
        List<BasicBlock> reversePreOrder = irFunc.getReversePreOrder();
        for(BasicBlock block : reversePreOrder){
            for(IRInstruction inst = block.getLastInst() , prevInst ; inst != null ; inst = prevInst){
                prevInst = inst.getPrevInst();
                if(inst instanceof IRBinaryOperation || inst instanceof IRCmpOperation || inst instanceof IRLoad || inst instanceof IRMove || inst instanceof IRUnaryOperation || inst instanceof IRHeapAlloc){
                    IRReg dest = inst.getDefinedReg();
                    if(dest == null || !inst.liveOut.contains(dest)){
                        eliminationChanged = true;
                        inst.remove();
                    }
                }
            }
        }
        for(IRRoot.ForRecord forRecord:irRoot.forRecMap.values()){
            if(forRecord.processed) continue;
            boolean isFieldOutside = false;
            if(forRecord.condBlock == null || forRecord.stepBlock == null || forRecord.bodyBlock == null || forRecord.nextBlock == null) continue;
            List<BasicBlock> blockList = new ArrayList<>();
            blockList.add(forRecord.condBlock);
            blockList.add(forRecord.stepBlock);
            blockList.add(forRecord.bodyBlock);
            blockList.add(forRecord.nextBlock);
            IRInstruction nextFirstInst = forRecord.nextBlock.getFirstInst();
            for(int i = 0 ; i < 3 ; ++i){
                for(IRInstruction inst = blockList.get(i).getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                    if(inst instanceof IRFunctionCall){
                        isFieldOutside = true;
                        continue;
                    }
                    if(inst.getDefinedReg() != null){
                        if(nextFirstInst.liveIn.contains(inst.getDefinedReg())){
                            isFieldOutside = true;
                        }
                        continue;
                    }
                    if(inst instanceof IRStore){
                        isFieldOutside = true;
                        continue;
                    }
                    if(inst instanceof IRJump){
                        if(!blockList.contains(((IRJump) inst).getTarBlock()))
                            isFieldOutside = true;
                        continue;
                    }
                    if(inst instanceof IRBranch){
                        if(!blockList.contains(((IRBranch) inst).getThenBlock()) || !blockList.contains(((IRBranch) inst).getElseBlock()))
                            isFieldOutside = true;
                        continue;
                    }
                    if(inst instanceof IRReturn){
                        //TODO
                        isFieldOutside = true;
                        continue;
                    }
                }
            }
            if(!isFieldOutside){
                forRecord.condBlock.reInit();
                forRecord.condBlock.addJumpInst(new IRJump(forRecord.condBlock , forRecord.nextBlock));
                forRecord.processed = true;
            }
        }
    }

    private Map<BasicBlock , BasicBlock> jumpTarBlockMap = new HashMap<>();

    BasicBlock replaceJumpTar(BasicBlock block){
        BasicBlock ret = block;
        BasicBlock query = jumpTarBlockMap.get(block);
        while(query != null){
            ret = query;
            query = jumpTarBlockMap.get(query);
        }
        return ret;
    }

    void removeBlankBlock(IRFunc irFunc){
        jumpTarBlockMap.clear();
        for(BasicBlock block : irFunc.getReversePostOrder()){
            if(block.getFirstInst() == block.getLastInst()){
                IRInstruction inst = block.getFirstInst();
                if(inst instanceof IRJump){
                    jumpTarBlockMap.put(block , ((IRJump) inst).getTarBlock());
                }
            }
        }
        for(BasicBlock block : irFunc.getReversePostOrder()){
            if(block.getLastInst() instanceof IRJump){
                IRJump jumpInst = (IRJump) block.getLastInst();
                jumpInst.setTarBlock(replaceJumpTar(jumpInst.getTarBlock()));
            }
            else if(block.getLastInst() instanceof IRBranch){
                IRBranch branchInst = (IRBranch) block.getLastInst();
                branchInst.setThenBlock(replaceJumpTar(branchInst.getThenBlock()));
                branchInst.setElseBlock(replaceJumpTar(branchInst.getElseBlock()));
                if(branchInst.getThenBlock() == branchInst.getElseBlock()){
                    branchInst.replace(new IRJump(block , branchInst.getThenBlock()));
                }
            }
        }
    }

    public void run(){
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            livelinessCheck(irFunc);
        }
        eliminationChanged = true;
        while(eliminationChanged){
            eliminationChanged = false;
            for(IRFunc irFunc : irRoot.getFuncs().values()){
                if(irFunc.isBuiltIn()) continue;
                tryEliminate(irFunc);
                removeBlankBlock(irFunc);
                livelinessCheck(irFunc);
            }
        }
    }
}
