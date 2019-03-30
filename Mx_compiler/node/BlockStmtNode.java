package Mx_compiler.node;

import Mx_compiler.Scope.Scope;
import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

import java.util.ArrayList;
import java.util.List;

public class BlockStmtNode extends StmtNode{
    private List<Node> statments;
    private Scope scope;

    public BlockStmtNode(List<Node>stats , Location loc){
        this.statments = stats;
        this.location = loc;
    }

    public BlockStmtNode(ExprStmtNode node , Location loc){
        List<Node> s = new ArrayList<>();
        s.add(node);
        this.statments = s;
        this.location = loc;
    }

    public BlockStmtNode(IfStmtNode node , Location loc){
        List<Node> s = new ArrayList<>();
        s.add(node);
        this.statments = s;
        this.location = loc;
    }

    public BlockStmtNode(ForStmtNode node , Location loc){
        List<Node> s = new ArrayList<>();
        s.add(node);
        this.statments = s;
        this.location = loc;
    }

    public BlockStmtNode(WhileStmtNode node , Location loc){
        List<Node> s = new ArrayList<>();
        s.add(node);
        this.statments = s;
        this.location = loc;
    }

    public BlockStmtNode(ReturnStmtNode node , Location loc){
        List<Node> s = new ArrayList<>();
        s.add(node);
        this.statments = s;
        this.location = loc;
    }

    public BlockStmtNode(VarDeclNode node , Location loc){
        List<Node> s = new ArrayList<>();
        s.add(node);
        this.statments = s;
        this.location = loc;
    }

    public Location location(){
        return location;
    }

    public List<Node> getstmts(){
        return statments;
    }

    public void initScope(Scope parentScope){
        scope = new Scope(parentScope);
    }

    public Scope getScope(){
        return scope;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
