package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

public class IRLoad extends IRInstruction{
    private IRReg dest;
    private int size , offset;
    private RegValue addr;
    private boolean isStatic , isLoadAddr;

    public IRLoad(BasicBlock curBlock , IRReg dest , int size , RegValue addr , int offset){
        super(curBlock);
        this.dest = dest;
        this.size = size;
        this.addr = addr;
        this.offset = offset;
        reloadUsedReg();
    }

    public IRLoad(BasicBlock curBlock , IRReg dest , int size , StaticData addr , boolean isLoadAddr){
        this(curBlock , dest , size , addr , 0);
        this.isStatic = true;
        this.isLoadAddr = isLoadAddr;
    }

    public IRReg getDest(){
        return dest;
    }

    public void setDest(IRReg dest){
        this.dest = dest;
    }

    public int getSize(){
        return size;
    }

    public void setSize(int size){
        this.size = size;
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
        if(addr instanceof  IRReg && !(addr instanceof StackSlot))
            usedRegs.add((IRReg) addr);
        usedRegValues.add(addr);
    }

    @Override
    public void setUsedRegs(Map<IRReg , IRReg> renameMap){
        if(addr instanceof  IRReg && !(addr instanceof StackSlot))
            renameMap.get(addr);
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
    public IRLoad copyRename(Map<Object , Object> renameMap){
        if(isStatic){
            return new IRLoad(
                    (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) ,
                    (IRReg) renameMap.getOrDefault(dest , addr) , size ,
                    (StaticData) renameMap.getOrDefault(addr , addr) , isLoadAddr
            );
        }
        else{
            return new IRLoad(
                    (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) ,
                    (IRReg) renameMap.getOrDefault(dest , dest) , size ,
                    (RegValue) renameMap.getOrDefault(addr ,addr) , offset
            );
        }
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
