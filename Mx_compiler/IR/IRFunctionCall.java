package Mx_compiler.IR;

import Mx_compiler.visitor.IRVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IRFunctionCall extends IRInstruction {
    private IRFunc irFunc;
    private List<RegValue> args = new ArrayList<>();
    private IRReg vreg;

    public IRFunctionCall(BasicBlock block , IRFunc irFunc){
        super(block);
        this.irFunc = irFunc;
    }

    public IRFunctionCall(BasicBlock curBlock , IRFunc irFunc , List<RegValue> args , IRReg vreg){
        super(curBlock);
        this.irFunc = irFunc;
        this.args = args;
        this.vreg = vreg;
        reloadUsedReg();
    }

    public IRFunc getIrFunc(){
        return irFunc;
    }

    public void setIrFunc(IRFunc irFunc){
        this.irFunc = irFunc;
    }

    public List<RegValue> getArgs(){
        return args;
    }

    public void setArgs(List<RegValue> args){
        this.args = args;
    }

    public IRReg getVreg(){
        return vreg;
    }

    public void setVreg(IRReg vreg){
        this.vreg = vreg;
    }

    @Override
    public void reloadUsedReg(){
        usedRegs.clear();
        usedRegValues.clear();
        for(RegValue arg : args){
            if(arg instanceof IRReg)
                usedRegs.add((IRReg) arg);
            usedRegValues.add(arg);
        }
    }

    @Override
    public void setUsedRegs(Map<IRReg , IRReg> renameMap){
        for(int i = 0 ; i < args.size() ; ++i){
            if(args.get(i) instanceof IRReg){
                args.set(i , renameMap.get((IRReg) args.get(i)));
            }
        }
        reloadUsedReg();
    }

    @Override
    public IRReg getDefinedReg(){
        return vreg;
    }

    @Override
    public void setDefinedReg(IRReg vreg){
        this.vreg = vreg;
    }

    @Override
    public IRFunctionCall copyRename(Map<Object , Object> renameMap){
        List<RegValue> copyArgs = new ArrayList<>();
        for(RegValue arg : args){
            copyArgs.add((RegValue) renameMap.getOrDefault(arg , arg));
        }
        return new IRFunctionCall(
                (BasicBlock) renameMap.getOrDefault(getBlock() , getBlock()) , irFunc , copyArgs ,
                (VirtualReg) renameMap.getOrDefault(vreg , vreg)
        );
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }
}
