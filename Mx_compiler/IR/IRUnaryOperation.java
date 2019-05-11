package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

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
        reloadUsedReg();
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

    @Override
    public void reloadUsedReg(){
        usedRegs.clear();
        usedRegValues.clear();
        if(rhs instanceof IRReg)
            usedRegs.add((IRReg) rhs);
        usedRegValues.add(rhs);
    }

    @Override
    public void setUsedRegs(Map<IRReg , IRReg> renamaMap){
        if(rhs instanceof IRReg)
            rhs = renamaMap.get(rhs);
        reloadUsedReg();
    }

    @Override
    public IRReg getDefinedReg(){
        return dest;
    }

    @Override
    public void setDefinedReg(IRReg vreg){
        dest = vreg;
    }

    @Override
    public IRUnaryOperation copyRename(Map<Object , Object> renameMap){
        return new IRUnaryOperation(
                (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) ,
                (IRReg) renameMap.getOrDefault(dest , dest) , op ,
                (RegValue) renameMap.getOrDefault(rhs , rhs)
        );
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
