package Mx_compiler.Scope;

import Mx_compiler.type.Type;

abstract public class Entity {
    protected String name;
    protected Type type;

    public Entity(){}

    public Entity(String name , Type type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public Type getType(){
        return type;
    }
}
