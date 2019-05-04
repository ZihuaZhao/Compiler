package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public abstract class StaticData extends IRReg{
    String name;
    int size;

    public StaticData(String name , int size){
        this.name = name;
        this.size = size;
    }

    public String getName(){
        return name;
    }

    public int getSize(){
        return size;
    }

    public abstract void accept(IRVisitor visitor);


}
