package Mx_compiler.IR;

import Mx_compiler.frontend.IRBuilder;
import Mx_compiler.visitor.IRVisitor;

public class IRBranch extends IRJumpInst{
    private RegValue cond;
    private BasicBlock thenBlock;
    private BasicBlock elseBlock;

    public IRBranch(BasicBlock curBlock , RegValue cond , BasicBlock thenBlock , BasicBlock elseBlock){
        super(curBlock);
        this.cond = cond;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    public RegValue getCond(){
        return cond;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

    public BasicBlock getThenBlock(){
        return thenBlock;
    }

    public BasicBlock getElseBlock(){
        return elseBlock;
    }
}
