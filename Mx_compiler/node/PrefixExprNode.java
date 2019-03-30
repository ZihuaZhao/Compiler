package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

public class PrefixExprNode extends ExprNode {
    public enum PrefixOps{
        PREFIX_INC , PREFIX_DEC , POS , NEG , LOG_NOT , BITWISE_NOT
    }

    private PrefixOps op;
    private ExprNode expr;

    public PrefixExprNode(PrefixOps op , ExprNode expr , Location loc){
        this.op = op;
        this.expr = expr;
        this.location = loc;
    }

    public PrefixOps getOp(){
        return op;
    }

    public ExprNode getExpr(){
        return expr;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }

}
