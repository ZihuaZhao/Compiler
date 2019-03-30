package Mx_compiler.node;
import Mx_compiler.ast.AstVisitor;
import Mx_compiler.utility.Location;

import java.util.List;

public class ProgramNode extends Node{
    private List<DeclNode> declarators;

    public ProgramNode(List<DeclNode> declarators , Location loc){
        this.declarators = declarators;
        this.location = loc;
    }

    public List<DeclNode> getDeclarators(){
        return declarators;
    }

    public Location location(){
        return location;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
