package Mx_compiler.backend;

import Mx_compiler.IR.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        }
    }
}
