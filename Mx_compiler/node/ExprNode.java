package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.type.Type;

public class ExprNode extends Node{
    private Type type;
    private boolean isLeftValue;

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

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
