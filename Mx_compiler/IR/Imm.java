package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class Imm extends RegValue{
    private int value;

    public Imm(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void modifyValue(int value){
        this.value = value;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

    @Override
    public Imm copy(){
        return new Imm(value);
    }
}
