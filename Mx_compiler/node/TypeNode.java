package Mx_compiler.node;
import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.type.Type;
import Mx_compiler.utility.Location;

public class TypeNode extends Node {
    private Type type;

    public TypeNode(Type type){
        this.type = type;
    }

    public TypeNode(Type type , Location loc){
        this.type = type;
        this.location = loc;
    }

    public void setType(Type type){
        this.type = type;
    }

    public Type getType(){
        return type;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }

}
