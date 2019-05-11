package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public BasicBlock getBlock(){
        return curBlock;
    }

    public void prependInst(IRInstruction inst){
        if(prevInst != null){
            prevInst.modifySuccInst(inst);
            inst.modifyPrevInst(prevInst);
        }
        else{
            curBlock.setFirstInst(inst);
        }
        inst.modifySuccInst(this);
        prevInst = inst;
    }

    public void appendInst(IRInstruction inst){
        if(succInst != null){
            succInst.modifyPrevInst(inst);
            inst.modifySuccInst(succInst);
        }
        else{
            curBlock.setLastInst(inst);
        }
        inst.modifyPrevInst(this);
        succInst = inst;
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

    protected List<IRReg> usedRegs = new ArrayList<>();
    protected List<RegValue> usedRegValues = new ArrayList<>();
    private boolean removed = false;
    public Set<VirtualReg> liveIn = null , liveOut = null;

    public void remove(){
        if(removed)
            throw new Error("cannot remove an removed inst");
        removed = true;
        if(prevInst != null)
            prevInst.modifySuccInst(succInst);
        if(succInst != null)
            succInst.modifyPrevInst(prevInst);
        if(this instanceof IRJumpInst)
            curBlock.delJumpInst();
        if(this == curBlock.getFirstInst())
            curBlock.setFirstInst(succInst);
        if(this == curBlock.getLastInst())
            curBlock.setLastInst(prevInst);
    }

    public void replace(IRInstruction inst){
        if(removed)
            throw new Error("cannot replace an removed inst");
        removed = true;
        inst.modifyPrevInst(prevInst);
        inst.modifySuccInst(succInst);
        if(prevInst != null)
            prevInst.modifySuccInst(inst);
        if(succInst != null)
            succInst.modifyPrevInst(inst);
        if(this == curBlock.getFirstInst())
            curBlock.setFirstInst(inst);
        if(this == curBlock.getLastInst())
            curBlock.setLastInst(inst);
    }

    public List<IRReg> getUsedRegs(){
        return usedRegs;
    }

    public List<RegValue> getUsedRegValues(){
        return usedRegValues;
    }

    public boolean isRemoved(){
        return removed;
    }

    public abstract IRInstruction copyRename(Map<Object , Object> renameMap);

    public abstract void reloadUsedReg();

    public abstract IRReg getDefinedReg();

    public abstract void setUsedRegs(Map<IRReg , IRReg> renameMap);

    public abstract void setDefinedReg(IRReg vreg);
}
