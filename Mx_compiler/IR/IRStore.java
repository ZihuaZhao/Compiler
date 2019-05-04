package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

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
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
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
}
