package Mx_compiler.frontend;

import Mx_compiler.ast.AstVisitor;
import Mx_compiler.node.*;
import Mx_compiler.type.ArrayType;
import Mx_compiler.type.ClassType;
import Mx_compiler.type.NullType;
import Mx_compiler.type.VoidType;

public class BasicScopeScanner implements AstVisitor{
    public void checkVarDecl(VarDeclNode node){
        if(node.getExpr() != null){
            node.getExpr().accept(this);
            boolean invalid;
            if(node.getType().getType() instanceof VoidType || node.getExpr().getType() instanceof VoidType) invalid = true;
            else if(node.getType().getType().equals(node.getExpr().getType())) invalid = false;
            else if(node.getExpr().getType() instanceof NullType) invalid = !(node.getType().getType() instanceof ClassType || node.getType().getType() instanceof  ArrayType);
            else invalid = true;
            if(invalid) throw new Error("invalid var init value");

        }
    }

    @Override
    public void visit(ProgramNode node){

    }

    @Override
    public void visit(VarDeclNode node){

    }

    @Override
    public void visit(TypeNode node){

    }

    @Override
    public void visit(BlockStmtNode node){

    }

    @Override
    public void visit(ClassBuildNode node){

    }

    @Override
    public void visit(ClassDeclNode node){

    }

    @Override
    public void visit(ExprNode node){

    }

    @Override
    public void visit(FuncDeclNode node){

    }

    @Override
    public void visit(ExprStmtNode node){

    }

    @Override
    public void visit(IfStmtNode node){

    }

    @Override
    public void visit(ForStmtNode node){

    }

    @Override
    public void visit(WhileStmtNode node){

    }

    @Override
    public void visit(ReturnStmtNode node){

    }

    @Override
    public void visit(BreakStmtNode node){

    }

    @Override
    public void visit(ContinueStmtNode node){

    }

    @Override
    public void visit(PrefixExprNode node){

    }

    @Override
    public void visit(SuffixExprNode node){

    }

    @Override
    public void visit(BinaryExprNode node){

    }

    @Override
    public void visit(FuncCallExprNode node){

    }

    @Override
    public void visit(SubscriptExprNode node){

    }

    @Override
    public void visit(MemberCallExprNode node){

    }

    @Override
    public void visit(IntLitExprNode node){

    }

    @Override
    public void visit(BoolLitExprNode node){

    }

    @Override
    public void visit(StringLitExprNode node){

    }

    @Override
    public void visit(NullLitExprNode node){

    }

    @Override
    public void visit(AssignExprNode node){

    }

    @Override
    public void visit(CreatorExprNode node){

    }

    @Override
    public void visit(IdExprNode node){

    }

    @Override
    public void visit(ThisExprNode node){

    }
}
