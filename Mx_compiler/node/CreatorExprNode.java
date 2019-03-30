package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.type.Type;
import Mx_compiler.utility.Location;

import java.util.List;

public class CreatorExprNode extends ExprNode{
    private TypeNode newType;
    private List<ExprNode> dims;
    private int dimNum;

    public CreatorExprNode(TypeNode type , List<ExprNode>dims , int num , Location loc){
        this.newType = type;
        this.dims = dims;
        this.dimNum = num;
        this.location = loc;
    }

    public TypeNode getNewType(){
        return newType;
    }

    public List<ExprNode> getDims(){
        return dims;
    }

    public int getDimNum(){
        return dimNum;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }


}
