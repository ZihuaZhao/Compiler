package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

public class ThisExprNode extends ExprNode {
    public ThisExprNode(Location loc){
        this.location = loc;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
