package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

public class IRReturn extends IRJumpInst{
    private RegValue regValue;

    public IRReturn(BasicBlock curBlock , RegValue regValue){
        super(curBlock);
        this.regValue = regValue;
        reloadUsedReg();
    }

    public RegValue getRegValue(){
        return regValue;
    }

    @Override
    public void reloadUsedReg(){
        usedRegs.clear();
        usedRegValues.clear();
        if(regValue != null && regValue instanceof IRReg)
            usedRegs.add((IRReg) regValue);
        if(regValue != null)
            usedRegValues.add(regValue);
    }

    @Override
    public void setUsedRegs(Map<IRReg , IRReg> renameMap){
        if(regValue != null && regValue instanceof IRReg)
            regValue = renameMap.get(regValue);
        reloadUsedReg();
    }

    @Override
    public IRReg getDefinedReg(){
        return null;
    }

    @Override
    public void setDefinedReg(IRReg vreg){
        return;
    }

    @Override
    public IRReturn copyRename(Map<Object , Object> renameMap){
        return new IRReturn(
                (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) ,
                (RegValue) renameMap.getOrDefault(regValue , regValue)
        );
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
