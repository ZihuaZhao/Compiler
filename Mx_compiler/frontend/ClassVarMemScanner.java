package Mx_compiler.frontend;

import Mx_compiler.Scope.ClassEntity;
import Mx_compiler.Scope.Scope;
import Mx_compiler.Scope.VarEntity;
import Mx_compiler.node.ClassDeclNode;
import Mx_compiler.node.DeclNode;
import Mx_compiler.node.ProgramNode;
import Mx_compiler.node.VarDeclNode;
import Mx_compiler.type.ClassType;

public class ClassVarMemScanner extends BasicScopeScanner {
    private Scope globalScope , classScope;
    private String className;

    public ClassVarMemScanner(Scope globalScope){
        this.globalScope = globalScope;
    }

    public Scope getGlobalScope(){
        return globalScope;
    }

    @Override
    public void visit(ProgramNode node){
        for(DeclNode declNode : node.getDeclarators()){
            if(!(declNode instanceof ClassDeclNode)) continue;
            declNode.accept(this);
        }
    }

    @Override
    public void visit(ClassDeclNode node){
        ClassEntity entity  = (ClassEntity) globalScope.getCheck(node.location() , node.getName() , Scope.classKey(node.getName()));
        classScope = entity.getScope();
        className = entity.getName();
        for(VarDeclNode varDeclNode : node.getVarMember()){
            varDeclNode.accept(this);
        }
    }

    @Override
    public void visit(VarDeclNode node){
        if(node.getType().getType() instanceof ClassType){
            String newName = ((ClassType) node.getType().getType()).getName();
            classScope.checkContainsKey(newName , Scope.classKey(newName));
        }
        checkVarDecl(node);
        VarEntity entity = new VarEntity(node.getName() , node.getType().getType() , className);
        classScope.putCheck(node.getName() , Scope.varKey(node.getName()) , entity);
    }
}
