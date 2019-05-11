package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class VirtualReg extends IRReg{
    private String name;
    private PhysicalReg physicalReg = null;

    public VirtualReg(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public PhysicalReg getPhysicalReg(){
        return physicalReg;
    }

    public void setPhysicalReg(PhysicalReg physicalReg){
        this.physicalReg = physicalReg;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
