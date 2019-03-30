package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

public class ReturnStmtNode extends StmtNode {
    private ExprNode expr;

    public ReturnStmtNode(ExprNode expr , Location loc){
        this.expr = expr;
        this.location = loc;
    }

    public ExprNode getExpr(){
        return expr;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
