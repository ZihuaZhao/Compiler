package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public class BinaryExprNode extends ExprNode{
    public enum BinaryOps{
        MUL , DIV , MOD , ADD , SUB , LSFT , RSFT , LT , GT , LTE , GTE , EQ , NEQ,
        BITWISE_AND , BITWISE_OR , BITWISE_XOR , LOG_AND , LOG_OR
    }

    private BinaryOps op;
    private ExprNode left , right;

    public BinaryExprNode(BinaryOps op , ExprNode left , ExprNode right , Location loc){
        this.op = op;
        this.left = left;
        this.right = right;
        this.location = loc;
    }

    public BinaryOps getOp(){
        return op;
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
