package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public abstract class RegValue {
    public abstract void accept(IRVisitor visitor);

    public abstract RegValue copy();
}
