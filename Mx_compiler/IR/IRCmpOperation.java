package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class IRCmpOperation extends IRInstruction{
    public enum IRCmpOp{
        GT , LT , GTE , LTE , EQ , NEQ
    }

    private IRReg dest;
    private IRCmpOp op;
    private RegValue lhs , rhs;

    public IRCmpOperation(BasicBlock curBlock , IRReg dest , IRCmpOp op , RegValue lhs , RegValue rhs){
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

    public IRCmpOperation.IRCmpOp getOp(){
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
