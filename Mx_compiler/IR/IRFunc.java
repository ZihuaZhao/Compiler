package Mx_compiler.IR;

import Mx_compiler.Scope.FuncEntity;
import Mx_compiler.visitor.IRVisitor;

import java.util.*;

public class IRFunc {
    private String name;
    private FuncEntity funcEntity;
    private List<VirtualReg> virtualRegList = new ArrayList<>();
    private List<BasicBlock> reversePostOrder = null , reversePreOrder = null;
    private List<IRReturn> returnList = new ArrayList<>();
    private BasicBlock startBlock = null , endBlock = null;
    private boolean recursiveCall = false;
    private String builtInCallLabel;
    private boolean isBuiltIn = false;
    private boolean isMemFunc = false;

    public IRFunc(){}

    public IRFunc(FuncEntity funcEntity){
        this.funcEntity = funcEntity;
        name = funcEntity.getName();
        if(funcEntity.isMember()){
            name = String.format("__member_%s_%s" , funcEntity.getClassName() , funcEntity.getName());
        }
    }

    public IRFunc(String name , String builtInCallLabel){
        this.name = name;
        this.builtInCallLabel = builtInCallLabel;
        this.funcEntity = null;
        this.isBuiltIn = true;
    }

    public String getName(){
        return name;
    }

    public FuncEntity getFuncEntity(){
        return funcEntity;
    }

    public List<VirtualReg> getVirtualRegList(){
        return virtualRegList;
    }

    public void setVirtualRegList(List<VirtualReg> virtualRegList){
        this.virtualRegList = virtualRegList;
    }

    public List<IRReturn> getReturnList(){
        return returnList;
    }

    public void addVirtualReg(VirtualReg virtualReg){
        virtualRegList.add(virtualReg);
    }

    public BasicBlock genStartBlock(){
        startBlock = new BasicBlock(funcEntity.getName() + "_entry" , this);
        return startBlock;
    }

    public BasicBlock getStartBlock(){
        return startBlock;
    }

    public void setStartBlock(BasicBlock startBlock){
        this.startBlock = startBlock;
    }

    public BasicBlock getEndBlock(){
        return endBlock;
    }

    public void setEndBlock(BasicBlock endBlock){
        this.endBlock = endBlock;
    }

    public void setMemFunc(boolean isMemFunc){
        this.isMemFunc = isMemFunc;
    }

    public boolean isMemFunc() {
        return isMemFunc;
    }

    public Set<IRFunc> calleeSet = new HashSet<>();
    public Set<IRFunc> recursiveCalleeSet = new HashSet<>();

    public void updateCalleeSet(){
        calleeSet.clear();
        for(BasicBlock block : getReversePostOrder()){
            for(IRInstruction inst = block.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
                if(inst instanceof IRFunctionCall){
                    calleeSet.add(((IRFunctionCall) inst).getIrFunc());
                }
            }
        }
    }

    public void setRecursiveCall(boolean recursiveCall){
        this.recursiveCalleeSet = recursiveCalleeSet;
    }

    public boolean isRecursiveCall(){
        return recursiveCall;
    }

    public void accept(IRVisitor visitor){
        visitor.visit(this);
    }

    private Set<BasicBlock> dfsVisited = null;

    private void dfsPostOrder(BasicBlock bb){
        if(dfsVisited.contains(bb)) return;
        dfsVisited.add(bb);
        for(BasicBlock succBlock : bb.getSuccBlock()){
            dfsPostOrder(succBlock);
        }
        reversePostOrder.add(bb);
    }

    private void dfsPreOrder(BasicBlock bb){
        if(dfsVisited.contains(bb)) return;
        dfsVisited.add(bb);
        reversePreOrder.add(bb);
        for(BasicBlock succBlock : bb.getSuccBlock()){
            dfsPreOrder(succBlock);
        }
    }

    public void doPostOrder(){
        reversePostOrder = new ArrayList<>();
        dfsVisited = new HashSet<>();
        dfsPostOrder(startBlock);
        dfsVisited = null;
        for(int i = 0 ; i < reversePostOrder.size() ; ++i){
            reversePostOrder.get(i).setPostNum(i);
        }
        Collections.reverse(reversePostOrder);
    }

    public List<BasicBlock> getReversePostOrder(){
        if(reversePostOrder == null){
            doPostOrder();
        }
        return reversePostOrder;
    }

    public void doPreOrder(){
        reversePreOrder = new ArrayList<>();
        dfsVisited = new HashSet<>();
        dfsPreOrder(startBlock);
        dfsVisited = null;
        for(int i = 0 ; i < reversePreOrder.size() ; ++i){
            reversePreOrder.get(i).setPreNum(i);
        }
        Collections.reverse(reversePreOrder);
    }

    public List<BasicBlock> getReversePreOrder(){
        if(reversePreOrder == null){
            doPreOrder();
        }
        return reversePreOrder;
    }

    private List<StackSlot> stackSlots = new ArrayList<>();

    public List<StackSlot> getStackSlots(){
        return stackSlots;
    }

    private Map<VirtualReg , StackSlot> argStackSlotMap = new HashMap<>();

    public Map<VirtualReg , StackSlot> getArgStackSlotMap(){
        return argStackSlotMap;
    }

    public Set<PhysicalReg> usedPhysicalGeneralRegs = new HashSet<>();

    public Set<PhysicalReg> getUsedPhysicalGeneralRegs(){
        return usedPhysicalGeneralRegs;
    }

    public String getBuiltInCallLabel(){
        return builtInCallLabel;
    }

    public boolean isBuiltIn(){
        return isBuiltIn;
    }
}
