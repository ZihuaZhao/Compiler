package Mx_compiler.frontend;

import Mx_compiler.IR.*;
import Mx_compiler.Scope.ClassEntity;
import Mx_compiler.Scope.FuncEntity;
import Mx_compiler.Scope.Scope;
import Mx_compiler.Scope.VarEntity;
import Mx_compiler.node.*;
import Mx_compiler.type.*;

import java.util.ArrayList;
import java.util.List;

public class IRBuilder extends BasicScopeScanner {
    private IRRoot irRoot = new IRRoot();
    private Scope globalScope, curScope;
    private IRFunc curFunc = null;
    private BasicBlock curBlock = null;
    private List<GlobalVarInit> globalVarInitList = new ArrayList<>();
    private boolean isFuncArg = false;
    private String curClassName;
    private BasicBlock curLoopBlock = null, curNextBlock = null;
    private boolean isAddr = false;


    public IRRoot getIrRoot() {
        return irRoot;
    }

    public IRBuilder(Scope globalScope) {
        this.globalScope = globalScope;
    }

    private void processIRAssign(RegValue dest , int addrOffset , ExprNode rhs , int size , boolean isMemOp){
        if(rhs.getTrue() != null){
            BasicBlock mergeBlock = new BasicBlock(null , curFunc);
            if(isMemOp){
                rhs.getTrue().addInst(new IRStore(rhs.getTrue() , new Imm(1) , 8 , dest , addrOffset));
                rhs.getFalse().addInst(new IRStore(rhs.getFalse() , new Imm(0) , 8 , dest , addrOffset));
            }
            else{
                rhs.getTrue().addInst(new IRMove(rhs.getTrue() , (VirtualReg)dest , new Imm(1)));
                rhs.getFalse().addInst(new IRMove(rhs.getFalse() , (VirtualReg)dest , new Imm(0)));
            }
            if(!rhs.getTrue().isJump()){
                rhs.getTrue().addJumpInst(new IRJump(rhs.getTrue() , mergeBlock));
            }
            if(!rhs.getFalse().isJump()){
                rhs.getFalse().addJumpInst(new IRJump(rhs.getFalse() , mergeBlock));
            }
            curBlock = mergeBlock;
        }
        else{
            if(isMemOp){
                curBlock.addInst(new IRStore(curBlock , rhs.getRegValue() , 8 , dest , addrOffset));
            }
            else{
                curBlock.addInst(new IRMove(curBlock , (IRReg)dest , rhs.getRegValue()));
            }
        }
    }

    @Override
    public void visit(ProgramNode node) {
        curScope = globalScope;
        for (DeclNode declNode : node.getDeclarators()) {
            if (declNode instanceof FuncDeclNode) {
                FuncEntity funcEntity = (FuncEntity) curScope.get(Scope.funcKey(declNode.getName()));
                IRFunc irFunc = new IRFunc(funcEntity);
                irRoot.addFunc(irFunc);
            } else if (declNode instanceof ClassDeclNode) {
                ClassEntity classEntity = (ClassEntity) curScope.get(Scope.classKey(declNode.getName()));
                curScope = classEntity.getScope();
                for (FuncDeclNode funcDeclNode : ((ClassDeclNode) declNode).getFuncMember()) {
                    FuncEntity funcEntity = (FuncEntity) curScope.get(Scope.funcKey(funcDeclNode.getName()));
                    IRFunc irFunc = new IRFunc(funcEntity);
                    irRoot.addFunc(irFunc);
                }
                if(((ClassDeclNode) declNode).getClassBuild() != null){
                    FuncEntity funcEntity = (FuncEntity) curScope.get(Scope.funcKey(classEntity.getName()));
                    IRFunc irFunc = new IRFunc(funcEntity);
                    irRoot.addFunc(irFunc);
                }
                curScope = curScope.getParent();
            } else if (declNode instanceof VarDeclNode) {
                declNode.accept(this);
            }
        }
        FuncDeclNode funcDeclNode = StaticVarInst();
        funcDeclNode.accept(this);
        for (DeclNode declNode : node.getDeclarators()) {
            if (declNode instanceof FuncDeclNode) {
                declNode.accept(this);
            } else if (declNode instanceof ClassDeclNode) {
                declNode.accept(this);
            } else if(declNode instanceof VarDeclNode){

            } else{
              throw new Error("invalid decl node");
            }
        }
        for(IRFunc irFunc : irRoot.getFuncs().values()){
            irFunc.updateCalleeSet();
        }
        irRoot.updataCalleeSet();
    }

    @Override
    public void visit(VarDeclNode node) {
        VarEntity varEntity = (VarEntity) curScope.get(Scope.varKey(node.getName()));
        if (curScope.isTop()) {
            Type type = node.getType().getType();
            StaticData staticData = new StaticVar(node.getName(), 8);
            irRoot.addStaticData(staticData);
            varEntity.setIrReg(staticData);
            if (node.getExpr() != null) {
                GlobalVarInit varInit = new GlobalVarInit(node.getName(), node.getExpr());
                globalVarInitList.add(varInit);
            }
        } else {
            VirtualReg virtualReg = new VirtualReg(node.getName());
            varEntity.setIrReg(virtualReg);
            if (isFuncArg) {
                curFunc.addVirtualReg(virtualReg);
            }
            if (node.getExpr() == null) {
                if (!isFuncArg) curBlock.addInst(new IRMove(curBlock, virtualReg, new Imm(0)));
            }
            else{
                if(node.getExpr().getType() instanceof BoolType && !(node.getExpr() instanceof BoolLitExprNode)){
                    node.getExpr().setTrue(new BasicBlock(null , curFunc));
                    node.getExpr().setFalse(new BasicBlock(null , curFunc));
                }
                node.getExpr().accept(this);
                processIRAssign(virtualReg , 0 , node.getExpr() , node.getExpr().getType().getVarSize() , false);
            }
        }
    }

    @Override
    public void visit(ClassDeclNode node) {
        curClassName = node.getName();
        curScope = globalScope;
        for (FuncDeclNode funcDeclNode : node.getFuncMember()) {
            funcDeclNode.accept(this);
        }
        if(node.getClassBuild() != null){
            node.getClassBuild().accept(this);
        }
        curClassName = null;
    }

    @Override
    public void visit(ClassBuildNode node){
        String funcName = node.getName();
        funcName = String.format("__member_%s_%s" , curClassName , funcName);
        curFunc = irRoot.getIRFunc(funcName);
        curBlock = curFunc.genStartBlock();
        Scope lastScope = curScope;
        curScope = node.getBlock().getScope();
        VarEntity varEntity = (VarEntity) curScope.get(Scope.varKey(Scope.THIS_PARA_NAME));
        VirtualReg virtualReg = new VirtualReg(varEntity.getName());
        curFunc.addVirtualReg(virtualReg);
        varEntity.setIrReg(virtualReg);
        curScope = lastScope;
        node.getBlock().accept(this);
        if(!curBlock.isJump()){
            curBlock.addJumpInst(new IRReturn(curBlock , null));
        }
    }


    @Override
    public void visit(FuncDeclNode node) {
        String funcName = node.getName();
        if (curClassName != null) {
            funcName = String.format("__member_%s_%s", curClassName, funcName);
        }
        curFunc = irRoot.getIRFunc(funcName);
        curBlock = curFunc.genStartBlock();
        Scope lastScope = curScope;
        curScope = node.getFuncBody().getScope();
        if (curClassName != null) {
            VarEntity varEntity = (VarEntity) curScope.get(Scope.varKey(Scope.THIS_PARA_NAME));
            VirtualReg virtualReg = new VirtualReg(varEntity.getName());
            curFunc.addVirtualReg(virtualReg);
            varEntity.setIrReg(virtualReg);
        }
        isFuncArg = true;
        for (DeclNode declNode : node.getParamList()) {
            declNode.accept(this);
        }
        isFuncArg = false;
        curScope = lastScope;
        if(node.getName().equals("main")){
            curBlock.addInst(new IRFunctionCall(curBlock , irRoot.getIRFunc("_init_func") , new ArrayList<>() , null));
        }
        node.getFuncBody().accept(this);
        if(!curBlock.isJump()){
            if(node.getReturnType() == null || node.getReturnType().getType() instanceof VoidType){
                curBlock.addJumpInst(new IRReturn(curBlock , null));
            }
            else{
                curBlock.addJumpInst(new IRReturn(curBlock , new Imm(0)));
            }
        }
        if(curFunc.getReturnList().size() > 1){
            BasicBlock mergeEndBlock = new BasicBlock(curFunc.getName()+"_end" , curFunc);
            VirtualReg retReg;
            if(node.getReturnType() == null || node.getReturnType().getType() instanceof VoidType){
                retReg = null;
            }
            else{
                retReg = new VirtualReg("return_value");
            }
            List<IRReturn> returnInstList = new ArrayList<>(curFunc.getReturnList());
            for(IRReturn retInst : returnInstList){
                BasicBlock block = retInst.getBlock();
                if(retInst.getRegValue() != null){
                    retInst.prependInst(new IRMove(block , retReg , retInst.getRegValue()));
                }
                retInst.remove();
                block.addJumpInst(new IRJump(block , mergeEndBlock));
            }
            mergeEndBlock.addJumpInst(new IRReturn(mergeEndBlock , retReg));
            curFunc.setEndBlock(mergeEndBlock);
        }
        else{
            curFunc.setEndBlock(curFunc.getReturnList().get(0).getBlock());
        }
        curFunc = null;
    }

    public FuncDeclNode StaticVarInst() {
        BlockStmtNode staticVarBody = new BlockStmtNode();
        List<Node> stmts = new ArrayList<>();
        for (GlobalVarInit globalVarInit : globalVarInitList) {
            IdExprNode id = new IdExprNode(globalVarInit.getName(), null);
            id.setVarEntity((VarEntity) curScope.get(Scope.varKey(globalVarInit.getName())));
            AssignExprNode assignExprNode = new AssignExprNode(id, globalVarInit.getVarInit(), null);
            stmts.add(assignExprNode);
        }
        staticVarBody.setstmts(stmts);
        staticVarBody.setScope(globalScope);
        TypeNode returnType = new TypeNode(VoidType.getType(), null);
        FuncDeclNode funcDeclNode = new FuncDeclNode(returnType, "_init_func", new ArrayList<>(), staticVarBody, null);
        FuncEntity funcEntity = new FuncEntity(funcDeclNode);
        globalScope.put(Scope.funcKey("_init_func"), funcEntity);
        IRFunc irFunc = new IRFunc(funcEntity);
        irRoot.addFunc(irFunc);
        return funcDeclNode;
    }

    @Override
    public void visit(BlockStmtNode node) {
        curScope = node.getScope();
        for (Node stmtNode : node.getstmts()) {
            if (stmtNode instanceof VarDeclNode) {
                stmtNode.accept(this);
            } else {
                stmtNode.accept(this);
            }
        }
        curScope = curScope.getParent();
    }

    @Override
    public void visit(ExprStmtNode node){
        node.getExpr().accept(this);
    }

    @Override
    public void visit(IfStmtNode node) {
        BasicBlock thenBlock, elseBlock = null, nextBlock;
        if (node.getElseStmt() != null) {
            thenBlock = new BasicBlock("_if_then", curFunc);
            elseBlock = new BasicBlock("_if_else", curFunc);
            nextBlock = new BasicBlock("_if_next", curFunc);
            node.getCond().setTrue(thenBlock);
            node.getCond().setFalse(elseBlock);
            node.getCond().accept(this);
            if (node.getCond() instanceof BoolLitExprNode) {
                curBlock.addJumpInst(new IRBranch(curBlock, node.getCond().getRegValue(), node.getCond().getTrue(), node.getCond().getFalse()));
            }
        } else {
            thenBlock = new BasicBlock("_if_then", curFunc);
            nextBlock = new BasicBlock("_if_next", curFunc);
            node.getCond().setTrue(thenBlock);
            node.getCond().setFalse(nextBlock);
            node.getCond().accept(this);
            if (node.getCond() instanceof BoolLitExprNode) {
                curBlock.addJumpInst(new IRBranch(curBlock, node.getCond().getRegValue(), node.getCond().getTrue(), node.getCond().getFalse()));
            }
        }
        curBlock = thenBlock;
        node.getBody().accept(this);
        if(!curBlock.isJump())
            curBlock.addJumpInst(new IRJump(curBlock, nextBlock));
        if (node.getElseStmt() != null) {
            curBlock = elseBlock;
            node.getElseStmt().accept(this);
            if(!curBlock.isJump())
                curBlock.addJumpInst(new IRJump(curBlock, nextBlock));
        }
        curBlock = nextBlock;
    }

    @Override
    public void visit(WhileStmtNode node) {
        BasicBlock condBlock = new BasicBlock("_while_cond", curFunc);
        BasicBlock bodyBlock = new BasicBlock("_while_body", curFunc);
        BasicBlock nextBlock = new BasicBlock("_while_next", curFunc);
        BasicBlock tmpLoopBlock = curLoopBlock;
        BasicBlock tmpNextBlock = curNextBlock;
        curLoopBlock = condBlock;
        curNextBlock = nextBlock;
        curBlock.addJumpInst(new IRJump(curBlock , condBlock));
        curBlock = condBlock;
        node.getCond().accept(this);
        node.getCond().setTrue(bodyBlock);
        node.getCond().setFalse(nextBlock);
        if (node.getCond() instanceof BoolLitExprNode) {
            curBlock.addJumpInst(new IRBranch(curBlock, node.getCond().getRegValue(), node.getCond().getTrue(), node.getCond().getFalse()));
        }
        curBlock = bodyBlock;
        node.getBody().accept(this);
        curBlock.addJumpInst(new IRJump(curBlock, condBlock));
        curLoopBlock = tmpLoopBlock;
        curNextBlock = tmpNextBlock;
        curBlock = nextBlock;
    }

    @Override
    public void visit(ForStmtNode node) {
        BasicBlock condBlock;
        BasicBlock stepBlock;
        BasicBlock bodyBlock = new BasicBlock("_for_body", curFunc);
        BasicBlock nextBlock = new BasicBlock("_for_next", curFunc);
        if(node.getCond() != null)
            condBlock = new BasicBlock("_for_cond" , curFunc);
        else
            condBlock = bodyBlock;
        if(node.getStep() != null)
            stepBlock = new BasicBlock("_for_step" , curFunc);
        else
            stepBlock = condBlock;
        condBlock.forNode = node;
        stepBlock.forNode = node;
        bodyBlock.forNode = node;
        nextBlock.forNode = node;
        irRoot.forRecMap.put(node , new IRRoot.ForRecord(condBlock , stepBlock , bodyBlock , nextBlock));
        BasicBlock tmpLoopBlock = curLoopBlock, tmpNextBlock = curNextBlock;
        curLoopBlock = condBlock;
        curNextBlock = nextBlock;
        if (node.getInit() != null) {
            node.getInit().accept(this);
            curBlock.addJumpInst(new IRJump(curBlock, condBlock));
        }
        if (node.getCond() != null) {
            curBlock = condBlock;
            node.getCond().setTrue(bodyBlock);
            node.getCond().setFalse(nextBlock);
            node.getCond().accept(this);
            if (node.getCond() instanceof BoolLitExprNode) {
                curBlock.addJumpInst(new IRBranch(curBlock, node.getCond().getRegValue(), node.getCond().getTrue(), node.getCond().getFalse()));
            }
        }
        if (node.getStep() != null) {
            curBlock = stepBlock;
            node.getStep().accept(this);
            curBlock.addJumpInst(new IRJump(curBlock, condBlock));
        }
        curBlock = bodyBlock;
        if(node.getBody() == null){
            if(!curBlock.isJump()){
                curBlock.addJumpInst(new IRJump(curBlock , condBlock));
            }
        }
        else{
            node.getBody().accept(this);
            if(!curBlock.isJump()){
                curBlock.addJumpInst(new IRJump(curBlock , stepBlock));
            }
        }
        curLoopBlock = tmpLoopBlock;
        curNextBlock = tmpNextBlock;
        curBlock = nextBlock;
    }

    @Override
    public void visit(ContinueStmtNode node) {
        curBlock.addJumpInst(new IRJump(curBlock, curLoopBlock));
    }

    @Override
    public void visit(BreakStmtNode node) {
        curBlock.addJumpInst(new IRJump(curBlock, curNextBlock));
    }

    @Override
    public void visit(ReturnStmtNode node) {
        Type returnType = curFunc.getFuncEntity().getReturnType();
        if(returnType == null || returnType instanceof VoidType){
            curBlock.addJumpInst(new IRReturn(curBlock , null));
        }
        else{
            if(returnType instanceof BoolType && !(node.getExpr() instanceof BoolLitExprNode)){
                node.getExpr().setTrue(new BasicBlock(null , curFunc));
                node.getExpr().setFalse(new BasicBlock(null , curFunc));
                node.getExpr().accept(this);
                VirtualReg vreg = new VirtualReg("boolRet");
                processIRAssign(vreg , 0 , node.getExpr() , 8 , false);
                curBlock.addJumpInst(new IRReturn(curBlock , vreg));
            }
            else{
                node.getExpr().accept(this);
                curBlock.addJumpInst(new IRReturn(curBlock , node.getExpr().getRegValue()));
            }
        }
    }

    private boolean checkIdThisMember(IdExprNode node){
        if(!node.isChecked()){
            if(curClassName != null){
                VarEntity varEntity = (VarEntity) curScope.get(Scope.varKey(node.getName()));
                node.setMemOp(varEntity.getIrReg() == null);
            }
            else{
                node.setMemOp(false);
            }
            node.setChecked(true);
        }
        return node.isMemOp();
    }

    private boolean isMemExpr(ExprNode node){
        if(node instanceof SubscriptExprNode || node instanceof MemberCallExprNode || (node instanceof IdExprNode && checkIdThisMember((IdExprNode) node))){
            return true;
        }
        return false;
    }

    @Override
    public void visit(SuffixExprNode node) {
        boolean needMemOp = isMemExpr(node);
        boolean tmpAddr = isAddr;
        isAddr = false;
        node.getExpr().accept(this);
        VirtualReg vreg = new VirtualReg(null);
        curBlock.addInst(new IRMove(curBlock , vreg , node.getExpr().getRegValue()));
        node.setRegValue(vreg);
        IRBinaryOperation.IRBinaryOp op;
        if(node.getOp() == SuffixExprNode.SuffixOps.SUFFIX_INC){
            op = IRBinaryOperation.IRBinaryOp.ADD;
        }
        else op = IRBinaryOperation.IRBinaryOp.SUB;
        if(needMemOp){
            isAddr = true;
            node.getExpr().accept(this);
            VirtualReg reg = new VirtualReg(null);
            curBlock.addInst(new IRBinaryOperation(curBlock, reg, op, node.getExpr().getRegValue(), new Imm(1)));
            curBlock.addInst(new IRStore(curBlock, reg, 8, node.getExpr().getAddrValue(), node.getExpr().getAddrOffset()));
        }
        else
            curBlock.addInst(new IRBinaryOperation(curBlock , (IRReg)node.getExpr().getRegValue() , op , node.getExpr().getRegValue() , new Imm(1)));
        isAddr = tmpAddr;
    }

    public void processPrintFuncCall(ExprNode arg , String funcName){
        if(arg instanceof BinaryExprNode){
            processPrintFuncCall(((BinaryExprNode) arg).getLeft() , "print");
            processPrintFuncCall(((BinaryExprNode) arg).getRight() , funcName);
            return;
        }
        IRFunc calleeFunc;
        List<RegValue> args = new ArrayList<>();
        if(arg instanceof FuncCallExprNode && ((FuncCallExprNode) arg).getFuncEntity().getName() == "toString"){
            ExprNode intExpr = ((FuncCallExprNode) arg).getArgs().get(0);
            intExpr.accept(this);
            calleeFunc = irRoot.getBuiltInFunc(funcName + "Int");
            args.add(intExpr.getRegValue());
        }
        else{
            arg.accept(this);
            calleeFunc = irRoot.getBuiltInFunc(funcName);
            args.add(arg.getRegValue());
        }
        curBlock.addInst(new IRFunctionCall(curBlock , calleeFunc , args , null));
    }

    public void processBuiltInFuncCall(FuncCallExprNode node , ExprNode thisExpr , FuncEntity funcEntity , String funcName){
        boolean wantAddrBak = isAddr;
        isAddr = false;
        ExprNode arg0 , arg1;
        VirtualReg vreg;
        IRFunc calleeFunc;
        List<RegValue> args = new ArrayList<>();
        switch(funcName){
            case IRRoot.BUILTIN_PRINT_FUNC_NAME:
            case IRRoot.BUILTIN_PRINTLN_FUNC_NAME:
                arg0 = node.getArgs().get(0);
                processPrintFuncCall(arg0 , funcName);
                break;
            case IRRoot.BUILTIN_GET_STRING_FUNC_NAME:
                vreg = new VirtualReg("getString");
                calleeFunc = irRoot.getBuiltInFunc(IRRoot.BUILTIN_GET_STRING_FUNC_NAME);
                args.clear();
                curBlock.addInst(new IRFunctionCall(curBlock , calleeFunc , args , vreg));
                node.setRegValue(vreg);
                break;
            case IRRoot.BUILTIN_GET_INT_FUNC_NAME:
                vreg = new VirtualReg("getInt");
                calleeFunc = irRoot.getBuiltInFunc(IRRoot.BUILTIN_GET_INT_FUNC_NAME);
                args.clear();
                curBlock.addInst(new IRFunctionCall(curBlock , calleeFunc , args , vreg));
                node.setRegValue(vreg);
                break;
            case IRRoot.BUILTIN_TO_STRING_FUNC_NAME:
                arg0 = node.getArgs().get(0);
                arg0.accept(this);
                vreg = new VirtualReg("toString");
                calleeFunc = irRoot.getBuiltInFunc(IRRoot.BUILTIN_TO_STRING_FUNC_NAME);
                args.clear();
                args.add(arg0.getRegValue());
                curBlock.addInst(new IRFunctionCall(curBlock, calleeFunc, args, vreg));
                node.setRegValue(vreg);
                break;
            case IRRoot.BUILTIN_STRING_SUBSTRING_FUNC_NAME:
                arg0 = node.getArgs().get(0);
                arg0.accept(this);
                arg1 = node.getArgs().get(1);
                arg1.accept(this);
                vreg = new VirtualReg("subString");
                args.clear();
                args.add(thisExpr.getRegValue());
                args.add(arg0.getRegValue());
                args.add(arg1.getRegValue());
                calleeFunc = irRoot.getBuiltInFunc(IRRoot.BUILTIN_STRING_SUBSTRING_FUNC_NAME);
                curBlock.addInst(new IRFunctionCall(curBlock, calleeFunc, args, vreg));
                node.setRegValue(vreg);
                break;
            case IRRoot.BUILTIN_STRING_PARSEINT_FUNC_NAME:
                vreg = new VirtualReg("parseInt");
                calleeFunc = irRoot.getBuiltInFunc(IRRoot.BUILTIN_STRING_PARSEINT_FUNC_NAME);
                args.clear();
                args.add(thisExpr.getRegValue());
                curBlock.addInst(new IRFunctionCall(curBlock, calleeFunc, args, vreg));
                node.setRegValue(vreg);
                break;
            case IRRoot.BUILTIN_STRING_ORD_FUNC_NAME:
                vreg = new VirtualReg("ord");
                calleeFunc = irRoot.getBuiltInFunc(IRRoot.BUILTIN_STRING_ORD_FUNC_NAME);
                args.clear();
                args.add(thisExpr.getRegValue());
                arg0 = node.getArgs().get(0);
                arg0.accept(this);
                args.add(arg0.getRegValue());
                curBlock.addInst(new IRFunctionCall(curBlock, calleeFunc, args, vreg));
                node.setRegValue(vreg);
                break;
            case IRRoot.BUILTIN_STRING_LENGTH_FUNC_NAME:
            case IRRoot.BUILTIN_ARRAY_SIZE_FUNC_NAME:
                vreg = new VirtualReg("sizeOrLength");
                curBlock.addInst(new IRLoad(curBlock, vreg, 8, thisExpr.getRegValue(), 0));
                node.setRegValue(vreg);
                break;
            default:
                throw new Error("invalid built_in func");
        }
        isAddr = wantAddrBak;
    }

    @Override
    public void visit(FuncCallExprNode node){
        FuncEntity funcEntity = node.getFuncEntity();
        String funcName = funcEntity.getName();
        List<RegValue>args = new ArrayList<>();
        ExprNode thisExpr = null;
        if(funcEntity.isMember()) {
            if (node.getFunc() instanceof MemberCallExprNode) {
                thisExpr = ((MemberCallExprNode) (node.getFunc())).getExpr();
            } else {
                thisExpr = new ThisExprNode(null);
                thisExpr.setType(new ClassType(curClassName));
            }
            thisExpr.accept(this);
            String className;
            if (thisExpr.getType() instanceof ClassType) {
                className = ((ClassType) thisExpr.getType()).getName();
            } else if (thisExpr.getType() instanceof ArrayType) {
                className = Scope.ARRAY_CLASS_NAME;
            } else {
                className = Scope.STRING_CLASS_NAME;
            }
            funcName = String.format("__member_%s_%s", className, funcName);
            args.add(thisExpr.getRegValue());
        }
        if (funcEntity.isBuiltIn()) {
            processBuiltInFuncCall(node , thisExpr , funcEntity , funcName);
            return;
        }
        for(ExprNode arg : node.getArgs()){
            arg.accept(this);
            args.add(arg.getRegValue());
        }
        IRFunc irFunc = irRoot.getIRFunc(funcName);
        VirtualReg vreg = new VirtualReg(null);
        curBlock.addInst(new IRFunctionCall(curBlock , irFunc , args , vreg));
        node.setRegValue(vreg);
        if(node.getTrue() != null){
            curBlock.addJumpInst(new IRBranch(curBlock , node.getRegValue() , node.getTrue() , node.getFalse()));
        }
    }

    @Override
    public void visit(SubscriptExprNode node){
        boolean tmpAddr = isAddr;
        isAddr = false;
        node.getArr().accept(this);
        node.getSub().accept(this);
        isAddr = tmpAddr;
        VirtualReg vreg = new VirtualReg(null);
        curBlock.addInst(new IRBinaryOperation(curBlock , vreg , IRBinaryOperation.IRBinaryOp.MUL , node.getSub().getRegValue() , new Imm(node.getType().getVarSize())));
        curBlock.addInst(new IRBinaryOperation(curBlock , vreg , IRBinaryOperation.IRBinaryOp.ADD , node.getArr().getRegValue() , vreg));
        if(isAddr){
            node.setAddrValue(vreg);
            node.setAddrOffset(8);
        }
        else{
            curBlock.addInst(new IRLoad(curBlock , vreg , node.getType().getVarSize() , vreg , 8));
            node.setRegValue(vreg);
            if (node.getTrue() != null) {
                curBlock.addJumpInst(new IRBranch(curBlock , node.getRegValue() , node.getTrue() , node.getFalse()));
            }
        }
    }

    @Override
    public void visit(MemberCallExprNode node) {
        boolean tmpAddr = isAddr;
        isAddr = false;
        node.getExpr().accept(this);
        isAddr = tmpAddr;
        RegValue addr = node.getExpr().getRegValue();
        String name = ((ClassType) node.getExpr().getType()).getName();
        ClassEntity classEntity = (ClassEntity) curScope.getCheck(name, Scope.classKey(name));
        VarEntity varEntity = (VarEntity) classEntity.getScope().getSelf(Scope.varKey(node.getId()));
        if (isAddr) {
            node.setAddrValue(addr);
            node.setAddrOffset(varEntity.getAddrOffset());
        } else {
            VirtualReg vreg = new VirtualReg(null);
            node.setRegValue(vreg);
            curBlock.addInst(new IRLoad(curBlock, vreg, varEntity.getType().getVarSize(), addr, varEntity.getAddrOffset()));
            if (node.getTrue() != null)
                curBlock.addJumpInst(new IRBranch(curBlock, node.getRegValue(), node.getTrue(), node.getFalse()));
        }
    }

    @Override
    public void visit(PrefixExprNode node){
        VirtualReg vreg = new VirtualReg(null);
        switch(node.getOp()){
            case PREFIX_DEC:
            case PREFIX_INC:
                boolean isMemExpr = isMemExpr(node.getExpr());
                boolean tmpAddr = isAddr;
                isAddr = false;
                node.getExpr().accept(this);
                node.setRegValue(node.getExpr().getRegValue());
                IRBinaryOperation.IRBinaryOp op;
                if(node.getOp() == PrefixExprNode.PrefixOps.PREFIX_INC){
                    op = IRBinaryOperation.IRBinaryOp.ADD;
                }
                else op = IRBinaryOperation.IRBinaryOp.SUB;
                if(isMemExpr){
                    isAddr = true;
                    node.getExpr().accept(this);
                    VirtualReg reg = new VirtualReg(null);
                    curBlock.addInst(new IRBinaryOperation(curBlock, reg, op, node.getExpr().getRegValue(), new Imm(1)));
                    curBlock.addInst(new IRStore(curBlock, reg, 8, node.getExpr().getAddrValue(), node.getExpr().getAddrOffset()));
                    node.getExpr().setRegValue(reg);
                }
                else
                    curBlock.addInst(new IRBinaryOperation(curBlock , (IRReg)node.getExpr().getRegValue() , op , node.getExpr().getRegValue() , new Imm(1)));
                isAddr = tmpAddr;
                break;
            case POS:
                node.setRegValue(node.getExpr().getRegValue());
                break;
            case NEG:
                node.setRegValue(vreg);
                node.getExpr().accept(this);
                curBlock.addInst(new IRUnaryOperation(curBlock , vreg , IRUnaryOperation.IRUnaryOp.NEG , node.getExpr().getRegValue()));
                break;
            case BITWISE_NOT:
                node.setRegValue(vreg);
                node.getExpr().accept(this);
                curBlock.addInst(new IRUnaryOperation(curBlock , vreg , IRUnaryOperation.IRUnaryOp.BITWISE_NOT , node.getExpr().getRegValue()));
                break;
            case LOG_NOT:
                node.setRegValue(vreg);
                node.getExpr().accept(this);
                curBlock.addInst(new IRUnaryOperation(curBlock , vreg , IRUnaryOperation.IRUnaryOp.LOG_NOT , node.getExpr().getRegValue()));
                break;
        }
    }

    @Override
    public void visit(BinaryExprNode node){
        switch(node.getOp()){
            case LOG_AND:
                node.getLeft().setTrue(new BasicBlock("_and_lhs_true" , curFunc));
                node.getLeft().setFalse(node.getFalse());
                node.accept(this);
                node.getRight().setTrue(node.getTrue());
                node.getRight().setFalse(node.getFalse());
                node.getRight().accept(this);
                break;
            case LOG_OR:
                node.getLeft().setTrue(new BasicBlock("_or_lhs_true" , curFunc));
                node.getLeft().setFalse(node.getFalse());
                node.accept(this);
                node.getRight().setTrue(node.getTrue());
                node.getRight().setFalse(node.getFalse());
                node.getRight().accept(this);
                break;
            case MUL:
            case DIV:
            case ADD:
            case SUB:
            case MOD:
            case LSFT:
            case RSFT:
            case BITWISE_AND:
            case BITWISE_OR:
            case BITWISE_XOR:
                node.getLeft().accept(this);
                node.getRight().accept(this);
                RegValue lhs = node.getLeft().getRegValue();
                RegValue rhs = node.getRight().getRegValue();
                int lhsImm = 0 , rhsImm = 0;
                if(lhs instanceof Imm) lhsImm = ((Imm) lhs).getValue();
                if(rhs instanceof Imm) rhsImm = ((Imm) rhs).getValue();
                boolean bothConst = false;
                if(lhs instanceof Imm && rhs instanceof Imm) bothConst = true;
                IRBinaryOperation.IRBinaryOp op;
                switch(node.getOp()){
                    case MUL:
                        op = IRBinaryOperation.IRBinaryOp.MUL;
                        if(bothConst){
                            node.setRegValue(new Imm(lhsImm * rhsImm));
                            return;
                        }
                        break;
                    case DIV:
                        op = IRBinaryOperation.IRBinaryOp.DIV;
                        if(bothConst){
                            node.setRegValue(new Imm(lhsImm / rhsImm));
                            return;
                        }
                        break;
                    case ADD:
                        op = IRBinaryOperation.IRBinaryOp.ADD;
                        if(bothConst){
                            node.setRegValue(new Imm(lhsImm + rhsImm));
                            return;
                        }
                        break;
                    case SUB:
                        op = IRBinaryOperation.IRBinaryOp.SUB;
                        if(bothConst){
                            node.setRegValue(new Imm(lhsImm - rhsImm));
                            return;
                        }
                        break;
                    case MOD:
                        op = IRBinaryOperation.IRBinaryOp.MOD;
                        if(bothConst){
                            node.setRegValue(new Imm(lhsImm % rhsImm));
                            return;
                        }
                        break;
                    case LSFT:
                        op = IRBinaryOperation.IRBinaryOp.LSFT;
                        if(bothConst){
                            node.setRegValue(new Imm(lhsImm << rhsImm));
                            return;
                        }
                        break;
                    case RSFT:
                        op = IRBinaryOperation.IRBinaryOp.RSFT;
                        if(bothConst){
                            node.setRegValue(new Imm(lhsImm >> rhsImm));
                            return;
                        }
                        break;
                    case BITWISE_AND:
                        op = IRBinaryOperation.IRBinaryOp.BITWISE_AND;
                        if(bothConst){
                            node.setRegValue(new Imm(lhsImm & rhsImm));
                            return;
                        }
                        break;
                    case BITWISE_OR:
                        op = IRBinaryOperation.IRBinaryOp.BITWISE_OR;
                        if(bothConst){
                            node.setRegValue(new Imm(lhsImm | rhsImm));
                            return;
                        }
                        break;
                    case BITWISE_XOR:
                        op = IRBinaryOperation.IRBinaryOp.BITWISE_XOR;
                        if(bothConst){
                            node.setRegValue(new Imm(lhsImm ^ rhsImm));
                            return;
                        }
                        break;
                    default:
                        throw new Error("invalid binary operation");
                }
                VirtualReg vreg = new VirtualReg(null);
                node.setRegValue(vreg);
                curBlock.addInst(new IRBinaryOperation(curBlock , vreg , op , lhs , rhs));
                break;
            case GT:
            case LT:
            case EQ:
            case NEQ:
            case GTE:
            case LTE:
                node.getLeft().accept(this);
                node.getRight().accept(this);
                RegValue lhss = node.getLeft().getRegValue();
                RegValue rhss = node.getRight().getRegValue();
                RegValue tmp;
                int lhssImm = 0 , rhssImm = 0;
                if(lhss instanceof Imm) lhssImm = ((Imm) lhss).getValue();
                if(rhss instanceof Imm) rhssImm = ((Imm) rhss).getValue();
                boolean bc = false;
                if(lhss instanceof Imm && rhss instanceof Imm) bc = true;
                IRCmpOperation.IRCmpOp opp;
                switch(node.getOp()){
                    case GT:
                        opp = IRCmpOperation.IRCmpOp.GT;
                        if(bc){
                            if(lhssImm < rhssImm) node.setRegValue(new Imm(1));
                            else node.setRegValue(new Imm(0));
                            return;
                        }
                        if(lhss instanceof Imm){
                            tmp = rhss;
                            rhss = lhss;
                            lhss = tmp;
                            opp = IRCmpOperation.IRCmpOp.LT;
                        }
                        break;
                    case LT:
                        opp = IRCmpOperation.IRCmpOp.LT;
                        if(bc){
                            if(lhssImm > rhssImm) node.setRegValue(new Imm(1));
                            else node.setRegValue(new Imm(0));
                            return;
                        }
                        if(lhss instanceof Imm){
                            tmp = rhss;
                            rhss = lhss;
                            lhss = tmp;
                            opp = IRCmpOperation.IRCmpOp.GT;
                        }
                        break;
                    case GTE:
                        opp = IRCmpOperation.IRCmpOp.GTE;
                        if(bc){
                            if(lhssImm >= rhssImm) node.setRegValue(new Imm(1));
                            else node.setRegValue(new Imm(0));
                            return;
                        }
                        if(lhss instanceof Imm){
                            tmp = rhss;
                            rhss = lhss;
                            lhss = tmp;
                            opp = IRCmpOperation.IRCmpOp.LTE;
                        }
                        break;
                    case LTE:
                        opp = IRCmpOperation.IRCmpOp.LTE;
                        if(bc){
                            if(lhssImm <= rhssImm) node.setRegValue(new Imm(1));
                            else node.setRegValue(new Imm(0));
                            return;
                        }
                        if(lhss instanceof Imm){
                            tmp = rhss;
                            rhss = lhss;
                            lhss = tmp;
                            opp = IRCmpOperation.IRCmpOp.GTE;
                        }
                        break;
                    case EQ:
                        opp = IRCmpOperation.IRCmpOp.EQ;
                        if(bc){
                            if(lhssImm == rhssImm) node.setRegValue(new Imm(1));
                            else node.setRegValue(new Imm(0));
                            return;
                        }
                        if(lhss instanceof Imm){
                            tmp = rhss;
                            rhss = lhss;
                            lhss = tmp;
                        }
                        break;
                    case NEQ:
                        opp = IRCmpOperation.IRCmpOp.NEQ;
                        if(bc){
                            if(lhssImm != rhssImm) node.setRegValue(new Imm(1));
                            else node.setRegValue(new Imm(0));
                            return;
                        }
                        if(lhss instanceof Imm){
                            tmp = rhss;
                            rhss = lhss;
                            lhss = tmp;
                        }
                        break;
                    default:
                        throw new Error("invalid cmp operation");
                }
                VirtualReg vvreg = new VirtualReg(null);
                curBlock.addInst(new IRCmpOperation(curBlock , vvreg , opp , lhss , rhss));
                if(node.getTrue() != null){
                    curBlock.addJumpInst(new IRBranch(curBlock , vvreg , node.getTrue() , node.getFalse()));
                }
                else{
                    node.setRegValue(vvreg);
                }
                break;
            default:
                throw new Error("invalid operation");
        }
    }

    @Override
    public void visit(AssignExprNode node){
        boolean isMemOp = isMemExpr(node.getLeft());
        isAddr = isMemOp;
        node.getLeft().accept(this);
        isAddr = false;
        if(node.getRight().getType() instanceof BoolType && !(node.getRight() instanceof BoolLitExprNode)){
            node.getRight().setTrue(new BasicBlock(null , curFunc));
            node.getRight().setFalse(new BasicBlock(null , curFunc));
        }
        node.getRight().accept(this);
        RegValue dest;
        int offset;
        if(isMemOp){
            dest = node.getLeft().getAddrValue();
            offset = node.getLeft().getAddrOffset();
        }
        else{
            dest = node.getLeft().getRegValue();
            offset = 0;
        }
        processIRAssign(dest , offset , node.getRight() , 8 , isMemOp);
        node.setRegValue(node.getRight().getRegValue());
    }

    @Override
    public void visit(IdExprNode node){
        VarEntity varEntity = node.getVarEntity();
        if(varEntity.getIrReg() == null){
            ThisExprNode thisExprNode = new ThisExprNode(null);
            thisExprNode.setType(new ClassType(curClassName));
            MemberCallExprNode memberCallExprNode = new MemberCallExprNode(thisExprNode , node.getName() , null);
            memberCallExprNode.accept(this);
            if(isAddr){
                node.setAddrValue(memberCallExprNode.getAddrValue());
                node.setAddrOffset(memberCallExprNode.getAddrOffset());
            }
            else{
                node.setRegValue(memberCallExprNode.getRegValue());
                if(node.getTrue() != null){
                    curBlock.addJumpInst(new IRBranch(curBlock , node.getRegValue() , node.getTrue() , node.getFalse()));
                }
            }
            node.setMemOp(true);
        }
        else{
            node.setRegValue(varEntity.getIrReg());
            if(node.getTrue() != null){
                curBlock.addJumpInst(new IRBranch(curBlock , node.getRegValue() , node.getTrue() , node.getFalse()));
            }
        }
    }

    @Override
    public void visit(ThisExprNode node){
        VarEntity varEntity = (VarEntity) curScope.get(Scope.varKey(Scope.THIS_PARA_NAME));
        node.setRegValue(varEntity.getIrReg());
        if(node.getTrue() != null){
            curBlock.addJumpInst(new IRBranch(curBlock , node.getRegValue() , node.getTrue() , node.getFalse()));
        }
    }

    @Override
    public void visit(IntLitExprNode node){
        node.setRegValue(new Imm(node.getValue()));
    }

    @Override
    public void visit(StringLitExprNode node){
        StaticString staticString = irRoot.getStaticString(node.getValue());
        if(staticString == null){
            staticString = new StaticString(node.getValue());
            irRoot.addStaticStrings(node.getValue() , staticString);
        }
        node.setRegValue(staticString);
    }

    @Override
    public void visit(BoolLitExprNode node){
        if(node.getValue()) node.setRegValue(new Imm(1));
        else node.setRegValue(new Imm(0));
    }

    @Override
    public void visit(NullLitExprNode node){
        node.setRegValue(new Imm(0));
    }

    @Override
    public void visit(TypeNode node){
        return;
    }

    public void processArrayNew(CreatorExprNode node , VirtualReg oreg , RegValue addr , int index){
        VirtualReg vreg = new VirtualReg(null);
        ExprNode dim = node.getDims().get(index);
        boolean tmpAddr = isAddr;
        isAddr = false;
        dim.accept(this);
        isAddr = tmpAddr;
        curBlock.addInst(new IRBinaryOperation(curBlock , vreg , IRBinaryOperation.IRBinaryOp.MUL , dim.getRegValue() , new Imm(8)));
        curBlock.addInst(new IRBinaryOperation(curBlock , vreg , IRBinaryOperation.IRBinaryOp.ADD , vreg , new Imm(8)));
        curBlock.addInst(new IRHeapAlloc(curBlock , vreg , vreg));
        curBlock.addInst(new IRStore(curBlock , dim.getRegValue() , 8 , vreg , 0));
        if (index < node.getDims().size() - 1) {
            VirtualReg loop_idx = new VirtualReg(null);
            VirtualReg addrNow = new VirtualReg(null);
            curBlock.addInst(new IRMove(curBlock , loop_idx , new Imm(0)));
            curBlock.addInst(new IRMove(curBlock , addrNow , vreg));
            BasicBlock condBlock = new BasicBlock("while_cond" , curFunc);
            BasicBlock bodyBlock = new BasicBlock("while_body" , curFunc);
            BasicBlock nextBlock = new BasicBlock("while_next" , curFunc);
            curBlock.addJumpInst(new IRJump(curBlock , condBlock));
            curBlock = condBlock;
            IRCmpOperation.IRCmpOp op = IRCmpOperation.IRCmpOp.LT;
            VirtualReg cmpReg = new VirtualReg(null);
            curBlock.addInst(new IRCmpOperation(curBlock , cmpReg , op , loop_idx , dim.getRegValue()));
            curBlock.addJumpInst(new IRBranch(curBlock , cmpReg , bodyBlock , nextBlock));
            curBlock = bodyBlock;
            curBlock.addInst(new IRBinaryOperation(curBlock , addrNow , IRBinaryOperation.IRBinaryOp.ADD , addrNow , new Imm(8)));
            processArrayNew(node , null , addrNow , index + 1);
            curBlock.addInst(new IRBinaryOperation(curBlock , loop_idx , IRBinaryOperation.IRBinaryOp.ADD , loop_idx , new Imm(1)));
            curBlock.addJumpInst(new IRJump(curBlock , condBlock));
            curBlock = nextBlock;
        }
        if (index == 0) {
            curBlock.addInst(new IRMove(curBlock , oreg , vreg));
        } else {
            curBlock.addInst(new IRStore(curBlock , vreg, 8, addr, 0));
        }
    }

    @Override
    public void visit(CreatorExprNode node){
        VirtualReg vreg = new VirtualReg(null);
        Type type = node.getNewType().getType();
        if(type instanceof ClassType){
            String className = ((ClassType) type).getName();
            ClassEntity classEntity = (ClassEntity) curScope.get(Scope.classKey(className));
            curBlock.addInst(new IRHeapAlloc(curBlock , vreg , new Imm(classEntity.getMemSize())));
            String funcName = String.format("__member_%s_%s" , className , className);
            IRFunc irFunc = irRoot.getIRFunc(funcName);
            if(irFunc != null){
                List<RegValue> args = new ArrayList<>();
                args.add(vreg);
                curBlock.addInst(new IRFunctionCall(curBlock , irFunc , args , null));
            }
        }
        else{
            processArrayNew(node , vreg , null , 0);
        }
        node.setRegValue(vreg);
    }
}