package Mx_compiler.visitor;

import Mx_compiler.IR.*;

public interface IRVisitor {
    void visit(IRRoot node);
    void visit(StaticVar node);
    void visit(VirtualReg node);
    void visit(Imm node);
    void visit(IRStore node);
    void visit(IRLoad node);
    void visit(IRBinaryOperation node);
    void visit(StaticString node);
    void visit(IRFunc node);
    void visit(BasicBlock node);
    void visit(IRBranch node);
    void visit(IRJump node);
    void visit(IRReturn node);
    void visit(IRUnaryOperation node);
    void visit(IRCmpOperation node);
    void visit(IRMove node);
    void visit(IRFunctionCall node);
}
