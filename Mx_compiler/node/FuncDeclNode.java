package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

import java.util.List;

public class FuncDeclNode extends DeclNode{
    private boolean isConstruct;
    private TypeNode returnType;
    private List<VarDeclNode> paramList;
    private BlockStmtNode funcBody;

    public FuncDeclNode(TypeNode re , String name , List<VarDeclNode> paramList , BlockStmtNode funcBody , Location loc){
        this.isConstruct = false;
        this.returnType = re;
        this.name = name;
        this.funcBody = funcBody;
        this.paramList = paramList;
        this.location = loc;
    }

    public boolean isConstruct(){
        return isConstruct;
    }

    public TypeNode getReturnType(){
        return returnType;
    }

    public List<VarDeclNode> getParamList(){
        return paramList;
    }

    public BlockStmtNode getFuncBody(){
        return funcBody;
    }


    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
