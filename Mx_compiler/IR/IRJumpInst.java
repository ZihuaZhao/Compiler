package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public abstract class IRJumpInst extends IRInstruction {
    public IRJumpInst(BasicBlock basicBlock){
        super(basicBlock);
    }
}
