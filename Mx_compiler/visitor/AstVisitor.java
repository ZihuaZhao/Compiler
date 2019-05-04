package Mx_compiler.visitor;

import Mx_compiler.node.*;

public interface AstVisitor{
    void visit(ProgramNode node);
    void visit(VarDeclNode node);
    void visit(TypeNode node);
    void visit(BlockStmtNode node);
    void visit(ClassBuildNode node);
    void visit(ClassDeclNode node);
    void visit(ExprNode node);
    void visit(FuncDeclNode node);
    void visit(ExprStmtNode node);
    void visit(IfStmtNode node);
    void visit(ForStmtNode node);
    void visit(WhileStmtNode node);
    void visit(ReturnStmtNode node);
    void visit(BreakStmtNode node);
    void visit(ContinueStmtNode node);
    void visit(PrefixExprNode node);
    void visit(SuffixExprNode node);
    void visit(BinaryExprNode node);
    void visit(FuncCallExprNode node);
    void visit(SubscriptExprNode node);
    void visit(MemberCallExprNode node);
    void visit(IntLitExprNode node);
    void visit(BoolLitExprNode node);
    void visit(StringLitExprNode node);
    void visit(NullLitExprNode node);
    void visit(AssignExprNode node);
    void visit(CreatorExprNode node);
    void visit(IdExprNode node);
    void visit(ThisExprNode node);
}
