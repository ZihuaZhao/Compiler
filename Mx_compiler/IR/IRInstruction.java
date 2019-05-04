package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public abstract class IRInstruction {
    private IRInstruction prevInst = null , succInst = null;
    private BasicBlock curBlock;

    public IRInstruction(BasicBlock curBlock){
        this.prevInst = null;
        this.succInst = null;
        this.curBlock = curBlock;
    }

    public IRInstruction(IRInstruction prevInst , IRInstruction succInst , BasicBlock curBlock){
        this.prevInst = prevInst;
        this.succInst = succInst;
        this.curBlock = curBlock;
    }

    public IRInstruction getPrevInst(){
        return prevInst;
    }

    public IRInstruction getSuccInst(){
        return succInst;
    }

    public void modifyPrevInst(IRInstruction irInstruction){
        this.prevInst = irInstruction;
    }

    public void modifySuccInst(IRInstruction irInstruction){
        this.succInst = irInstruction;
    }

    public abstract void accept(IRVisitor visitor);
}
