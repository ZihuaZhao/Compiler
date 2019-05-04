package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class IRLoad extends IRInstruction{
    private IRReg dest;
    private int size , offset;
    private RegValue addr;
    private boolean isStatic;

    public IRLoad(BasicBlock curBlock , IRReg dest , int size , RegValue addr , int offset){
        super(curBlock);
        this.dest = dest;
        this.size = size;
        this.addr = addr;
        this.offset = offset;
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

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
