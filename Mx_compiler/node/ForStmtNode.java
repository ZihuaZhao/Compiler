package Mx_compiler.node;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

public class ForStmtNode extends StmtNode {
    private ExprNode init , cond , step;
    private StmtNode body;

    public ForStmtNode(ExprNode init , ExprNode cond , ExprNode step , StmtNode body , Location loc){
        this.init = init;
        this.cond = cond;
        this.step = step;
        this.body = body;
        this.location = loc;
    }

    public ExprNode getInit(){
        return init;
    }

    public ExprNode getCond(){
        return cond;
    }

    public ExprNode getStep(){
        return step;
    }

    public StmtNode getBody(){
        return body;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
