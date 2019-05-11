package Mx_compiler.IR;

import Mx_compiler.node.StmtNode;

import java.util.*;

public class IRRoot {
    private Map<String , IRFunc> funcs = new HashMap<>();
    private Map<String , IRFunc> builtInFuncs = new HashMap<>();
    private List<StaticData> staticDataList = new ArrayList<>();
    private Map<String , StaticString> staticStringMap = new HashMap<>();
    private PhysicalReg preg0 , preg1;
    private int maxNumFuncArgs = 3;
    private boolean hasDivShiftInst = false;

    public IRRoot(){
        //TODO
    }

    public PhysicalReg getPreg0(){
        return preg0;
    }

    public PhysicalReg getPreg1(){
        return preg1;
    }

    public void setPreg0(PhysicalReg preg0){
        this.preg0 = preg0;
    }

    public void setPreg1(PhysicalReg preg1){
        this.preg1 = preg1;
    }

    public void addFunc(IRFunc irFunc){
        funcs.put(irFunc.getName() , irFunc);
    }

    public IRFunc getIRFunc(String funcName){
        return funcs.get(funcName);
    }

    public void addStaticData(StaticData staticData){
        staticDataList.add(staticData);
    }

    public void addStaticStrings(String name , StaticString staticString){
        staticStringMap.put(name , staticString);
    }

    public StaticString getStaticString(String name){
        return staticStringMap.get(name);
    }

    public Map<String , StaticString> getStaticStringMap(){
        return staticStringMap;
    }

    public Map<String , IRFunc> getFuncs(){
        return funcs;
    }

    public List<StaticData> getStaticDataList(){
        return staticDataList;
    }

    public int getMaxNumFuncArgs(){
        return maxNumFuncArgs;
    }

    public void setMaxNumFuncArgs(int maxNumFuncArgs){
        this.maxNumFuncArgs = maxNumFuncArgs;
    }

    public boolean isHasDivShiftInst(){
        return hasDivShiftInst;
    }

    public void setHasDivShiftInst(boolean hasDivShiftInst){
        this.hasDivShiftInst = hasDivShiftInst;
    }

    public static class ForRecord{
        public BasicBlock condBlock , stepBlock , bodyBlock , nextBlock;
        public boolean processed = false;

        public ForRecord(BasicBlock condBlock , BasicBlock stepBlock , BasicBlock bodyBlock , BasicBlock nextBlock){
            this.condBlock = condBlock;
            this.stepBlock = stepBlock;
            this.bodyBlock = bodyBlock;
            this.nextBlock = nextBlock;
        }
    }
    public Map<StmtNode, ForRecord> forRecMap = new HashMap<>();

    public void updataCalleeSet(){
        Set<IRFunc> recursiveCalleeSet = new HashSet<>();
        for(IRFunc irFunc : funcs.values()){
            irFunc.recursiveCalleeSet.clear();
        }
        boolean changed = true;
        while(changed){
            changed = false;
            for(IRFunc irFunc : funcs.values()){
                recursiveCalleeSet.clear();
                recursiveCalleeSet.addAll(irFunc.calleeSet);
                for(IRFunc calleeFunc : irFunc.calleeSet){
                    recursiveCalleeSet.addAll(calleeFunc.recursiveCalleeSet);
                }
                if(!recursiveCalleeSet.equals(irFunc.recursiveCalleeSet)){
                    irFunc.recursiveCalleeSet.clear();
                    irFunc.recursiveCalleeSet.addAll(recursiveCalleeSet);
                    changed = true;
                }
            }
        }
    }
}
