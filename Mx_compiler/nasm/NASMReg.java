package Mx_compiler.nasm;

import Mx_compiler.IR.PhysicalReg;

public class NASMReg extends PhysicalReg {
    private String name;
    private boolean isGeneral , isCallerSave , isCalleeSave;
    private int argIndex;

    public NASMReg(String name , boolean isGeneral , boolean isCallerSave , boolean isCalleeSave , int argIndex){
        this.name = name;
        this.isGeneral = isGeneral;
        this.isCallerSave = isCallerSave;
        this.isCalleeSave = isCalleeSave;
        this.argIndex = argIndex;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public boolean isGeneral(){
        return isGeneral;
    }

    @Override
    public boolean isCallerSave(){
        return isCallerSave;
    }

    @Override
    public boolean isCalleeSave(){
        return isCalleeSave;
    }

    @Override
    public boolean isArg(){
        return argIndex != -1;
    }

    @Override
    public int getArgIndex(){
        return argIndex;
    }
}
