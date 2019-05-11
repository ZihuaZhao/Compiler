package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

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
        reloadUsedReg();
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

    @Override
    public void reloadUsedReg(){
        usedRegValues.clear();
        usedRegs.clear();
        if(lhs instanceof IRReg)
            usedRegs.add((IRReg) lhs);
        if(rhs instanceof IRReg)
            usedRegs.add((IRReg) rhs);
        usedRegValues.add(lhs);
        usedRegValues.add(rhs);
    }

    @Override
    public void setUsedRegs(Map<IRReg , IRReg> renameMap){
        if(lhs instanceof IRReg)
            lhs = renameMap.get(lhs);
        if(rhs instanceof IRReg)
            rhs = renameMap.get(rhs);
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
    public IRBinaryOperation copyRename(Map<Object , Object> renameMap){
        return new IRBinaryOperation((BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) ,
                (IRReg) renameMap.getOrDefault(dest , dest) , op , (RegValue) renameMap.getOrDefault(lhs , lhs) ,
                (RegValue) renameMap.getOrDefault(rhs , rhs));
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
