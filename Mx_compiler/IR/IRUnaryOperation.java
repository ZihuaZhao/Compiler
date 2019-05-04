package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class IRUnaryOperation extends IRInstruction {
    public enum IRUnaryOp{
        NEG , BITWISE_NOT , LOG_NOT
    }

    private IRReg dest;
    private IRUnaryOp op;
    private RegValue rhs;

    public IRUnaryOperation(BasicBlock curBlock , IRReg dest , IRUnaryOp op , RegValue rhs){
        super(curBlock);
        this.dest = dest;
        this.op = op;
        this.rhs = rhs;
    }

    public IRReg getDest(){
        return dest;
    }

    public void setDest(IRReg dest){
        this.dest = dest;
    }

    public IRUnaryOp getOp(){
        return op;
    }

    public void setOp(IRUnaryOp op){
        this.op = op;
    }

    public RegValue getRhs(){
        return rhs;
    }

    public void setRhs(RegValue rhs){
        this.rhs = rhs;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
