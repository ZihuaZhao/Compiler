package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

public class StringLitExprNode extends ExprNode {
    private String value;

    public StringLitExprNode(String v , Location loc){
        this.value = v;
        this.location = loc;
    }

    public String getValue(){
        return value;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
