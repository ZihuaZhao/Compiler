package Mx_compiler.backend;

import Mx_compiler.IR.*;

import java.util.*;

public class StaticDataProcessor {
    private IRRoot irRoot;

    public StaticDataProcessor(IRRoot irRoot){
        this.irRoot = irRoot;
    }

    public class FuncInfo{
        Set<StaticData> definedStaticData = new HashSet<>();
        Set<StaticData> recursiveDefinedData = new HashSet<>();
        Set<StaticData> recursiveUsedData = new HashSet<>();
        Map<StaticData , VirtualReg> staticDataVregMap = new HashMap<>();
    }

    private Map<IRFunc , FuncInfo> funcInfoMap = new HashMap<>();

    private boolean isStaticLoadStore(IRInstruction inst){
        return (inst instanceof  IRLoad && ((IRLoad) inst).isStatic()) || (inst instanceof IRStore && ((IRStore) inst).isStatic());
    }

    private VirtualReg getStaticDataVreg(Map<StaticData , VirtualReg> staticDataVregMap , StaticData staticData){
        VirtualReg vreg = staticDataVregMap.get(staticData);
        if(vreg == null){
            vreg = new VirtualReg(staticData.getName());
            staticDataVregMap.put(staticData , vreg);
        }
        return vreg;
    }

    public void run(){
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            FuncInfo funcInfo = new FuncInfo();
            funcInfoMap.put(irFunc , funcInfo);
            Map<IRReg , IRReg> renameMap = new HashMap<>();
            for(BasicBlock block : irFunc.getReversePostOrder()){
                for(IRInstruction inst = block.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                    if(isStaticLoadStore(inst)) continue;
                    List<IRReg> usedRegs = inst.getUsedRegs();
                    if(!usedRegs.isEmpty()){
                        renameMap.clear();
                        for(IRReg reg : usedRegs){
                            if(reg instanceof StaticData && !(reg instanceof StaticString)){
                                renameMap.put(reg , getStaticDataVreg(funcInfo.staticDataVregMap , (StaticData) reg));
                            }
                            else{
                                renameMap.put(reg , reg);
                            }
                        }
                        inst.setUsedRegs(renameMap);
                    }
                    IRReg definedReg = inst.getDefinedReg();
                    if(definedReg != null && definedReg instanceof StaticData){
                        VirtualReg vreg = getStaticDataVreg(funcInfo.staticDataVregMap , (StaticData) definedReg);
                        inst.setDefinedReg(vreg);
                        funcInfo.definedStaticData.add((StaticData) definedReg);
                    }
                }
            }
            BasicBlock startBlock = irFunc.getStartBlock();
            IRInstruction firstInst = startBlock.getFirstInst();
            funcInfo.staticDataVregMap.forEach(((staticData, virtualReg) -> firstInst.prependInst(new IRLoad(startBlock , virtualReg , 8 , staticData , staticData instanceof StaticString))));
        }
        for(IRFunc builtFunc : irRoot.getBuiltInFuncs().values()){
            funcInfoMap.put(builtFunc , new FuncInfo());
        }
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            FuncInfo funcInfo = funcInfoMap.get(irFunc);
            funcInfo.recursiveUsedData.addAll(funcInfo.staticDataVregMap.keySet());
            funcInfo.recursiveDefinedData.addAll(funcInfo.definedStaticData);
            for(IRFunc calleeFunc : irFunc.recursiveCalleeSet){
                FuncInfo calleeFuncInfo = funcInfoMap.get(calleeFunc);
                funcInfo.recursiveDefinedData.addAll(calleeFuncInfo.definedStaticData);
                funcInfo.recursiveUsedData.addAll(calleeFuncInfo.staticDataVregMap.keySet());
            }
        }

        for(IRFunc irFunc : irRoot.getFuncs().values()){
            FuncInfo funcInfo = funcInfoMap.get(irFunc);
            Set<StaticData> usedStaticData = funcInfo.staticDataVregMap.keySet();
            if(usedStaticData.isEmpty()) continue;
            for(BasicBlock block : irFunc.getReversePostOrder()){
                for(IRInstruction inst = block.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                    if(!(inst instanceof IRFunctionCall)) continue;
                    IRFunc calleeFunc = ((IRFunctionCall) inst).getIrFunc();
                    FuncInfo calleeFuncInfo = funcInfoMap.get(calleeFunc);
                    for(StaticData staticData : funcInfo.definedStaticData){
                        if(staticData instanceof StaticString) continue;
                        if(calleeFuncInfo.recursiveUsedData.contains(staticData)){
                            inst.prependInst(new IRStore(block , funcInfo.staticDataVregMap.get(staticData) , 8 , staticData));
                        }
                    }
                    if(calleeFuncInfo.recursiveDefinedData.isEmpty()) continue;
                    Set<StaticData> loadStaticDataSet = new HashSet<>();
                    loadStaticDataSet.addAll(calleeFuncInfo.recursiveDefinedData);
                    loadStaticDataSet.retainAll(usedStaticData);
                    for(StaticData staticData : loadStaticDataSet){
                        if(staticData instanceof StaticString){
                            continue;
                        }
                        inst.appendInst(new IRLoad(block , funcInfo.staticDataVregMap.get(staticData) , 8 , staticData , staticData instanceof StaticString));
                    }
                }
            }
        }
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            FuncInfo funcInfo = funcInfoMap.get(irFunc);
            IRReturn retInst = irFunc.getReturnList().get(0);
            for(StaticData staticData : funcInfo.definedStaticData){
                retInst.prependInst(new IRStore(retInst.getBlock() , funcInfo.staticDataVregMap.get(staticData) , 8 , staticData));
            }
        }
    }
}
