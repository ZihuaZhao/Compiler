package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

public class AssignExprNode extends ExprNode{
    private ExprNode left , right;

    public AssignExprNode(ExprNode left , ExprNode right , Location loc){
        this.left = left;
        this.right = right;
        this.location = loc;
    }

    public ExprNode getLeft(){
        return left;
    }

    public ExprNode getRight(){
        return right;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
