package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

public class IRPop extends IRInstruction{
    private PhysicalReg preg;

    public IRPop(BasicBlock curBlock , PhysicalReg preg){
        super(curBlock);
        this.preg = preg;
    }

    public PhysicalReg getPreg(){
        return preg;
    }

    @Override
    public void reloadUsedReg(){
        return;
    }

    @Override
    public IRReg getDefinedReg(){
        return null;
    }

    @Override
    public IRInstruction copyRename(Map<Object , Object> renameMap){
        return null;
    }

    @Override
    public void setDefinedReg(IRReg vreg){
        return;
    }

    @Override
    public void setUsedRegs(Map<IRReg , IRReg> renameMap){
        return;
    }

    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
