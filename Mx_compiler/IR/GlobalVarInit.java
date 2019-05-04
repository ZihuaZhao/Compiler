package Mx_compiler.IR;

import Mx_compiler.node.ExprNode;

public class GlobalVarInit {
    private String name;
    private ExprNode varInit;

    public GlobalVarInit(String name , ExprNode varInit){
        this.name = name;
        this.varInit = varInit;
    }

    public String getName(){
        return name;
    }

    public ExprNode getVarInit(){
        return varInit;
    }
}
