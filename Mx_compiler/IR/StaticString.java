package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

public class StaticString extends StaticData {
    private String value;

    public StaticString(String value){
        super("_static_str" , 8);
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
