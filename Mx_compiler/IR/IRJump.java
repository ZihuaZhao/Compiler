package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

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

    @Override
    public void reloadUsedReg() {
        return;
    }

    @Override
    public void setUsedRegs(Map<IRReg , IRReg> renameMap) {
        return;
    }

    @Override
    public IRReg getDefinedReg() {
        return null;
    }

    @Override
    public void setDefinedReg(IRReg vreg) {
        return;
    }

    @Override
    public IRJump copyRename(Map<Object , Object> renameMap){
        return new IRJump(
                (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) ,
                (BasicBlock) renameMap.getOrDefault(tarBlock , tarBlock)
        );
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
