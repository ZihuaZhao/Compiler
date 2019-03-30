package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;

public class VarDeclNode extends DeclNode {
    private TypeNode type;
    private ExprNode expr;

    public VarDeclNode(TypeNode type , String name , ExprNode expr){
        this.type = type;
        this.name = name;
        this.expr = expr;
    }

    public VarDeclNode(TypeNode type , String name){
        this.type = type;
        this.name = name;
        this.expr = null;
    }

    public TypeNode getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public ExprNode getExpr(){
        return expr;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
