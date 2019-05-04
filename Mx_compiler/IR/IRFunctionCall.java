package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.ArrayList;
import java.util.List;

public class IRFunctionCall extends IRInstruction {
    private IRFunc irFunc;
    private List<RegValue> args = new ArrayList<>();
    private VirtualReg vreg;

    public IRFunctionCall(BasicBlock block , IRFunc irFunc){
        super(block);
        this.irFunc = irFunc;
    }

    public IRFunctionCall(BasicBlock curBlock , IRFunc irFunc , List<RegValue> args , VirtualReg vreg){
        super(curBlock);
        this.irFunc = irFunc;
        this.args = args;
        this.vreg = vreg;
    }

    public IRFunc getIrFunc(){
        return irFunc;
    }

    public void setIrFunc(IRFunc irFunc){
        this.irFunc = irFunc;
    }

    public List<RegValue> getArgs(){
        return args;
    }

    public void setArgs(List<RegValue> args){
        this.args = args;
    }

    public VirtualReg getVreg(){
        return vreg;
    }

    public void setVreg(VirtualReg vreg){
        this.vreg = vreg;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
