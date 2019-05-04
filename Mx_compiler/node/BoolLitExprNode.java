package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public class BoolLitExprNode extends ExprNode {
    private boolean value;

    public BoolLitExprNode(boolean v , Location loc){
        this.value = v;
        this.location = loc;
    }

    public boolean getValue(){
        return value;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
