package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public class BreakStmtNode extends StmtNode {

    public BreakStmtNode(Location loc){
        this.location = loc;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
