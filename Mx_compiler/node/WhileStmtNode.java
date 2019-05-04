package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public class WhileStmtNode extends StmtNode{
    private ExprNode cond;
    StmtNode body;

    public WhileStmtNode(ExprNode cond , StmtNode body , Location loc){
        this.cond = cond;
        this.body = body;
        this.location = loc;
    }

    public ExprNode getCond(){
        return cond;
    }

    public StmtNode getBody(){
        return body;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
