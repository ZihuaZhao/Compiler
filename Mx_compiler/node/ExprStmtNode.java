package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public class ExprStmtNode extends StmtNode {
    private ExprNode expr;

    public ExprStmtNode(ExprNode expr , Location loc){
        this.expr = expr;
        this.location = loc;
    }

    public ExprStmtNode(ExprNode expr){
        this.expr = expr;
    }

    public ExprNode getExpr(){
        return expr;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
