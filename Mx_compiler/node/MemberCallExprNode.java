package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

public class MemberCallExprNode extends ExprNode {
    private ExprNode expr;
    String id;

    public MemberCallExprNode(ExprNode expr , String id , Location loc){
        this.expr = expr;
        this.id = id;
        this.location = loc;
    }

    public ExprNode getExpr(){
        return expr;
    }

    public String getId(){
        return id;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
