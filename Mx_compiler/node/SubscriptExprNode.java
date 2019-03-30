package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

public class SubscriptExprNode extends ExprNode {
    private ExprNode arr , sub;

    public SubscriptExprNode(ExprNode arr , ExprNode sub , Location loc){
        this.arr = arr;
        this.sub = sub;
        this.location = loc;
    }

    public ExprNode getArr(){
        return arr;
    }

    public ExprNode getSub(){
        return sub;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
