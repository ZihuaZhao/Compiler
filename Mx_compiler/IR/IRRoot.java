package Mx_compiler.IR;

import Mx_compiler.Scope.Scope;
import Mx_compiler.nasm.NASMReg;
import Mx_compiler.nasm.NASMRegSet;
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

    private void insertBuiltInFuncs(){
        IRFunc func;

        func = new IRFunc(BUILTIN_STRING_CONCAT_FUNC_NAME, "__builtin_string_concat");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_STRING_EQUAL_FUNC_NAME, "__builtin_string_equal");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_STRING_INEQUAL_FUNC_NAME, "__builtin_string_inequal");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_STRING_LESS_FUNC_NAME, "__builtin_string_less");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_STRING_LESS_EQUAL_FUNC_NAME, "__builtin_string_less_equal");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_PRINT_FUNC_NAME, "_Z5printPc");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_PRINTLN_FUNC_NAME, "_Z7printlnPc");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_PRINT_INT_FUNC_NAME, "_Z8printInti");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_PRINTLN_INT_FUNC_NAME, "_Z10printlnInti");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_GET_STRING_FUNC_NAME, "_Z9getStringv");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_GET_INT_FUNC_NAME, "_Z6getIntv");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_TO_STRING_FUNC_NAME, "_Z8toStringi");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_STRING_SUBSTRING_FUNC_NAME, "_Z27__member___string_substringPcii");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_STRING_PARSEINT_FUNC_NAME, "_Z26__member___string_parseIntPc");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);

        func = new IRFunc(BUILTIN_STRING_ORD_FUNC_NAME, "_Z21__member___string_ordPci");
        func.getUsedPhysicalGeneralRegs().addAll(NASMRegSet.generalRegs);
        addBuiltInFunc(func);
    }

    public IRRoot(){
        insertBuiltInFuncs();
    }

    public PhysicalReg getPreg0(){
        return preg0;
    }

    public PhysicalReg getPreg1(){
        return preg1;
    }

    public void addBuiltInFunc(IRFunc builtInFunc){
        builtInFuncs.put(builtInFunc.getName() , builtInFunc);
    }

    public IRFunc getBuiltInFunc(String name){
        return builtInFuncs.get(name);
    }

    public Map<String , IRFunc> getBuiltInFuncs(){
        return builtInFuncs;
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

    public void removeFunc(String funcName){
        funcs.remove(funcName);
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

    static public final String BUILTIN_STRING_CONCAT_FUNC_NAME = "__builtin_string_concat";
    static public final String BUILTIN_STRING_EQUAL_FUNC_NAME = "__builtin_string_equal";
    static public final String BUILTIN_STRING_INEQUAL_FUNC_NAME = "__builtin_string_inequal";
    static public final String BUILTIN_STRING_LESS_FUNC_NAME = "__builtin_string_less";
    static public final String BUILTIN_STRING_LESS_EQUAL_FUNC_NAME = "__builtin_string_less_equal";

    static public final String BUILTIN_PRINT_FUNC_NAME = "print";
    static public final String BUILTIN_PRINTLN_FUNC_NAME = "println";
    static public final String BUILTIN_PRINT_INT_FUNC_NAME = "printInt";
    static public final String BUILTIN_PRINTLN_INT_FUNC_NAME = "printlnInt";
    static public final String BUILTIN_GET_STRING_FUNC_NAME = "getString";
    static public final String BUILTIN_GET_INT_FUNC_NAME = "getInt";
    static public final String BUILTIN_TO_STRING_FUNC_NAME = "toString";
    static public final String BUILTIN_STRING_LENGTH_FUNC_NAME = "__member_" + Scope.STRING_CLASS_NAME + "_" + "length";
    static public final String BUILTIN_STRING_SUBSTRING_FUNC_NAME = "__member_" + Scope.STRING_CLASS_NAME + "_" + "substring";
    static public final String BUILTIN_STRING_PARSEINT_FUNC_NAME = "__member_" + Scope.STRING_CLASS_NAME + "_" + "parseInt";
    static public final String BUILTIN_STRING_ORD_FUNC_NAME = "__member_" +  Scope.STRING_CLASS_NAME+ "_" + "ord";
    static public final String BUILTIN_ARRAY_SIZE_FUNC_NAME = "__member_" + Scope.ARRAY_CLASS_NAME + "_" + "size";


}
