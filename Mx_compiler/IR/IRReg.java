package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public abstract class IRReg extends RegValue{
    public abstract void accept(IRVisitor visitor);
}
