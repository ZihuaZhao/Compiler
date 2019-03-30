package Mx_compiler.Scope;

import Mx_compiler.node.ClassDeclNode;
import Mx_compiler.node.FuncDeclNode;
import Mx_compiler.type.ClassType;
import Mx_compiler.type.Type;

public class ClassEntity extends Entity{
    private Scope scope;

    public ClassEntity(String name , Type type , Scope parentScope){
        this.name = name;
        this.type = type;
        this.scope = new Scope(parentScope);
    }

    public ClassEntity(ClassDeclNode node , Scope parentScope){
        this.name = node.getName();
        this.type = new ClassType(node.getName());
        String key;
        FuncEntity entity;
        scope = new Scope(parentScope);
        for(FuncDeclNode funcDeclNode : node.getFuncMember()){
            key = scope.funcKey(funcDeclNode.getName());
            entity = new FuncEntity(funcDeclNode , name);
            scope.putCheck(funcDeclNode.getName() , key , entity);
        }
        if(node.getClassBuild() != null){
            key = scope.funcKey(node.getName());
            entity = new FuncEntity(node.getClassBuild() , node.getName());
            scope.putCheck(node.getName() , key , entity);
        }
    }

    public Scope getScope(){
        return scope;
    }

}
