package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.Map;

public abstract class IRJumpInst extends IRInstruction {
    public IRJumpInst(BasicBlock basicBlock){
        super(basicBlock);
    }

    public abstract void accept(IRVisitor visitor);

    @Override
    public abstract IRJumpInst copyRename(Map<Object , Object> renameMap);
}
