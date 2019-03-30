package Mx_compiler.frontend;

import Mx_compiler.Scope.*;
import Mx_compiler.node.*;
import Mx_compiler.type.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GlobalScopePreScanner extends BasicScopeScanner{
    private Scope scope = new Scope();

    public Scope getScope(){
        return scope;
    }

    private void putBuiltInFunc(Scope thisScope , String name , List<VarEntity> params , Type returnType){
        String k = Scope.funcKey(name);
        FuncEntity entity = new FuncEntity(name , new FunctionType(name));
        entity.setParams(params);
        entity.setReturnType(returnType);
        entity.setBuiltIn(true);
        if(!thisScope.isTop()){
            entity.setMember(true);
        }
        thisScope.putCheck(name , k , entity);
    }

    private void putBuiltInFunc(){
        putBuiltInFunc(scope,"print", Collections.singletonList(new VarEntity("str", StringType.getType())), VoidType.getType());
        putBuiltInFunc(scope,"println", Collections.singletonList(new VarEntity("str", StringType.getType())), VoidType.getType());
        putBuiltInFunc(scope,"getString", new ArrayList<>(), StringType.getType());
        putBuiltInFunc(scope,"getInt", new ArrayList<>(), IntType.getType());
        putBuiltInFunc(scope,"toString", Collections.singletonList(new VarEntity("i", IntType.getType())), StringType.getType());
        String stringKey = Scope.classKey(Scope.STRING_CLASS_NAME);
        ClassEntity stringEntity = new ClassEntity(Scope.STRING_CLASS_NAME, new ClassType(Scope.STRING_CLASS_NAME), scope);
        putBuiltInFunc(stringEntity.getScope(), "length", Arrays.asList(new VarEntity(Scope.THIS_PARA_NAME, StringType.getType())), IntType.getType());
        putBuiltInFunc(stringEntity.getScope(), "substring", Arrays.asList(new VarEntity(Scope.THIS_PARA_NAME, StringType.getType()), new VarEntity("left", IntType.getType()), new VarEntity("right", IntType.getType())), StringType.getType());
        putBuiltInFunc(stringEntity.getScope(), "parseInt", Arrays.asList(new VarEntity(Scope.THIS_PARA_NAME, StringType.getType())), IntType.getType());
        putBuiltInFunc(stringEntity.getScope(), "ord", Arrays.asList(new VarEntity(Scope.THIS_PARA_NAME, StringType.getType()), new VarEntity("pos", IntType.getType())), IntType.getType());
        scope.putCheck(Scope.STRING_CLASS_NAME, stringKey, stringEntity);
        String arrayKey = Scope.classKey(Scope.ARRAY_CLASS_NAME);
        ClassEntity arrayEntity = new ClassEntity(Scope.ARRAY_CLASS_NAME, new ClassType(Scope.ARRAY_CLASS_NAME), scope);
        putBuiltInFunc(arrayEntity.getScope(), "size", Arrays.asList(new VarEntity(Scope.THIS_PARA_NAME, new ArrayType(null))), IntType.getType());
        scope.putCheck(Scope.ARRAY_CLASS_NAME, arrayKey, arrayEntity);
    }

    private void checkMainFunc(FuncEntity mainFunc){
        if(mainFunc == null) throw new Error("main not found");
        if(!(mainFunc.getReturnType() instanceof IntType)) throw new Error("main should be int");
        if(!mainFunc.getParams().isEmpty()) throw new Error("main should have no params");
    }

    @Override
    public void visit(ProgramNode node){
        putBuiltInFunc();
        for(DeclNode declNode : node.getDeclarators()){
            if(declNode instanceof VarDeclNode) continue;
            declNode.accept(this);
        }
        checkMainFunc((FuncEntity) scope.get(Scope.funcKey("main")));
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
