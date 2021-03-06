package Mx_compiler.node;

import Mx_compiler.type.IntType;
import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public class IntLitExprNode extends ExprNode {
    private int value;

    public IntLitExprNode(int v , Location loc){
        //TODO
        this.setType(IntType.getType());
        this.value = v;
        this.location = loc;
    }

    public int getValue(){
        return value;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
