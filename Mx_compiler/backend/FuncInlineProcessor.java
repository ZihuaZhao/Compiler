package Mx_compiler.backend;

import Mx_compiler.IR.*;

import java.util.*;

public class FuncInlineProcessor {
    private final int MAX_INLINE_INST = 30;
    private final int MAX_LOW_INLINE_INST = 40;
    private final int MAX_FUNC_INST = 1 << 12;
    private final int MAX_INLINE_DEPTH = 4;

    private IRRoot ir;

    private class FuncInfo {
        int numInst = 0; int numCalled = 0;
        boolean recursiveCall , memFunc = false;
    }

    private Map<IRFunc, FuncInfo> funcInfoMap = new HashMap<>();
    private Map<IRFunc, IRFunc> funcBakUpMap = new HashMap<>();

    public FuncInlineProcessor(IRRoot ir) { this.ir = ir; }

    private void copyRegValue(Map<Object, Object> renameMap, RegValue regValue){
        if (!renameMap.containsKey(regValue)) renameMap.put(regValue, regValue.copy());
    }

    private IRFunc genBakUpFunc(IRFunc func) {
        IRFunc bakFunc = new IRFunc();
        Map<Object, Object> bbRenameMap = new HashMap<>();
        for (BasicBlock bb : func.getReversePostOrder()) {
            bbRenameMap.put(bb, new BasicBlock(bb.getName() , bakFunc));
        }
        for (BasicBlock bb : func.getReversePostOrder()) {
            BasicBlock bakBB = (BasicBlock) bbRenameMap.get(bb);
            for (IRInstruction inst = bb.getFirstInst(); inst != null; inst = inst.getSuccInst()) {
                if (inst instanceof IRJumpInst) {
                    bakBB.addJumpInst((IRJumpInst) inst.copyRename(bbRenameMap));
                } else {
                    bakBB.addInst(inst.copyRename(bbRenameMap));
                }
            }
        }
        bakFunc.setStartBlock((BasicBlock) bbRenameMap.get(func.getStartBlock()));
        bakFunc.setEndBlock((BasicBlock) bbRenameMap.get(func.getEndBlock()));
        bakFunc.setVirtualRegList(func.getVirtualRegList());
        return bakFunc;
    }

    private IRInstruction inlineFunctionCall(IRFunctionCall funcCallInst){
        IRFunc callerFunc = funcCallInst.getBlock().getIrFunc();
        IRFunc calleeFunc = funcBakUpMap.getOrDefault(funcCallInst.getIrFunc(), funcCallInst.getIrFunc());
        List<BasicBlock> reversePostOrder = calleeFunc.getReversePostOrder();

        Map<Object, Object> renameMap = new HashMap<>();
        BasicBlock oldEndBB = calleeFunc.getEndBlock();
        BasicBlock newEndBB = new BasicBlock(oldEndBB.getName() , callerFunc);
        renameMap.put(oldEndBB, newEndBB);
        renameMap.put(calleeFunc.getStartBlock(), funcCallInst.getBlock());
        if (callerFunc.getEndBlock() == funcCallInst.getBlock()) callerFunc.setEndBlock(newEndBB);

        Map<Object, Object> callBBRenameMap = Collections.singletonMap(funcCallInst.getBlock(), newEndBB);
        for (IRInstruction inst = funcCallInst.getSuccInst(); inst != null; inst = inst.getSuccInst()){
            if (inst instanceof  IRJumpInst){
                newEndBB.addJumpInst(((IRJumpInst) inst).copyRename(callBBRenameMap));
            } else {
                newEndBB.addInst(inst.copyRename(callBBRenameMap));
            }
            inst.remove();
        }
        IRInstruction newEndBBFirstInst = newEndBB.getFirstInst();
        for (int i = 0 ; i < funcCallInst.getArgs().size(); ++i){
            VirtualReg oldArgVreg = calleeFunc.getVirtualRegList().get(i);
            VirtualReg newArgVreg = oldArgVreg.copy();
            funcCallInst.prependInst(new IRMove(funcCallInst.getBlock(), newArgVreg, funcCallInst.getArgs().get(i)));
            renameMap.put(oldArgVreg, newArgVreg);
        }
        funcCallInst.remove();

        for (BasicBlock bb : reversePostOrder){
            if (!renameMap.containsKey(bb)){
                renameMap.put(bb, new BasicBlock(bb.getName() , callerFunc));
            }
        }

        for (BasicBlock oldBB : reversePostOrder){
            BasicBlock newBB = (BasicBlock) renameMap.get(oldBB);
            if (oldBB.forNode != null) {
                IRRoot.ForRecord forRec = ir.forRecMap.get(oldBB.forNode);
                if (forRec.condBlock == oldBB) forRec.condBlock = newBB;
                if (forRec.stepBlock== oldBB) forRec.stepBlock = newBB;
                if (forRec.bodyBlock == oldBB) forRec.bodyBlock = newBB;
                if (forRec.nextBlock == oldBB) forRec.nextBlock = newBB;
            }
            for (IRInstruction inst = oldBB.getFirstInst(); inst != null; inst = inst.getSuccInst()){
                for (RegValue usedRegValue : inst.getUsedRegValues()){
                    copyRegValue(renameMap, usedRegValue);
                }
                if (inst.getDefinedReg() != null) {
                    copyRegValue(renameMap, inst.getDefinedReg());
                }
                if (newBB == newEndBB) {
                    if (!(inst instanceof IRReturn)) {
                        newEndBBFirstInst.prependInst(inst.copyRename(renameMap));
                    }
                } else {
                    if (inst instanceof IRJumpInst) {
                        if (!(inst instanceof IRReturn)) {
                            newBB.addJumpInst(((IRJumpInst) inst).copyRename(renameMap));
                        }
                    } else {
                        newBB.addInst(inst.copyRename(renameMap));
                    }
                }
            }
        }
        if (!funcCallInst.getBlock().isJump()){
            funcCallInst.getBlock().addJumpInst(new IRJump(funcCallInst.getBlock(), newEndBB));
        }
        IRReturn returnInst = calleeFunc.getReturnList().get(0);
        if (returnInst.getRegValue() != null)
            newEndBBFirstInst.prependInst(new IRMove(newEndBB, funcCallInst.getVreg(), (RegValue) renameMap.get(returnInst.getRegValue())));

        return newEndBB.getFirstInst();
    }

    public void run(){
        for (IRFunc irFunction : ir.getFuncs().values()) {
            irFunction.setRecursiveCall(irFunction.recursiveCalleeSet.contains(irFunction));
            FuncInfo funcInfo = new FuncInfo();
            funcInfo.recursiveCall = irFunction.isRecursiveCall();
            funcInfo.memFunc = irFunction.isMemFunc();
            funcInfoMap.put(irFunction, funcInfo);
        }

        for (IRFunc irFunction : ir.getFuncs().values()){
            FuncInfo funcInfo = funcInfoMap.get(irFunction);
            for (BasicBlock bb : irFunction.getReversePostOrder()){
                for (IRInstruction inst = bb.getFirstInst(); inst != null; inst = inst.getSuccInst()){
                    funcInfo.numInst ++;
                    if (inst instanceof IRFunctionCall){
                        FuncInfo calleeInfo = funcInfoMap.get(((IRFunctionCall) inst).getIrFunc());
                        if (calleeInfo != null) calleeInfo.numCalled++;
                    }
                }
            }
        }

        List<BasicBlock> reversePostOrder = new ArrayList<>();
        List<String> unCalledFunc = new ArrayList<>();
        boolean changed = true;
        boolean thisFuncChanged;
        while (changed){
            changed = false;
            unCalledFunc.clear();
            for (IRFunc irFunction : ir.getFuncs().values()) {
                FuncInfo funcInfo = funcInfoMap.get(irFunction);
                reversePostOrder.clear();
                reversePostOrder.addAll(irFunction.getReversePostOrder());
                thisFuncChanged = false;
                for (BasicBlock bb : reversePostOrder)
                    for (IRInstruction inst = bb.getFirstInst(), nextInst; inst != null; inst = nextInst){
                        //inst.getNextInst() may be changed later
                        nextInst = inst.getSuccInst();
                        if (!(inst instanceof IRFunctionCall)) continue;

                        FuncInfo calleeInfo = funcInfoMap.get(((IRFunctionCall) inst).getIrFunc());
                        if (calleeInfo == null) continue;
                        if (calleeInfo.recursiveCall) continue;
                        if (calleeInfo.memFunc) continue;
                        if (calleeInfo.numInst > MAX_LOW_INLINE_INST || calleeInfo.numInst + funcInfo.numInst > MAX_FUNC_INST) continue;

                        nextInst = inlineFunctionCall((IRFunctionCall) inst);
                        funcInfo.numInst += calleeInfo.numInst;
                        changed = true;
                        thisFuncChanged = true;

                        calleeInfo.numCalled --;
                        if (calleeInfo.numCalled == 0){
                            unCalledFunc.add(((IRFunctionCall) inst).getIrFunc().getName());
                        }
                    }
                if (thisFuncChanged) {
                    irFunction.doPostOrder();
                }
            }
            for (String funcName : unCalledFunc){
                ir.removeFunc(funcName);
            }
        }
        for (IRFunc irFunction : ir.getFuncs().values()) {
            irFunction.updateCalleeSet();
        }
        ir.updataCalleeSet();

        //TODO inline recursive functions
        reversePostOrder = new ArrayList<>();
        changed = true;
        for (int i = 0; changed && i < MAX_INLINE_DEPTH; ++i) {
            changed = false;

            // bak up self recursive functions
            funcBakUpMap.clear();
            for (IRFunc irFunction : ir.getFuncs().values()) {
                FuncInfo funcInfo = funcInfoMap.get(irFunction);
                if (!funcInfo.recursiveCall) continue;
                funcBakUpMap.put(irFunction, genBakUpFunc(irFunction));
            }

            for (IRFunc irFunction : ir.getFuncs().values()) {
                FuncInfo funcInfo = funcInfoMap.get(irFunction);
                reversePostOrder.clear();
                reversePostOrder.addAll(irFunction.getReversePostOrder());
                thisFuncChanged = false;
                for (BasicBlock bb : reversePostOrder) {
                    for (IRInstruction inst = bb.getFirstInst(), nextInst; inst != null; inst = nextInst) {
                        // inst.getNextInst() may be changed later
                        nextInst = inst.getSuccInst();
                        if (!(inst instanceof IRFunctionCall)) continue;
                        FuncInfo calleeInfo = funcInfoMap.get(((IRFunctionCall) inst).getIrFunc());
                        if (calleeInfo == null) continue; // skip built-in functions
                        if (calleeInfo.memFunc) continue;
                        if (calleeInfo.numInst > MAX_INLINE_INST || calleeInfo.numInst + funcInfo.numInst > MAX_FUNC_INST) continue;

                        nextInst = inlineFunctionCall((IRFunctionCall) inst);
                        int numAddInst = calleeInfo.numInst;
                        funcInfo.numInst += numAddInst;
                        changed = true;
                        thisFuncChanged = true;
                    }
                }
                if (thisFuncChanged) {
                    irFunction.doPostOrder();
                }
            }
        }
        for (IRFunc irFunction : ir.getFuncs().values()) {
            irFunction.updateCalleeSet();
        }
        ir.updataCalleeSet();
    }
}
