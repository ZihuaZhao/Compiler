package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class IRJump extends IRJumpInst{
    private BasicBlock tarBlock;

    public IRJump(BasicBlock curBlock , BasicBlock tarBlock){
        super(curBlock);
        this.tarBlock = tarBlock;
    }

    public BasicBlock getTarBlock(){
        return tarBlock;
    }

    public void setTarBlock(BasicBlock block){
        this.tarBlock = block;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
