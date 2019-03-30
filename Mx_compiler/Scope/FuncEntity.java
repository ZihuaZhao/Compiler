package Mx_compiler.Scope;

import Mx_compiler.node.ClassBuildNode;
import Mx_compiler.node.FuncDeclNode;
import Mx_compiler.node.VarDeclNode;
import Mx_compiler.type.ClassType;
import Mx_compiler.type.FunctionType;
import Mx_compiler.type.Type;

import java.util.ArrayList;
import java.util.List;

public class FuncEntity extends Entity {
    private List<VarEntity> params;
    private Type returnType;
    private String className;
    private boolean isConstruct = false , isMember = false , isBuiltIn = false;

    public FuncEntity(String name , Type type){
        this.name = name;
        this.type = type;
    }

    public FuncEntity(ClassBuildNode node , String className){
        this.name = node.getName();
        this.type = new FunctionType(node.getName());
        this.returnType = null;
        this.params.add(new VarEntity(Scope.THIS_PARA_NAME , new ClassType(className)));
        isMember = true;
        isConstruct = true;
    }

    public FuncEntity(FuncDeclNode node , String className){
        this.name = node.getName();
        this.type = new FunctionType(node.getName());
        this.params = new ArrayList<>();
        params.add(new VarEntity(Scope.THIS_PARA_NAME , new ClassType(className)));
        for(VarDeclNode param : node.getParamList()){
            params.add(new VarEntity(param));
        }
        returnType = node.getReturnType().getType();
        isConstruct = false;
        isMember = true;
    }

    public FuncEntity(FuncDeclNode node){
        this.name = node.getName();
        this.type = new FunctionType(node.getName());
        this.params = new ArrayList<>();
        for(VarDeclNode param : node.getParamList()){
            params.add(new VarEntity(param));
        }
        returnType = node.getReturnType().getType();
        isConstruct = false;
        isMember = false;
        className = null;
    }

    public List<VarEntity> getParams(){
        return params;
    }

    public void setParams(List<VarEntity> params){
        this.params = params;
    }

    public boolean isConstruct(){
        return isConstruct;
    }

    public boolean isMember(){
        return isMember;
    }

    public void setMember(boolean isMember){
        this.isMember = isMember;
    }

    public boolean isBuiltIn(){
        return isBuiltIn;
    }

    public void setBuiltIn(boolean isBuiltIn){
        this.isBuiltIn = isBuiltIn;
    }

    public Type getReturnType(){
        return this.returnType;
    }

    public void setReturnType(Type type){
        this.returnType = type;
    }
}
