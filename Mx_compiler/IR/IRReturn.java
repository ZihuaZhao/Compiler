package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class IRReturn extends IRJumpInst{
    private RegValue regValue;

    public IRReturn(BasicBlock curBlock , RegValue regValue){
        super(curBlock);
        this.regValue = regValue;
    }

    public RegValue getRegValue(){
        return regValue;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
