package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

import java.util.List;

public class ClassDeclNode extends DeclNode{
    private List<VarDeclNode> varMember;
    private List<FuncDeclNode> funcMember;
    private ClassBuildNode classBuild;

    public ClassDeclNode(String name , List<VarDeclNode> var , List<FuncDeclNode> func , ClassBuildNode build , Location loc){
        this.name = name;
        this.location = loc;
        this.varMember = var;
        this.funcMember = func;
        this.classBuild = build;
    }

    public List<VarDeclNode> getVarMember(){
        return varMember;
    }

    public List<FuncDeclNode> getFuncMember(){
        return funcMember;
    }

    public ClassBuildNode getClassBuild(){
        return classBuild;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }

}
