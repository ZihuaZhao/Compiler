package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

public class IRHeapAlloc extends IRInstruction {
    private IRReg dest;
    private RegValue allocSize;

    public IRHeapAlloc(BasicBlock curBlock , IRReg dest , RegValue allocSize){
        super(curBlock);
        this.dest = dest;
        this.allocSize = allocSize;
        reloadUsedReg();
    }

    public IRReg getDest(){
        return dest;
    }

    public void setDest(IRReg dest){
        this.dest = dest;
    }

    public RegValue getAllocSize(){
        return allocSize;
    }

    public void setAllocSize(RegValue allocSize){
        this.allocSize = allocSize;
    }

    @Override
    public void reloadUsedReg(){
        usedRegs.clear();
        usedRegValues.clear();
        if(allocSize instanceof IRReg)
            usedRegs.add((IRReg) allocSize);
        usedRegValues.add(allocSize);
    }

    @Override
    public void setUsedRegs(Map<IRReg , IRReg> renameMap){
        if(allocSize instanceof IRReg)
            allocSize = renameMap.get(allocSize);
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
    public IRHeapAlloc copyRename(Map<Object , Object> renameMap){
        return new IRHeapAlloc(
                (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) ,
                (IRReg) renameMap.getOrDefault(dest ,dest) ,
                (RegValue) renameMap.getOrDefault(allocSize , allocSize)
        );
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
