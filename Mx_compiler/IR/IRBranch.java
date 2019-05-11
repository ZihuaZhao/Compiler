package Mx_compiler.IR;

import Mx_compiler.frontend.IRBuilder;
import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

public class IRBranch extends IRJumpInst{
    private RegValue cond;
    private BasicBlock thenBlock;
    private BasicBlock elseBlock;

    public IRBranch(BasicBlock curBlock , RegValue cond , BasicBlock thenBlock , BasicBlock elseBlock){
        super(curBlock);
        this.cond = cond;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
        reloadUsedReg();
    }

    public RegValue getCond(){
        return cond;
    }

    public BasicBlock getThenBlock(){
        return thenBlock;
    }

    public void setThenBlock(BasicBlock thenBlock){
        this.thenBlock = thenBlock;
    }

    public BasicBlock getElseBlock(){
        return elseBlock;
    }

    public void setElseBlock(BasicBlock elseBlock){
        this.elseBlock = elseBlock;
    }

    @Override
    public void reloadUsedReg(){
        usedRegs.clear();
        usedRegValues.clear();
        if(cond instanceof IRReg)
            usedRegs.add((IRReg) cond);
        usedRegValues.add(cond);
    }

    @Override
    public void setUsedRegs(Map<IRReg , IRReg> renameMap){
        if(cond instanceof IRReg)
            cond = renameMap.get(cond);
        reloadUsedReg();
    }

    @Override
    public IRReg getDefinedReg(){
        return null;
    }

    @Override
    public void setDefinedReg(IRReg vreg){
        return;
    }

    @Override
    public IRBranch copyRename(Map<Object , Object> renameMap){
        return new IRBranch(
                (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) , (RegValue) renameMap.getOrDefault(cond ,cond) ,
                (BasicBlock) renameMap.getOrDefault(thenBlock , thenBlock) , (BasicBlock) renameMap.getOrDefault(elseBlock , elseBlock)
        );
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
