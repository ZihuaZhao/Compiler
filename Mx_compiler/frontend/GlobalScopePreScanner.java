package Mx_compiler.frontend;

import Mx_compiler.Scope.ClassEntity;
import Mx_compiler.Scope.Entity;
import Mx_compiler.Scope.FuncEntity;
import Mx_compiler.Scope.Scope;
import Mx_compiler.node.*;

public class GlobalScopePreScanner extends BasicScopeScanner{
    private Scope scope = new Scope();

    public Scope getScope(){
        return scope;
    }

    private void putBuiltInFunc(){

    }

    @Override
    public void visit(ProgramNode node){
        putBuiltInFunc();
        for(DeclNode declNode : node.getDeclarators()){
            if(declNode instanceof VarDeclNode) continue;
            declNode.accept(this);
        }
    }

    @Override
    public void visit(FuncDeclNode node){
        String key = Scope.funcKey(node.getName());
        Entity entity = new FuncEntity(node);
        scope.putCheck(node.getName() , key , entity);
    }

    @Override
    public void visit(ClassDeclNode node){
        String key = Scope.classKey(node.getName());
        Entity entity = new ClassEntity(node , scope);
        scope.putCheck(node.getName() , key , entity);
    }
}
