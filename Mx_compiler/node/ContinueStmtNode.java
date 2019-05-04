package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public class ContinueStmtNode extends StmtNode {
    public ContinueStmtNode(Location loc){
        this.location = loc;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
