package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class StaticVar extends StaticData{
    public StaticVar(String name , int size){
        super(name , size);
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
