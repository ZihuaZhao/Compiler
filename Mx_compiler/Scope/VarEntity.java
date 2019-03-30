package Mx_compiler.Scope;

import Mx_compiler.node.VarDeclNode;
import Mx_compiler.type.Type;

public class VarEntity extends Entity{
    private boolean isGlobal = false;
    private boolean isMember = false;
    private String className;

    public VarEntity(String name , Type type , String className){
        this.name = name;
        this.className = className;
        this.isMember = true;
        this.type = type;
    }

    public VarEntity(String name , Type type){
        this.type = type;
        this.name = name;
    }

    public VarEntity(VarDeclNode node){
        this.name = node.getName();
        this.type = node.getType().getType();
    }

    public boolean isGlobal(){
        return isGlobal;
    }

    public boolean isMember(){
        return isMember;
    }
}
