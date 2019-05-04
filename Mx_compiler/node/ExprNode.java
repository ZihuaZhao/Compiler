package Mx_compiler.node;

import Mx_compiler.IR.BasicBlock;
import Mx_compiler.IR.RegValue;
import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.type.Type;

public class ExprNode extends Node{
    private Type type;
    private boolean isLeftValue;
    private BasicBlock trueBasicBlock , falseBasicBlock;
    private RegValue regValue , addrValue;
    private int addrOffset;

    public Type getType(){
        return type;
    }

    public boolean isLeftType(){
        return isLeftValue;
    }

    public void setType(Type type){
        this.type = type;
    }

    public void setLeftValue(boolean isLeftValue){
        this.isLeftValue = isLeftValue;
    }

    public BasicBlock getTrue(){
        return trueBasicBlock;
    }

    public BasicBlock getFalse(){
        return falseBasicBlock;
    }

    public void setTrue(BasicBlock trueBasicBlock){
        this.trueBasicBlock = trueBasicBlock;
    }

    public void setFalse(BasicBlock falseBasicBlock){
        this.falseBasicBlock = falseBasicBlock;
    }

    public RegValue getRegValue(){
        return regValue;
    }

    public void setRegValue(RegValue regValue){
        this.regValue = regValue;
    }

    public RegValue getAddrValue(){
        return addrValue;
    }

    public void setAddrValue(RegValue addrValue){
        this.addrValue = addrValue;
    }

    public int getAddrOffset(){
        return addrOffset;
    }

    public void setAddrOffset(int addrOffset){
        this.addrOffset = addrOffset;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
