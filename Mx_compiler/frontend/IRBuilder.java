package Mx_compiler.frontend;

import Mx_compiler.IR.*;
import Mx_compiler.Scope.ClassEntity;
import Mx_compiler.Scope.FuncEntity;
import Mx_compiler.Scope.Scope;
import Mx_compiler.Scope.VarEntity;
import Mx_compiler.node.*;
import Mx_compiler.type.*;

import java.lang.reflect.Member;
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
        //TODO
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
                if(!isFuncArg)  curBlock.addInst(new IRMove(curBlock, virtualReg, new Imm(0)));
                else{
                    if(node.getExpr().getType() instanceof BoolType && !(node.getExpr() instanceof BoolLitExprNode)){
                        node.getExpr().setTrue(new BasicBlock(null , curFunc));
                        node.getExpr().setFalse(new BasicBlock(null , curFunc));
                    }
                    node.getExpr().accept(this);
                    //TODO
                }
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
        curClassName = null;
    }


    @Override
    public void visit(FuncDeclNode node) {
        String funcName = node.getName();
        if (curClassName != null) {
            funcName = String.format("_member_%s_%s", curClassName, funcName);
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
            node.accept(this);
        }
        isFuncArg = false;
        curScope = lastScope;
        if(node.getName().equals("main")){
            curBlock.addInst(new IRFunctionCall(curBlock , irRoot.getIRFunc("_init_func")));
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
        //TODO
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
    public void visit(IfStmtNode node) {
        BasicBlock thenBlock, elseBlock = null, nextBlock;
        if (node.getElseStmt() != null) {
            thenBlock = new BasicBlock("_if_then", curFunc);
            elseBlock = new BasicBlock("_if_else", curFunc);
            nextBlock = new BasicBlock("_if_next", curFunc);
            node.getCond().setTrue(thenBlock);
            node.getCond().setFalse(elseBlock);
            node.getCond().accept(this);
            if (node.getCond().getType() instanceof BoolType) {
                curBlock.addJumpInst(new IRBranch(curBlock, node.getCond().getRegValue(), node.getCond().getTrue(), node.getCond().getFalse()));
            }
        } else {
            thenBlock = new BasicBlock("_if_then", curFunc);
            nextBlock = new BasicBlock("_if_next", curFunc);
            node.getCond().setTrue(thenBlock);
            node.getCond().setFalse(nextBlock);
            node.getCond().accept(this);
            if (node.getCond().getType() instanceof BoolType) {
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
        curBlock = condBlock;
        node.getCond().accept(this);
        node.getCond().setTrue(bodyBlock);
        node.getCond().setFalse(nextBlock);
        if (node.getCond().getType() instanceof BoolType) {
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
        BasicBlock condBlock = new BasicBlock("_for_cond", curFunc);
        BasicBlock stepBlock = new BasicBlock("_for_step", curFunc);
        BasicBlock bodyBlock = new BasicBlock("_for_body", curFunc);
        BasicBlock nextBlock = new BasicBlock("_for_next", curFunc);
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
            if (node.getCond().getType() instanceof BoolType) {
                curBlock.addJumpInst(new IRBranch(curBlock, node.getCond().getRegValue(), node.getCond().getTrue(), node.getCond().getFalse()));
            }
        }
        curBlock = bodyBlock;
        node.getBody().accept(this);
        if (!curBlock.isJump()) {
            curBlock.addJumpInst(new IRJump(curBlock, stepBlock));
        }
        if (node.getStep() != null) {
            curBlock = stepBlock;
            node.getStep().accept(this);
            curBlock.addJumpInst(new IRJump(curBlock, condBlock));
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
                //TODO
                curBlock.addJumpInst(new IRReturn(curBlock , vreg));
            }
            else{
                node.getExpr().accept(this);
                curBlock.addJumpInst(new IRReturn(curBlock , node.getExpr().getRegValue()));
            }
        }
    }

    private boolean isMemExpr(ExprNode node){
        if(node instanceof SubscriptExprNode || node instanceof MemberCallExprNode){
            return true;
        }
        return false;
    }

    @Override
    public void visit(SuffixExprNode node) {
        boolean isMemExpr = isMemExpr(node.getExpr());
        boolean tmpAddr = isAddr;
        isAddr = false;
        node.getExpr().accept(this);
        VirtualReg vreg = new VirtualReg(null);
        curBlock.addInst(new IRMove(curBlock , vreg , node.getExpr().getRegValue()));
        IRBinaryOperation.IRBinaryOp op;
        if(node.getOp() == SuffixExprNode.SuffixOps.SUFFIX_INC){
            op = IRBinaryOperation.IRBinaryOp.ADD;
        }
        else op = IRBinaryOperation.IRBinaryOp.SUB;
        curBlock.addInst(new IRBinaryOperation(curBlock , (IRReg)node.getExpr().getRegValue() , op , node.getExpr().getRegValue() , new Imm(1)));
        isAddr = tmpAddr;
    }

    @Override
    public void visit(FuncCallExprNode node){
        FuncEntity funcEntity = node.getFuncEntity();
        String funcName = funcEntity.getName();
        List<RegValue>args = new ArrayList<>();
        ExprNode thisExpr = null;
        if(funcEntity.isMember()){
            if(node.getFunc() instanceof MemberCallExprNode){
                thisExpr = ((MemberCallExprNode)(node.getFunc())).getExpr();
            }
            else{
                thisExpr = new ThisExprNode(null);
                thisExpr.setType(new ClassType(curClassName));
            }
            thisExpr.accept(this);
            String className;
            if(thisExpr.getType() instanceof ClassType){
                className = ((ClassType) thisExpr.getType()).getName();
            }
            else if(thisExpr.getType() instanceof ArrayType){
                className = Scope.ARRAY_CLASS_NAME;
            }
            else{
                className = Scope.STRING_CLASS_NAME;
            }
            funcName = String.format("_member_%s_%s" , className , funcName);
            args.add(thisExpr.getRegValue());
            if(funcEntity.isBuiltIn()){
                //TODO
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
    public void visit(MemberCallExprNode node){
        node.getExpr().accept(this);
        RegValue addr = node.getExpr().getRegValue();
        String name = ((ClassType)node.getExpr().getType()).getName();
        ClassEntity classEntity = (ClassEntity) curScope.getCheck(name , Scope.classKey(name));
        VarEntity varEntity = (VarEntity) classEntity.getScope().getSelf(Scope.varKey(node.getId()));
        VirtualReg vreg = new VirtualReg(null);
        node.setRegValue(vreg);
        curBlock.addInst(new IRLoad(curBlock , vreg , varEntity.getType().getVarSize() , addr , varEntity.getAddrOffset()));
        curBlock.addJumpInst(new IRBranch(curBlock , node.getRegValue() , node.getTrue() , node.getFalse()));
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
        //TODO
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
                node.setMemOp(true);
            }
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
}