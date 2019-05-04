package Mx_compiler.IR;

import Mx_compiler.Scope.FuncEntity;
import Mx_compiler.visitor.IRVisitor;

import java.util.*;

public class IRFunc {
    private String name;
    private FuncEntity funcEntity;
    private List<VirtualReg> virtualRegList = new ArrayList<>();
    private List<BasicBlock> reversePostOrder = null , reversePreOrder = null;
    private List<IRReturn> retuenList = new ArrayList<>();
    private BasicBlock startBlock = null , endBlock = null;

    public IRFunc(FuncEntity funcEntity){
        this.funcEntity = funcEntity;
        name = funcEntity.getName();
        if(funcEntity.isMember()){
            name = String.format("_member_%s_%s" , funcEntity.getClassName() , funcEntity.getName());
        }
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

    public List<IRReturn> getRetuenList(){
        return retuenList;
    }

    public void addVirtualReg(VirtualReg virtualReg){
        virtualRegList.add(virtualReg);
    }

    public BasicBlock genStartBlock(){
        startBlock = new BasicBlock(funcEntity.getName() + "_entry" , this);
        return startBlock;
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
}
