package Mx_compiler.node;

import Mx_compiler.Scope.FuncEntity;
import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

import java.util.List;

public class FuncCallExprNode extends ExprNode{
    private ExprNode func;
    private List<ExprNode> args;
    private FuncEntity funcEntity;

    public FuncCallExprNode(ExprNode func , List<ExprNode> args , Location loc){
        this.func = func;
        this.args = args;
        this.location = loc;
    }

    public ExprNode getFunc(){
        return  func;
    }

    public List<ExprNode> getArgs(){
        return args;
    }

    public FuncEntity getFuncEntity(){
        return funcEntity;
    }

    public void setFuncEntity(FuncEntity funcEntity){
        this.funcEntity = funcEntity;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
