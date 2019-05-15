package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public abstract class PhysicalReg extends IRReg{
    @Override
    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

    @Override
    public PhysicalReg copy(){
        return null;
    }

    public abstract String getName();
    public abstract boolean isGeneral();
    public abstract boolean isCallerSave();
    public abstract boolean isCalleeSave();
    public abstract boolean isArg();
    public abstract int getArgIndex();
}
