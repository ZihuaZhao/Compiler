package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class IRBinaryOperation extends IRInstruction{
    public enum IRBinaryOp{
        ADD , SUB , MUL , DIV , MOD ,
        LSFT , RSFT , BITWISE_AND , BITWISE_OR , BITWISE_XOR
    }

    private IRReg dest;
    private IRBinaryOp op;
    private RegValue lhs , rhs;

    public IRBinaryOperation(BasicBlock curBlock , IRReg dest , IRBinaryOp op , RegValue lhs , RegValue rhs){
        super(curBlock);
        this.dest = dest;
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IRReg getDest(){
        return dest;
    }

    public void setDest(IRReg dest){
        this.dest = dest;
    }

    public IRBinaryOp getOp(){
        return op;
    }

    public RegValue getLhs(){
        return lhs;
    }

    public RegValue getRhs(){
        return rhs;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
