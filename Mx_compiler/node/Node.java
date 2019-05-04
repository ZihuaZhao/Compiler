package Mx_compiler.node;

import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public abstract class Node {
    protected Location location;

    public Location location(){
        return location;
    }

    abstract public void accept(AstVisitor visitor);

}
