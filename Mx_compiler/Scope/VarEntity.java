package Mx_compiler.Scope;

import Mx_compiler.IR.IRReg;
import Mx_compiler.IR.StaticData;
import Mx_compiler.node.VarDeclNode;
import Mx_compiler.type.Type;

public class VarEntity extends Entity{
    private boolean isGlobal = false;
    private boolean isMember = false;
    private String className;
    private IRReg irReg;
    private int addrOffset;

    public VarEntity(String name , Type type , String className){
        this.name = name;
        this.className = className;
        this.isMember = true;
        this.type = type;
    }

    public VarEntity(String name , Type type){
        this.type = type;
        this.name = name;
    }

    public VarEntity(VarDeclNode node){
        this.name = node.getName();
        this.type = node.getType().getType();
    }

    public boolean isGlobal(){
        return isGlobal;
    }

    public boolean isMember(){
        return isMember;
    }

    public void setIrReg(IRReg irReg){
        this.irReg = irReg;
    }

    public IRReg getIrReg(){
        return irReg;
    }

    public int getAddrOffset(){
        return addrOffset;
    }

    public void setAddrOffset(int addrOffset){
        this.addrOffset = addrOffset;
    }
}
