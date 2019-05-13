package Mx_compiler.node;

import Mx_compiler.Scope.VarEntity;
import Mx_compiler.visitor.AstVisitor;
import Mx_compiler.utility.Location;

public class IdExprNode extends ExprNode{
    private String name;
    private VarEntity varEntity = null;
    private boolean isMemOp = false;
    private boolean isChecked = false;

    public IdExprNode(String name , Location loc){
        this.name = name;
        this.location = loc;
    }

    public boolean isChecked(){
        return isChecked;
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }

    public String getName(){
        return this.name;
    }

    public VarEntity getVarEntity(){
        return varEntity;
    }

    public void setVarEntity(VarEntity varEntity){
        this.varEntity = varEntity;
    }

    public boolean getMemOp(){
        return isMemOp;
    }

    public void setMemOp(boolean isMemOp){
        this.isMemOp = isMemOp;
    }

    public boolean isMemOp(){
        return isMemOp;
    }

    @Override
    public void accept(AstVisitor visitor){
        visitor.visit(this);
    }
}
