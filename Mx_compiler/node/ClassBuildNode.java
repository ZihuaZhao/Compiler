package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public class ClassBuildNode extends DeclNode{
    private String name;
    private BlockStmtNode block;

    public ClassBuildNode (String name , BlockStmtNode block , Location loc){
        this.name = name;
        this.block = block;
        this.location = loc;
    }

    public ClassBuildNode (){
        this.name = null;
        this.block = null;
        this.location = null;
    }

    public String getName(){
        return name;
    }

    public BlockStmtNode getBlock(){
        return block;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
