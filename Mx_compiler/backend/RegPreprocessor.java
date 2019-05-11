package Mx_compiler.backend;

import Mx_compiler.IR.*;
import Mx_compiler.nasm.NASMReg;
import Mx_compiler.nasm.NASMRegSet;

public class RegPreprocessor {
    private IRRoot irRoot;

    public RegPreprocessor(IRRoot irRoot){
        this.irRoot = irRoot;
    }

    private void processArgs(IRFunc irFunc){
        IRInstruction firstInst = irFunc.getStartBlock().getFirstInst();
        for(int i = 6 ; i < irFunc.getVirtualRegList().size() ; ++i){
            VirtualReg arg = irFunc.getVirtualRegList().get(i);
            StackSlot argSlot = new StackSlot(irFunc , "arg" + i , true);
            irFunc.getArgStackSlotMap().put(arg , argSlot);
            firstInst.prependInst(new IRLoad(firstInst.getBlock() , arg , 8 , argSlot , 0));
        }
        if(irFunc.getVirtualRegList().size() > 0)
            irFunc.getVirtualRegList().get(0).setPhysicalReg(NASMRegSet.rdi);
        if(irFunc.getVirtualRegList().size() > 1)
            irFunc.getVirtualRegList().get(1).setPhysicalReg(NASMRegSet.rsi);
        if(irFunc.getVirtualRegList().size() > 2)
            irFunc.getVirtualRegList().get(2).setPhysicalReg(NASMRegSet.rdx);
        if(irFunc.getVirtualRegList().size() > 3)
            irFunc.getVirtualRegList().get(3).setPhysicalReg(NASMRegSet.rcx);
        if(irFunc.getVirtualRegList().size() > 4)
            irFunc.getVirtualRegList().get(4).setPhysicalReg(NASMRegSet.r8);
        if(irFunc.getVirtualRegList().size() > 5)
            irFunc.getVirtualRegList().get(5).setPhysicalReg(NASMRegSet.r9);
    }

    public void run(){
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            processArgs(irFunc);
        }
    }

}
