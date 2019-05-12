package Mx_compiler.backend;

import Mx_compiler.IR.*;

public class TwoRegTransform {
    private IRRoot irRoot;

    public TwoRegTransform(IRRoot irRoot){
        this.irRoot = irRoot;
    }

    public void run(){
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            for(BasicBlock block : irFunc.getReversePostOrder()){
                for(IRInstruction inst = block.getFirstInst() , succInst ; inst != null ; inst = succInst){
                    succInst = inst.getSuccInst();
                    if(!(inst instanceof IRBinaryOperation)) continue;
                    IRBinaryOperation binInst = (IRBinaryOperation) inst;
                    if(binInst.getDest() == binInst.getLhs()) continue;
                    if(binInst.getDest() == binInst.getRhs()){
                        if(binInst.isCommutativeOp()){
                            binInst.setRhs(binInst.getLhs());
                            binInst.setLhs(binInst.getDest());
                        }
                        else{
                            VirtualReg vreg = new VirtualReg("rhsBak");
                            binInst.prependInst(new IRMove(binInst.getBlock() , vreg , binInst.getRhs()));
                            binInst.prependInst(new IRMove(binInst.getBlock() , binInst.getDest() , binInst.getLhs()));
                            binInst.setLhs(binInst.getDest());
                            binInst.setRhs(vreg);
                        }
                    }
                    else if(binInst.getOp() != IRBinaryOperation.IRBinaryOp.DIV && binInst.getOp() != IRBinaryOperation.IRBinaryOp.MOD){
                        binInst.prependInst(new IRMove(binInst.getBlock() , binInst.getDest() , binInst.getLhs()));
                        binInst.setLhs(binInst.getDest());
                    }
                }
            }
        }
    }
}
