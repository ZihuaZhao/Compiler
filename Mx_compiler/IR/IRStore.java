package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

public class IRStore extends IRInstruction{
    private RegValue value , addr;
    private int size , offset;
    private boolean isStatic;

    public IRStore(BasicBlock curBlock , RegValue value , int size , RegValue addr , int offset){
        super(curBlock);
        this.value = value;
        this.size = size;
        this.addr = addr;
        this.offset = offset;
        this.isStatic = false;
        reloadUsedReg();
    }

    public IRStore(BasicBlock curBlock , RegValue value , int size , StaticData addr){
        this(curBlock , value , size , addr , 0);
        this.isStatic = true;
    }

    public RegValue getValue(){
        return value;
    }

    public int getSize(){
        return size;
    }

    public RegValue getAddr(){
        return addr;
    }

    public void setAddr(RegValue addr){
        this.addr = addr;
    }

    public int getOffset(){
        return offset;
    }

    public void setOffset(int offset){
        this.offset = offset;
    }

    @Override
    public void reloadUsedReg(){
        usedRegs.clear();
        usedRegValues.clear();
        if(addr instanceof IRReg && !(addr instanceof StackSlot))
            usedRegs.add((IRReg) addr);
        if(value instanceof IRReg)
            usedRegs.add((IRReg) value);
        usedRegValues.add(addr);
        usedRegValues.add(value);
    }

    @Override
    public void setUsedRegs(Map<IRReg , IRReg> renameMap){
        if(addr instanceof IRReg && !(addr instanceof StackSlot))
            addr = renameMap.get(addr);
        if(value instanceof IRReg)
            value = renameMap.get(value);
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
    public IRStore copyRename(Map<Object , Object> renameMap){
        if(isStatic){
            return new IRStore(
                    (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) ,
                    (RegValue) renameMap.getOrDefault(value , value) , size ,
                    (StaticData) renameMap.getOrDefault(addr , addr)
            );
        }
        else{
            return new IRStore(
                    (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) ,
                    (RegValue) renameMap.getOrDefault(value , value) , size ,
                    (RegValue) renameMap.getOrDefault(addr , addr) , offset
            );
        }
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
