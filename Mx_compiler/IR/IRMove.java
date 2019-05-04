package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class IRMove extends IRInstruction{
    private IRReg lhs;
    private RegValue rhs;

    public IRMove(BasicBlock block , IRReg lhs , RegValue rhs){
        super(block);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IRReg getLhs(){
        return lhs;
    }

    public RegValue getRhs(){
        return rhs;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
