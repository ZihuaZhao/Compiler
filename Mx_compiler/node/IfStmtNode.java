package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;

public class IfStmtNode extends StmtNode {
    private ExprNode cond;
    private BlockStmtNode body;
    private BlockStmtNode elseStmt;

    public IfStmtNode(ExprNode cond , BlockStmtNode body , BlockStmtNode elseS){
        this.cond = cond;
        this.body = body;
        this.elseStmt = elseS;
    }

    public ExprNode getCond(){
        return cond;
    }

    public BlockStmtNode getBody(){
        return body;
    }

    public BlockStmtNode getElseStmt(){
        return elseStmt;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
