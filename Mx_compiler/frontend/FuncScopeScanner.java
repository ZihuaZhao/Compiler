package Mx_compiler.frontend;

import Mx_compiler.Scope.*;
import Mx_compiler.node.*;
import Mx_compiler.type.*;

public class FuncScopeScanner extends BasicScopeScanner{
    private Scope globalScope , curScope;
    private Type curReturnType;
    private ClassType curClassType;
    private FuncEntity curFuncEntity;
    private int loopCt;

    public FuncScopeScanner(Scope scope){
        this.globalScope = scope;
    }

    public Scope getGlobalScope(){
        return globalScope;
    }

    @Override
    public void visit(ProgramNode node){
        curScope = globalScope;
        loopCt = 0;
        for(DeclNode declNode : node.getDeclarators()){
            if(declNode instanceof VarDeclNode) declNode.accept(this);
            else if(declNode instanceof ClassDeclNode) declNode.accept(this);
            else if(declNode instanceof FuncDeclNode) declNode.accept(this);
            else throw new Error("invalid declNode");
        }
    }

    @Override
    public void visit(VarDeclNode node){
        if(node.getType().getType() instanceof ClassType){
            String className = ((ClassType) node.getType().getType()).getName();
            curScope.checkContainsKey(className , Scope.classKey(className));
        }
        checkVarDecl(node);
        VarEntity entity = new VarEntity(node.getName() , node.getType().getType());
        curScope.putCheck(node.getName() , Scope.varKey(node.getName()) , entity);
    }

    @Override
    public void visit(FuncDeclNode node){
        FuncEntity entity = (FuncEntity)curScope.getCheck(node.location() , node.getName() , Scope.funcKey(node.getName()));
        if(entity.getReturnType() instanceof ClassType){
            curScope.checkContainsKey(((ClassType) entity.getReturnType()).getName() , Scope.classKey(((ClassType) entity.getReturnType()).getName()));
        }
        curReturnType = entity.getReturnType();
        node.getFuncBody().initScope(curScope);
        curScope = node.getFuncBody().getScope();
        if(curClassType != null){
            String key = Scope.varKey(Scope.THIS_PARA_NAME);
            curScope.putCheck(Scope.THIS_PARA_NAME , key , new VarEntity(Scope.THIS_PARA_NAME , curClassType));
            if(node.isConstruct() && !(node.getName().equals(curClassType.getName()))){
                throw new Error("func should have a return type");
            }
        }
        for(VarDeclNode param : node.getParamList()){
            param.accept(this);
        }
        curScope = curScope.getParent();
        node.getFuncBody().accept(this);
    }

    @Override
    public void visit(ClassDeclNode node){
        ClassEntity entity = (ClassEntity) curScope.getCheck(node.location() , node.getName() , Scope.classKey(node.getName()));
        curScope = entity.getScope();
        curClassType = (ClassType) entity.getType();
        for(FuncDeclNode declNode : node.getFuncMember()){
            declNode.accept(this);
        }
        curScope = curScope.getParent();
        curClassType = null;
    }

    @Override
    public void visit(BlockStmtNode node){
        curScope = node.getScope();
        String name , key;
        VarEntity varEntity;
        for(Node n : node.getstmts()){
            if(n instanceof StmtNode){
                if(n instanceof BlockStmtNode){
                    ((BlockStmtNode) n).initScope(curScope);
                }
                n.accept(this);
            }
            else if(n instanceof VarDeclNode){
                n.accept(this);
            }
            else throw new Error("invalid node in block");
        }
        curScope = curScope.getParent();
    }

    @Override
    public void visit(ExprStmtNode node){
        if(node.getExpr() != null)  node.getExpr().accept(this);
    }

    @Override
    public void visit(IfStmtNode node){
        node.getCond().accept(this);
        if(!(node.getCond().getType() instanceof BoolType)){
            throw new Error("if stmt cond should be bool type");
        }
        if(node.getBody() != null){
            ((BlockStmtNode)node.getBody()).initScope(curScope);
            node.getBody().accept(this);
        }
        if(node.getElseStmt() != null){
            ((BlockStmtNode)node.getElseStmt()).initScope(curScope);
            node.getElseStmt().accept(this);
        }
    }

    @Override
    public void visit(WhileStmtNode node){
        loopCt++;
        node.getCond().accept(this);
        if(!(node.getCond().getType() instanceof BoolType)){
            throw new Error("while cond stmt should be bool type");
        }
        if(node.getBody() != null){
            ((BlockStmtNode)node.getBody()).initScope(curScope);
            node.getBody().accept(this);
        }
        loopCt--;
    }

    @Override
    public void visit(ForStmtNode node){
        loopCt++;
        if(node.getInit() != null){
            node.getInit().accept(this);
        }
        if(node.getCond() != null){
            node.getCond().accept(this);
            if(!(node.getCond().getType() instanceof BoolType)){
                throw new Error("for cond stmt should be bool type");
            }
        }
        if(node.getStep() != null){
            node.getStep().accept(this);
        }
        if(node.getBody() != null){
            ((BlockStmtNode)node.getBody()).initScope(curScope);
            node.getBody().accept(this);
        }
        loopCt--;
    }

    @Override
    public void visit(ContinueStmtNode node){
        if(loopCt <= 0) throw new Error("continue should be in loop");
    }

    @Override
    public void visit(BreakStmtNode node){
        if(loopCt <= 0) throw new Error("break should be in loop");
    }

    @Override
    public void visit(ReturnStmtNode node){
        boolean invalidReturnType = false;
        if(node.getExpr() == null){
            if(!(curReturnType == null || curReturnType instanceof VoidType)){
                invalidReturnType = true;
            }
        }
        else{
            node.getExpr().accept(this);
            if(node.getExpr().getType() == null || node.getExpr().getType() instanceof VoidType){
                invalidReturnType = true;
            }
            else if(node.getExpr().getType() instanceof NullType){
                invalidReturnType = !(curReturnType instanceof ClassType || curReturnType instanceof ArrayType);
            }
            else if(!node.getExpr().getType().equals(curReturnType)){
                invalidReturnType = true;
            }
        }
        if(invalidReturnType){
            if(curReturnType == null || curReturnType instanceof VoidType) throw new Error("return should have no value");
            else throw new Error("return type error");
        }
    }

    @Override
    public void visit(SuffixExprNode node){
        node.getExpr().accept(this);
        if(!(node.getExpr().getType() instanceof IntType)){
            throw new Error("suffix should be on int type");
        }
        if(!(node.getExpr().isLeftType())){
            throw new Error("suffix should be on right");
        }
        node.setType(IntType.getType());
        node.setLeftValue(false);
    }

    @Override
    public void visit(FuncCallExprNode node){
        node.getFunc().accept(this);
        if(!(node.getFunc().getType() instanceof FunctionType)){
            throw new Error("type not callable");
        }
        FuncEntity funcEntity = curFuncEntity;
        node.setFuncEntity(funcEntity);
        int paraNum = funcEntity.getParams().size();
        int fir = 0;
        if(funcEntity.isMember()) fir = 1;
        if(paraNum - fir != node.getArgs().size()){
            throw new Error("func arg num is not right");
        }
        boolean invalidType;
        for(int i = 0 ; i < paraNum - fir ; ++ i){
            node.getArgs().get(i).accept(this);
            if(node.getArgs().get(i).getType() instanceof VoidType) invalidType = true;
            else if(node.getArgs().get(i).getType() instanceof NullType) invalidType = !(funcEntity.getParams().get(i + fir).getType() instanceof ClassType || funcEntity.getParams().get(i + fir).getType() instanceof ArrayType);
            else invalidType = !(funcEntity.getParams().get(i + fir).getType().equals(node.getArgs().get(i).getType()));
            if(invalidType){
                throw new Error("func arg invalid type");
            }
        }
        node.setType(funcEntity.getReturnType());
        node.setLeftValue(false);
    }

    @Override
    public void visit(SubscriptExprNode node){
        node.getArr().accept(this);
        if(!(node.getArr().getType() instanceof ArrayType)){
            throw new Error("type not subscriptable");
        }
        node.getSub().accept(this);
        if(!(node.getSub().getType() instanceof IntType)){
            throw new Error("type not sub");
        }
        node.setLeftValue(true);
        node.setType(((ArrayType) node.getArr().getType()).getBaseType());
    }

    @Override
    public void visit(MemberCallExprNode node){
        node.getExpr().accept(this);
        String className;
        Entity memberEntity;
        if(node.getExpr().getType() instanceof ClassType){
            className = ((ClassType) node.getExpr().getType()).getName();
        }
        else if(node.getExpr().getType() instanceof StringType){
            className = Scope.STRING_CLASS_NAME;
        }
        else if(node.getExpr().getType() instanceof ArrayType){
            className = Scope.ARRAY_CLASS_NAME;
        }
        else throw new Error("type invalid in memcall");
        ClassEntity classEntity = (ClassEntity) curScope.getCheck(className , Scope.classKey(className));
        if(classEntity.getScope().containsSelfExactKey(Scope.varKey(node.getId())))
        memberEntity = classEntity.getScope().getSelf(Scope.varKey(node.getId()));
        else{
            memberEntity = classEntity.getScope().getSelfCheck(Scope.funcKey(node.getId()));
            curFuncEntity = (FuncEntity) memberEntity;
        }
        node.setLeftValue(true);
        node.setType(memberEntity.getType());
    }

    @Override
    public void visit(PrefixExprNode node){
        node.getExpr().accept(this);
        switch(node.getOp()){
            case PREFIX_INC:
            case PREFIX_DEC:
                if(!(node.getExpr().getType() instanceof IntType)) throw new Error("operator prefix_dec cannot apply to not int");
                if(!(node.getExpr().isLeftType())) throw new Error("left value only");
                node.setType(IntType.getType());
                node.setLeftValue(true);
                break;
            case POS:
            case NEG:
            case BITWISE_NOT:
                if(!(node.getExpr().getType() instanceof IntType)) throw new Error("operator bitwise_not can only apply to int");
                node.setType(IntType.getType());
                node.setLeftValue(false);
                break;
            case LOG_NOT:
                if(!(node.getExpr().getType() instanceof BoolType)) throw new Error("operator log_not can only apply to bool");
                node.setType(BoolType.getType());
                node.setLeftValue(false);
                break;
            default: throw new Error("invalid prefix stmt");
        }
    }

    @Override
    public void visit(CreatorExprNode node){
        if(node.getNewType().getType() instanceof ClassType){
            curScope.getCheck(((ClassType) node.getNewType().getType()).getName() , Scope.classKey(((ClassType) node.getNewType().getType()).getName()));
        }
        if(node.getDims() != null){
            for(ExprNode exprNode : node.getDims()){
                exprNode.accept(this);
                if(!(exprNode.getType() instanceof IntType)) throw new Error("creator size should be int");
            }
        }
        node.setLeftValue(false);
        node.setType(node.getNewType().getType());
    }

    @Override
    public void visit(BinaryExprNode node){
        node.getLeft().accept(this);
        node.getRight().accept(this);
        switch(node.getOp()){
            case MUL:
            case DIV:
            case MOD:
            case ADD:
                if(node.getLeft().getType() instanceof StringType && node.getRight().getType() instanceof StringType){
                    node.setType(StringType.getType());
                    node.setLeftValue(false);
                    break;
                }
            case SUB:
            case LSFT:
            case RSFT:
            case BITWISE_AND:
            case BITWISE_OR:
            case BITWISE_XOR:
                if(!(node.getLeft().getType() instanceof IntType)) throw new Error("lhs of bit_xor should be int");
                if(!(node.getRight().getType() instanceof IntType)) throw new Error("rhs of bit_xor should be int");
                node.setLeftValue(false);
                node.setType(IntType.getType());
                break;
            case GT:
            case LT:
            case GTE:
            case LTE:
                if(!(node.getLeft().getType() instanceof IntType || node.getLeft().getType() instanceof StringType))
                    throw new Error("lhs of lte should be int or string");
                if(!(node.getRight().getType() instanceof IntType || node.getRight().getType() instanceof StringType))
                    throw new Error("rhs of lte should be int or string");
                node.setType(IntType.getType());
                node.setLeftValue(false);
            case EQ:
            case NEQ:
                boolean invalid;
                if(node.getLeft().getType() instanceof VoidType || node.getRight().getType() instanceof VoidType) invalid = true;
                else if(node.getLeft().getType().equals(node.getRight().getType())) invalid = false;
                else if(node.getLeft().getType() instanceof NullType)
                    invalid = !(node.getRight().getType() instanceof ClassType || node.getRight().getType() instanceof ArrayType);
                else if(node.getRight().getType() instanceof NullType)
                    invalid = !(node.getLeft().getType() instanceof ClassType || node.getLeft().getType() instanceof ArrayType);
                else invalid = true;
                if(invalid) throw new Error("neq get wrong");
                node.setLeftValue(false);
                node.setType(BoolType.getType());
                break;
            case LOG_OR:
            case LOG_AND:
                if(!(node.getLeft().getType() instanceof BoolType)) throw new Error("log_and should have lhs bool");
                if(!(node.getRight().getType() instanceof BoolType)) throw new Error("log_and should have rhs bool");
                node.setType(BoolType.getType());
                node.setLeftValue(false);
                break;
            default: throw new Error("invalid bin stmt");
        }
    }

    @Override
    public void visit(IdExprNode node){
        String name = node.getName();
        Entity entity = curScope.getVarFuncCheck(name);
        if(entity instanceof VarEntity){
            node.setVarEntity((VarEntity) entity);
            node.setLeftValue(true);
        }
        else if(entity instanceof FuncEntity){
            curFuncEntity = (FuncEntity) entity;
            node.setLeftValue(false);
        }
        else throw new Error("invalid entity type for id");
        node.setType(entity.getType());
    }

    @Override
    public void visit(AssignExprNode node){
        node.getLeft().accept(this);
        node.getRight().accept(this);
        if(!(node.getLeft().isLeftType())) throw new Error("lhs of = should be left value");
        boolean invalid;
        if(node.getLeft().getType() instanceof VoidType || node.getRight().getType() instanceof VoidType) invalid = true;
        else if(node.getLeft().getType().equals(node.getRight().getType())) invalid = false;
        else if(node.getRight().getType() instanceof NullType)
            invalid = !(node.getLeft().getType() instanceof ClassType || node.getLeft().getType() instanceof ArrayType);
        else invalid = true;
        if(invalid) throw new Error("= get wrong lhs and rhs");
        node.setLeftValue(false);
        node.setType(node.getLeft().getType());
    }

    @Override
    public void visit(ThisExprNode node){
        Entity entity = curScope.getVarFuncCheck(Scope.THIS_PARA_NAME);
        if(!(entity instanceof VarEntity)) throw new Error("invalid entity type in this");
        node.setLeftValue(false);
        node.setType(entity.getType());
    }

    @Override
    public void visit(IntLitExprNode node){
        node.setLeftValue(false);
        node.setType(IntType.getType());
    }

    @Override
    public void visit(StringLitExprNode node){
        node.setLeftValue(false);
        node.setType(StringType.getType());
    }

    @Override
    public void visit(BoolLitExprNode node){
        node.setLeftValue(false);
        node.setType(BoolType.getType());
    }

    @Override
    public void visit(NullLitExprNode node){
        node.setLeftValue(false);
        node.setType(NullType.getType());
    }
}
