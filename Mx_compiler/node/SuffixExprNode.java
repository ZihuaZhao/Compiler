package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public class SuffixExprNode extends ExprNode {
    public enum SuffixOps{
        SUFFIX_INC , SUFFIX_DEC
    }

    private SuffixOps op;
    private ExprNode expr;

    public SuffixExprNode(SuffixOps op , ExprNode expr , Location loc){
        this.op = op;
        this.expr = expr;
        this.location = loc;
    }

    public SuffixOps getOp(){
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
