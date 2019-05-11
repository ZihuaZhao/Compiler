package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

public class IRMove extends IRInstruction{
    private IRReg lhs;
    private RegValue rhs;

    public IRMove(BasicBlock block , IRReg lhs , RegValue rhs){
        super(block);
        this.lhs = lhs;
        this.rhs = rhs;
        reloadUsedReg();
    }

    public IRReg getLhs(){
        return lhs;
    }

    public RegValue getRhs(){
        return rhs;
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
    public void setUsedRegs(Map<IRReg , IRReg> renameMap){
        if(rhs instanceof IRReg)
            renameMap.get(rhs);
        reloadUsedReg();
    }

    @Override
    public IRReg getDefinedReg(){
        return lhs;
    }

    @Override
    public void setDefinedReg(IRReg vreg){
        lhs = vreg;
    }

    @Override
    public IRMove copyRename(Map<Object , Object> renameMap){
        return new IRMove(
                (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) ,
                (IRReg) renameMap.getOrDefault(lhs , lhs) ,
                (RegValue) renameMap.getOrDefault(rhs , rhs)
        );
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
