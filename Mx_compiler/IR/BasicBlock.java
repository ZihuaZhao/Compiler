package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.HashSet;
import java.util.Set;

public class BasicBlock {
    private String name;
    private IRFunc irFunc;
    private IRInstruction firstInst = null , lastInst = null;
    private boolean isJump = false;
    private int postNum = 0 , preNum = 0;
    private Set<BasicBlock> prevBlock = new HashSet<>(), succBlock = new HashSet<>();

    public BasicBlock(String name , IRFunc irFunc){
        this.name = name;
        this.irFunc = irFunc;
    }

    public void addInst(IRInstruction irInstruction){
        if(firstInst == null && lastInst == null){
            firstInst = irInstruction;
            lastInst = irInstruction;
        }
        else if(firstInst == lastInst){
            firstInst.modifySuccInst(irInstruction);
            lastInst = irInstruction;
            lastInst.modifyPrevInst(firstInst);
        }
        else{
            lastInst.modifySuccInst(irInstruction);
            irInstruction.modifyPrevInst(lastInst);
            lastInst = irInstruction;
        }
    }

    public String getName(){
        return name;
    }

    public IRFunc getIrFunc(){
        return irFunc;
    }

    public boolean isJump(){
        return isJump;
    }

    public int getPostNum(){
        return postNum;
    }

    public void setPostNum(int i){
        postNum = i;
    }

    public int getPreNum(){
        return preNum;
    }

    public void setPreNum(int i){
        preNum = i;
    }

    public void addPrevBlock(BasicBlock prev){
        prevBlock.add(prev);
    }

    public void addSuccBlock(BasicBlock succ){
        succBlock.add(succ);
        if(succ != null){
            succ.addPrevBlock(this);
        }
    }

    public void addJumpInst(IRJumpInst jumpInst){
        addInst(jumpInst);
        isJump = true;
        if(jumpInst instanceof IRJump){
            addSuccBlock(((IRJump) jumpInst).getTarBlock());
        }
        else if(jumpInst instanceof IRBranch){
            addSuccBlock(((IRBranch) jumpInst).getThenBlock());
            addSuccBlock(((IRBranch) jumpInst).getElseBlock());
        }
        else if(jumpInst instanceof IRReturn){
            irFunc.getRetuenList().add((IRReturn)jumpInst);
        }
        else{
            throw new Error("invalid jump instruction");
        }
    }

    public Set<BasicBlock> getPrevBlock(){
        return prevBlock;
    }

    public Set<BasicBlock> getSuccBlock(){
        return succBlock;
    }

    public IRInstruction getFirstInst(){
        return firstInst;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }


}
