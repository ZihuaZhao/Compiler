package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class VirtualReg extends IRReg{
    private String name;

    public VirtualReg(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
