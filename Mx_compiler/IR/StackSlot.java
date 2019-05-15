package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class StackSlot extends IRReg{
    private IRFunc curFunc;
    private String name;

    public StackSlot(IRFunc curFunc , String name , boolean isArgSlot){
        this.curFunc = curFunc;
        this.name = name;
        if(!isArgSlot){
            curFunc.getStackSlots().add(this);
        }
    }

    public IRFunc getFunc(){
        return curFunc;
    }

    public String getName(){
        return name;
    }

    @Override
    public void accept(IRVisitor visitor){}

    @Override
    public RegValue copy(){
        return null;
    }
}
