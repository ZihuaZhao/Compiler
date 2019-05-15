package Mx_compiler.backend;

import Mx_compiler.IR.*;

import java.util.*;

public class FuncInlineProcessor {
    private int MAX_INLINE_INST = 30;
    private int MAX_LOW_INLINE_INST = 30;
    private int MAX_FUNC_INST = 1 << 12;
    private int MAX_INLINE_DEPTH = 5;

    private IRRoot irRoot;

    private class FuncInfo{
        int numInst = 0;
        int numCalled = 0;
        boolean recursiveCall , memFunc = false;
    }

    private Map<IRFunc , FuncInfo> funcInfoMap = new HashMap<>();
    private Map<IRFunc , IRFunc> funcBakUpMap = new HashMap<>();

    public FuncInlineProcessor(IRRoot irRoot){
        this.irRoot = irRoot;
    }

    public void copyRegValue(Map<Object , Object> renameMap , RegValue regValue){
        if(!renameMap.containsKey(regValue))
            renameMap.put(regValue , regValue.copy());
    }

    private IRInstruction inlineFuncCall(IRFunctionCall functionCall){
        IRFunc callerFunc = functionCall.getBlock().getIrFunc();
        IRFunc calleeFunc = funcBakUpMap.getOrDefault(functionCall.getIrFunc() , functionCall.getIrFunc());
        List<BasicBlock> reversePostOrder = calleeFunc.getReversePostOrder();
        Map<Object , Object> renameMap = new HashMap<>();
        BasicBlock oldEndBlock = calleeFunc.getEndBlock();
        BasicBlock newEndBlock = new BasicBlock(oldEndBlock.getName() , callerFunc);
        renameMap.put(oldEndBlock , newEndBlock);
        renameMap.put(calleeFunc.getStartBlock() , functionCall.getBlock());
        if(callerFunc.getEndBlock() == functionCall.getBlock())
            callerFunc.setEndBlock(newEndBlock);
        Map<Object , Object> callRenameMap = Collections.singletonMap(functionCall.getBlock() , newEndBlock);
        for(IRInstruction inst = functionCall.getSuccInst() ; inst != null ; inst = inst.getSuccInst()){
            if(inst instanceof IRJumpInst)
                newEndBlock.addJumpInst(((IRJumpInst) inst).copyRename(callRenameMap));
            else{
                newEndBlock.addInst(inst.copyRename(callRenameMap));
            }
            inst.remove();
        }
        IRInstruction newEndFirstInst = newEndBlock.getFirstInst();
        for(int i = 0 ; i < functionCall.getArgs().size() ; ++i){
            VirtualReg oldArg = calleeFunc.getVirtualRegList().get(i);
            VirtualReg newArg = oldArg.copy();
            functionCall.prependInst(new IRMove(functionCall.getBlock() , newArg , functionCall.getArgs().get(i)));
            renameMap.put(oldArg , newArg);
        }
        functionCall.remove();
        for(BasicBlock block : reversePostOrder){
            if(!renameMap.containsKey(block)){
                renameMap.put(block , new BasicBlock(block.getName() , callerFunc));
            }
        }
        for(BasicBlock oldBlock : reversePostOrder){
            BasicBlock newBlock = (BasicBlock) renameMap.get(oldBlock);
            if(oldBlock.forNode != null){
                IRRoot.ForRecord forRecord = irRoot.forRecMap.get(oldBlock.forNode);
                if(forRecord.condBlock == oldBlock)
                    forRecord.condBlock = newBlock;
                if(forRecord.stepBlock == oldBlock)
                    forRecord.stepBlock = newBlock;
                if(forRecord.bodyBlock == oldBlock)
                    forRecord.bodyBlock = newBlock;
                if(forRecord.nextBlock == oldBlock)
                    forRecord.nextBlock = newBlock;
            }
            for(IRInstruction inst = oldBlock.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                for(RegValue usedRegValue : inst.getUsedRegValues()){
                    copyRegValue(renameMap , usedRegValue);
                }
                if(inst.getDefinedReg() != null)
                    copyRegValue(renameMap , inst.getDefinedReg());
                if(newBlock == newEndBlock){
                    if(!(inst instanceof IRReturn)){
                        newEndFirstInst.prependInst(inst.copyRename(renameMap));
                    }
                }
                else{
                    if(inst instanceof IRJumpInst){
                        if(!(inst instanceof IRReturn)){
                            newBlock.addJumpInst(((IRJumpInst) inst).copyRename(renameMap));
                        }
                    }
                    else{
                        newBlock.addInst(inst.copyRename(renameMap));
                    }
                }
            }
        }
        if(!functionCall.getBlock().isJump()){
            functionCall.getBlock().addJumpInst(new IRJump(functionCall.getBlock() , newEndBlock));
        }
        IRReturn irReturn = calleeFunc.getReturnList().get(0);
        if(irReturn.getRegValue() != null){
            newEndFirstInst.prependInst(new IRMove(newEndBlock , functionCall.getVreg() , (RegValue) renameMap.get(irReturn.getRegValue())));
        }
        return newEndBlock.getFirstInst();
    }

    private IRFunc genBakUpFunc(IRFunc irFunc){
        IRFunc bakFunc = new IRFunc();
        Map<Object , Object> blockRenameMap = new HashMap<>();
        for(BasicBlock block : irFunc.getReversePostOrder()){
            blockRenameMap.put(block , new BasicBlock(block.getName() , bakFunc));
        }
        for(BasicBlock block : irFunc.getReversePostOrder()){
            BasicBlock bakBlock = (BasicBlock)blockRenameMap.get(block);
            for(IRInstruction inst = block.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                if(inst instanceof IRJumpInst){
                    bakBlock.addJumpInst((IRJumpInst) inst.copyRename(blockRenameMap));
                }
                else{
                    bakBlock.addInst(inst.copyRename(blockRenameMap));
                }
            }
        }
        bakFunc.setStartBlock((BasicBlock) blockRenameMap.get(irFunc.getStartBlock()));
        bakFunc.setEndBlock((BasicBlock) blockRenameMap.get(irFunc.getEndBlock()));
        bakFunc.setVirtualRegList(irFunc.getVirtualRegList());
        return bakFunc;
    }

    public void run(){
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            irFunc.setRecursiveCall(irFunc.recursiveCalleeSet.contains(irFunc));
            FuncInfo funcInfo = new FuncInfo();
            funcInfo.recursiveCall = irFunc.isRecursiveCall();
            funcInfo.memFunc = irFunc.isMemFunc();
            funcInfoMap.put(irFunc , funcInfo);
        }
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            FuncInfo funcInfo = funcInfoMap.get(irFunc);
            for(BasicBlock block : irFunc.getReversePostOrder()){
                for(IRInstruction inst = block.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                    ++funcInfo.numInst;
                    if(inst instanceof IRFunctionCall){
                        FuncInfo calleeInfo = funcInfoMap.get(((IRFunctionCall) inst).getIrFunc());
                        if(calleeInfo != null)
                            ++calleeInfo.numCalled;
                    }
                }
            }
        }
        List<BasicBlock> reversePostOrder = new ArrayList<>();
        List<String> unCalledFuncs = new ArrayList<>();
        boolean changed = true , thisFuncChanged;
        while(changed){
            changed = false;
            unCalledFuncs.clear();
            for(IRFunc irFunc : irRoot.getFuncs().values()){
                FuncInfo funcInfo = funcInfoMap.get(irFunc);
                reversePostOrder.clear();
                reversePostOrder.addAll(irFunc.getReversePostOrder());
                thisFuncChanged = false;
                for(BasicBlock block : reversePostOrder){
                    for(IRInstruction inst = block.getFirstInst() , nextInst ; inst != null ; inst = nextInst){
                        nextInst = inst.getSuccInst();
                        if(!(inst instanceof IRFunctionCall))
                            continue;
                        FuncInfo calleeInfo = funcInfoMap.get(((IRFunctionCall) inst).getIrFunc());
                        if(calleeInfo == null)
                            continue;
                        if(calleeInfo.recursiveCall)
                            continue;
                        if(calleeInfo.memFunc)
                            continue;
                        if(calleeInfo.numInst > MAX_LOW_INLINE_INST || calleeInfo.numInst + funcInfo.numInst > MAX_FUNC_INST)
                            continue;
                        nextInst = inlineFuncCall((IRFunctionCall) inst);
                        funcInfo.numInst += calleeInfo.numInst;
                        changed = true;
                        thisFuncChanged = true;
                        --calleeInfo.numCalled;
                        if(calleeInfo.numCalled == 0){
                            unCalledFuncs.add(((IRFunctionCall) inst).getIrFunc().getName());
                        }
                    }
                }
                if(thisFuncChanged){
                    irFunc.doPostOrder();
                }
            }
            for(String funcName : unCalledFuncs){
                irRoot.removeFunc(funcName);
            }
        }
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            irFunc.updateCalleeSet();
        }
        irRoot.updataCalleeSet();
        reversePostOrder = new ArrayList<>();
        changed = true;
        for(int i = 0 ; changed && i < MAX_INLINE_DEPTH ; ++i){
            changed = false;
            funcBakUpMap.clear();
            for(IRFunc irFunc : irRoot.getFuncs().values()){
                FuncInfo funcInfo = funcInfoMap.get(irFunc);
                if(!funcInfo.recursiveCall)
                    continue;
                funcBakUpMap.put(irFunc , genBakUpFunc(irFunc));
            }
            for(IRFunc irFunc : irRoot.getFuncs().values()){
                FuncInfo funcInfo = funcInfoMap.get(irFunc);
                reversePostOrder.clear();
                reversePostOrder.addAll(irFunc.getReversePostOrder());
                thisFuncChanged = false;
                for(BasicBlock block : reversePostOrder){
                    for(IRInstruction inst = block.getFirstInst() , succInst ; inst != null ; inst = succInst){
                        succInst = inst.getSuccInst();
                        if(!(inst instanceof IRFunctionCall))
                            continue;
                        FuncInfo calleeInfo = funcInfoMap.get(((IRFunctionCall) inst).getIrFunc());
                        if(calleeInfo == null)
                            continue;
                        if(calleeInfo.memFunc)
                            continue;
                        if(calleeInfo.numInst > MAX_INLINE_INST || calleeInfo.numInst + funcInfo.numInst > MAX_FUNC_INST)
                            continue;
                        succInst = inlineFuncCall((IRFunctionCall) inst);
                        int numAddInst = calleeInfo.numInst;
                        funcInfo.numInst += numAddInst;
                        changed = true;
                        thisFuncChanged = true;
                    }
                }
                if(thisFuncChanged){
                    irFunc.doPostOrder();
                }
            }
        }
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            irFunc.updateCalleeSet();
        }
        irRoot.updataCalleeSet();
    }
}
