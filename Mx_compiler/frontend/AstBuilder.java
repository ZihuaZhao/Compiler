package Mx_compiler.frontend;
import Mx_compiler.node.*;
import Mx_compiler.type.*;
import Mx_compiler.utility.Location;
import Mx_grammar.MXBaseVisitor;
import Mx_grammar.MXParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends MXBaseVisitor<Node> {
    private TypeNode typeForVar;

    @Override
    public Node visitProgram(MXParser.ProgramContext ctx) {
        List<DeclNode> declarators = new ArrayList<>();
        if(ctx.context() != null){
            for(ParserRuleContext context : ctx.context()){
                Node declarator = visit(context);
                declarators.add((DeclNode) declarator);
            }
        }
        return new ProgramNode(declarators , Location.fromCtx(ctx));
    }

    @Override
    public Node visitContext(MXParser.ContextContext ctx){
        if(ctx.classDeclField() != null) {
            return visit(ctx.classDeclField());
        }
        else if(ctx.varDeclField() != null){
            return visit(ctx.varDeclField());
        }
        else{
            return visit(ctx.funcDeclField());
        }
    }

    @Override
    public Node visitVarDeclField(MXParser.VarDeclFieldContext ctx){
        typeForVar = (TypeNode) visit(ctx.type());
        return visit(ctx.varField());
    }

    @Override
    public Node visitVarField(MXParser.VarFieldContext ctx){
        String name = ctx.ID().getText();
        if(ctx.varInitField() != null){
            ExprNode init = (ExprNode) visit(ctx.varInitField().expr());
            return new VarDeclNode(typeForVar , name , init);
        }
        else{
            return new VarDeclNode(typeForVar , name);
        }
    }

    @Override
    public Node visitFuncDeclField(MXParser.FuncDeclFieldContext ctx){
        TypeNode funcType;
        if(ctx.type() != null){
            funcType = (TypeNode) visit(ctx.type());
        }
        else{
            funcType = new TypeNode(VoidType.getType());
        }
        String name = ctx.funcField().funcName().ID().getText();
        List<VarDeclNode> params = new ArrayList<>();
        Node paramDecl;
        if(ctx.funcField().paramField() != null){
            for(ParserRuleContext param : ctx.funcField().paramField().param()){
                paramDecl = visit(param);
                params.add((VarDeclNode) paramDecl);
            }
        }
        BlockStmtNode funcBody = (BlockStmtNode) visit(ctx.funcField().funcBody().block());
        return new FuncDeclNode(funcType , name , params , funcBody , Location.fromCtx(ctx));
    }

    @Override
    public Node visitClassDeclField(MXParser.ClassDeclFieldContext ctx){
        String name = ctx.ID().getText();
        List<VarDeclNode> varMember = new ArrayList<>();
        List<FuncDeclNode> funcMember = new ArrayList<>();
        ClassBuildNode classBuild = null;
        Node mem;
        if(ctx.classBody() != null){
            for(ParserRuleContext classBody : ctx.classBody()){
                mem = visit(classBody);
                if(mem instanceof VarDeclNode){
                    varMember.add((VarDeclNode)mem);
                }
                else if(mem instanceof FuncDeclNode){
                    funcMember.add((FuncDeclNode)mem);
                }
                else if(mem instanceof ClassBuildNode){
                    if(!(((ClassBuildNode) mem).getName().equals(name))){
                        throw new Error("invalid class builder");
                    }
                    classBuild = (ClassBuildNode)mem;
                }
            }
        }
        return new ClassDeclNode(name , varMember , funcMember, classBuild , Location.fromCtx(ctx));
    }

    @Override
    public Node visitClassBody(MXParser.ClassBodyContext ctx){
        if(ctx.funcDeclField() != null){
            return visit(ctx.funcDeclField());
        }
        else if(ctx.varDeclField() != null){
            return visit(ctx.varDeclField());
        }
        else{
            return visit(ctx.classBuildField());
        }
    }

    @Override
    public Node visitClassBuildField(MXParser.ClassBuildFieldContext ctx){
        String name = ctx.ID().getText();
        BlockStmtNode body = (BlockStmtNode) visit(ctx.funcBody().block());
        for(Node node : body.getstmts()){
            if(node instanceof ReturnStmtNode && ((ReturnStmtNode) node).getExpr() != null) throw new Error("there shouldn't be return in class build");
        }
        return new ClassBuildNode(name , body , Location.fromCtx(ctx));
    }

    @Override
    public Node visitParam(MXParser.ParamContext ctx){
        String name = ctx.paraname().ID().getText();
        TypeNode type = (TypeNode) visit(ctx.type());
        return new VarDeclNode(type , name);
    }

    @Override
    public Node visitType(MXParser.TypeContext ctx){
        TypeNode type;
        if(ctx.type() != null){
            type = (TypeNode) visit(ctx.type());
            return new TypeNode(new ArrayType(type.getType()),Location.fromCtx(ctx));
        }
        else type = (TypeNode) visit(ctx.nonArrayType());
        return type;
    }

     @Override
    public Node visitNonArrayType(MXParser.NonArrayTypeContext ctx){
        if(ctx.classType() != null){
            return new TypeNode(new ClassType(ctx.classType().ID().getText()), Location.fromCtx(ctx));
        }
        Type type;
        if(ctx.varType().INT() != null){
            type = IntType.getType();
        }
        else if(ctx.varType().BOOL() != null){
            type = BoolType.getType();
        }
        else{
            type = StringType.getType();
        }
        return new TypeNode(type , Location.fromCtx(ctx));
    }

    @Override
    public Node visitBlock(MXParser.BlockContext ctx){
        List<Node> blockStmts = new ArrayList<>();
        if(ctx.statement() != null){
            for(ParserRuleContext statement : ctx.statement()){
                Node node = visit(statement);
                if(node != null){
                    blockStmts.add(node);
                }
            }
        }
        return new BlockStmtNode(blockStmts , Location.fromCtx(ctx));
    }

    @Override
    public Node visitBlockStmt(MXParser.BlockStmtContext ctx){
        return visit(ctx.block());
    }

    @Override
    public Node visitExprStmt(MXParser.ExprStmtContext ctx){
        ExprNode expr = (ExprNode)visit(ctx.exprField());
        return new ExprStmtNode(expr , Location.fromCtx(ctx));
    }

    @Override
    public Node visitExprField(MXParser.ExprFieldContext ctx){
        if(ctx.expr() != null){
            return visit(ctx.expr());
        }
        else return null;
    }

    @Override
    public Node visitVarDeclStmt(MXParser.VarDeclStmtContext ctx){
        return visit(ctx.varDeclField());
    }

    @Override
    public Node visitIfStmt(MXParser.IfStmtContext ctx){
        return visit(ctx.ifField());
    }

    @Override
    public Node visitLoopStmt(MXParser.LoopStmtContext ctx){
        return visit(ctx.loopField());
    }

    @Override
    public Node visitJumpStmt(MXParser.JumpStmtContext ctx){
        return visit(ctx.jumpField());
    }

    @Override
    public Node visitBody(MXParser.BodyContext ctx){
        return visit(ctx.statement());
    }

    @Override
    public Node visitIfField(MXParser.IfFieldContext ctx){
        ExprNode cond = (ExprNode) visit(ctx.expr());
        Node bbody = visit(ctx.body().statement());
        BlockStmtNode body;
        if(bbody instanceof ExprStmtNode) body = new BlockStmtNode((ExprStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof VarDeclNode) body = new BlockStmtNode((VarDeclNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof IfStmtNode) body = new BlockStmtNode((IfStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof WhileStmtNode) body = new BlockStmtNode((WhileStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof ForStmtNode) body = new BlockStmtNode((ForStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof ReturnStmtNode) body = new BlockStmtNode((ReturnStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof BreakStmtNode) body = new BlockStmtNode((BreakStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof ContinueStmtNode) body = new BlockStmtNode((ContinueStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof BlockStmtNode) body = (BlockStmtNode) bbody;
        else if(ctx.body().statement() == null) body = null;
        else throw new Error("error body statements");
        Node elseStmt;
        if(ctx.elseField() != null) elseStmt = visit(ctx.elseField().body().statement());
        else elseStmt = null;
        BlockStmtNode elseBody;
        if(elseStmt instanceof ExprStmtNode) elseBody = new BlockStmtNode((ExprStmtNode) elseStmt , Location.fromCtx(ctx));
        else if(elseStmt instanceof VarDeclNode) elseBody = new BlockStmtNode((VarDeclNode) elseStmt , Location.fromCtx(ctx));
        else if(elseStmt instanceof IfStmtNode) elseBody = new BlockStmtNode((IfStmtNode) elseStmt , Location.fromCtx(ctx));
        else if(elseStmt instanceof WhileStmtNode) elseBody = new BlockStmtNode((WhileStmtNode) elseStmt , Location.fromCtx(ctx));
        else if(elseStmt instanceof ForStmtNode) elseBody = new BlockStmtNode((ForStmtNode) elseStmt , Location.fromCtx(ctx));
        else if(elseStmt instanceof ReturnStmtNode) elseBody = new BlockStmtNode((ReturnStmtNode) elseStmt , Location.fromCtx(ctx));
        else if(elseStmt instanceof BreakStmtNode) elseBody = new BlockStmtNode((BreakStmtNode) elseStmt , Location.fromCtx(ctx));
        else if(elseStmt instanceof ContinueStmtNode) elseBody = new BlockStmtNode((ContinueStmtNode) elseStmt , Location.fromCtx(ctx));
        else if(elseStmt instanceof BlockStmtNode) elseBody = (BlockStmtNode) elseStmt;
        else if(elseStmt == null) elseBody = null;
        else throw new Error("error body statements");
        return new IfStmtNode(cond , body , elseBody);
    }

    @Override
    public Node visitLoopField(MXParser.LoopFieldContext ctx){
        if(ctx.forField() != null){
            return visit(ctx.forField());
        }
        else{
            return visit(ctx.whileField());
        }
    }

    @Override
    public Node visitForField(MXParser.ForFieldContext ctx){
        ExprNode init , cond , step;
        if(ctx.init != null){
            init = (ExprNode) visit(ctx.init);
        }
        else init = null;
        if(ctx.cond != null){
            cond = (ExprNode) visit(ctx.cond);
        }
        else cond = null;
        if(ctx.step != null){
            step = (ExprNode) visit(ctx.step);
        }
        else step = null;
        Node bbody = visit(ctx.body().statement());
        BlockStmtNode body;
        if(bbody instanceof ExprStmtNode) body = new BlockStmtNode((ExprStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof VarDeclNode) body = new BlockStmtNode((VarDeclNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof IfStmtNode) body = new BlockStmtNode((IfStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof WhileStmtNode) body = new BlockStmtNode((WhileStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof ForStmtNode) body = new BlockStmtNode((ForStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof ReturnStmtNode) body = new BlockStmtNode((ReturnStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof BreakStmtNode) body = new BlockStmtNode((BreakStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof ContinueStmtNode) body = new BlockStmtNode((ContinueStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof BlockStmtNode) body = (BlockStmtNode) bbody;
        else if(ctx.body().statement() == null) body = null;
        else throw new Error("error body statements");
        return new ForStmtNode(init , cond , step , body , Location.fromCtx(ctx));
    }

    @Override
    public Node visitWhileField(MXParser.WhileFieldContext ctx){
        ExprNode cond;
        if(ctx.expr() != null){
            cond = (ExprNode) visit(ctx.expr());
        }
        else cond = null;
        Node bbody = visit(ctx.body().statement());
        BlockStmtNode body;
        if(bbody instanceof ExprStmtNode) body = new BlockStmtNode((ExprStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof VarDeclNode) body = new BlockStmtNode((VarDeclNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof IfStmtNode) body = new BlockStmtNode((IfStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof WhileStmtNode) body = new BlockStmtNode((WhileStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof ForStmtNode) body = new BlockStmtNode((ForStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof ReturnStmtNode) body = new BlockStmtNode((ReturnStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof BreakStmtNode) body = new BlockStmtNode((BreakStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof ContinueStmtNode) body = new BlockStmtNode((ContinueStmtNode) bbody , Location.fromCtx(ctx));
        else if(bbody instanceof BlockStmtNode) body = (BlockStmtNode) bbody;
        else if(ctx.body().statement() == null) body = null;
        else throw new Error("error body statements");
        return new WhileStmtNode(cond , body , Location.fromCtx(ctx));
    }

    @Override
    public Node visitJumpField(MXParser.JumpFieldContext ctx){
        return visit(ctx.jumpInst());
    }

    @Override
    public Node visitReturnInst(MXParser.ReturnInstContext ctx){
        ExprNode expr;
        if(ctx.expr() != null){
            expr = (ExprNode) visit(ctx.expr());
        }
        else expr = null;
        return new ReturnStmtNode(expr , Location.fromCtx(ctx));
    }

    @Override
    public Node visitBreakInst(MXParser.BreakInstContext ctx){
        return new BreakStmtNode(Location.fromCtx(ctx));
    }

    @Override
    public Node visitContinueInst(MXParser.ContinueInstContext ctx){
        return new ContinueStmtNode(Location.fromCtx(ctx));
    }

    @Override
    public Node visitPrefixExpr(MXParser.PrefixExprContext ctx){
        PrefixExprNode.PrefixOps op;
        if(ctx.INC() != null) op = PrefixExprNode.PrefixOps.PREFIX_INC;
        else if(ctx.DEC() != null) op = PrefixExprNode.PrefixOps.PREFIX_DEC;
        else if(ctx.ADD() != null) op = PrefixExprNode.PrefixOps.POS;
        else if(ctx.SUB() != null) op = PrefixExprNode.PrefixOps.NEG;
        else if(ctx.NOT() != null) op = PrefixExprNode.PrefixOps.LOG_NOT;
        else op = PrefixExprNode.PrefixOps.BITWISE_NOT;
        ExprNode expr = (ExprNode) visit(ctx.expr());
        return new PrefixExprNode(op , expr , Location.fromCtx(ctx));
    }

    @Override
    public Node visitSuffixIncDec(MXParser.SuffixIncDecContext ctx){
        SuffixExprNode.SuffixOps op;
        if(ctx.INC() != null) op = SuffixExprNode.SuffixOps.SUFFIX_INC;
        else op = SuffixExprNode.SuffixOps.SUFFIX_DEC;
        ExprNode expr = (ExprNode) visit(ctx.expr());
        return new SuffixExprNode(op , expr , Location.fromCtx(ctx));
    }

    @Override
    public Node visitBinaryExpr(MXParser.BinaryExprContext ctx){
        BinaryExprNode.BinaryOps op;
        if(ctx.MUL() != null) op = BinaryExprNode.BinaryOps.MUL;
        else if(ctx.DIV() != null) op = BinaryExprNode.BinaryOps.DIV;
        else if(ctx.MOD() != null) op = BinaryExprNode.BinaryOps.MOD;
        else if(ctx.ADD() != null) op = BinaryExprNode.BinaryOps.ADD;
        else if(ctx.SUB() != null) op = BinaryExprNode.BinaryOps.SUB;
        else if(ctx.LSFT() != null) op = BinaryExprNode.BinaryOps.LSFT;
        else if(ctx.RSFT() != null) op = BinaryExprNode.BinaryOps.RSFT;
        else if(ctx.LT() != null) op = BinaryExprNode.BinaryOps.LT;
        else if(ctx.GT() != null) op = BinaryExprNode.BinaryOps.GT;
        else if(ctx.LTE() != null) op = BinaryExprNode.BinaryOps.LTE;
        else if(ctx.GTE() != null) op = BinaryExprNode.BinaryOps.GTE;
        else if(ctx.EQ() != null) op = BinaryExprNode.BinaryOps.EQ;
        else if(ctx.NEQ() != null) op = BinaryExprNode.BinaryOps.NEQ;
        else if(ctx.BITAND() != null) op = BinaryExprNode.BinaryOps.BITWISE_AND;
        else if(ctx.BITOR() != null) op = BinaryExprNode.BinaryOps.BITWISE_OR;
        else if(ctx.BITXOR() != null) op = BinaryExprNode.BinaryOps.BITWISE_XOR;
        else if(ctx.AND() != null) op = BinaryExprNode.BinaryOps.LOG_AND;
        else op = BinaryExprNode.BinaryOps.LOG_OR;
        ExprNode left , right;
        left = (ExprNode) visit(ctx.left);
        right = (ExprNode) visit(ctx.right);
        return new BinaryExprNode(op , left , right , Location.fromCtx(ctx));
    }

    @Override
    public Node visitFunctionCall(MXParser.FunctionCallContext ctx){
        ExprNode func = (ExprNode)visit(ctx.expr());
        List<ExprNode> args = new ArrayList<>();
        if(ctx.arguments().exprList() != null){
            for(ParserRuleContext expr : ctx.arguments().exprList().expr()){
                args.add((ExprNode)visit(expr));
            }
        }
        return new FuncCallExprNode(func , args , Location.fromCtx(ctx));
    }

    @Override
    public Node visitSubscriptExpr(MXParser.SubscriptExprContext ctx){
        ExprNode arr = (ExprNode)visit(ctx.arr);
        ExprNode sub = (ExprNode)visit(ctx.sub);
        return new SubscriptExprNode(arr , sub , Location.fromCtx(ctx));
    }

    @Override
    public Node visitMemberCall(MXParser.MemberCallContext ctx){
        ExprNode expr = (ExprNode) visit(ctx.expr());
        String id = ctx.ID().getText();
        return new MemberCallExprNode(expr , id , Location.fromCtx(ctx));
    }

    @Override
    public Node visitNewExpr(MXParser.NewExprContext ctx){
        return visit(ctx.creator());
    }

    @Override
    public Node visitLit(MXParser.LitContext ctx){
        return visit(ctx.literal());
    }

    @Override
    public Node visitIntLit(MXParser.IntLitContext ctx){
        int value;
        value = Integer.parseInt(ctx.getText());
        return new IntLitExprNode(value , Location.fromCtx(ctx));
    }

    @Override
    public Node visitBoolLit(MXParser.BoolLitContext ctx){
        boolean value;
        if(ctx.BoolLiteral().getText().equals("true")) value = true;
        else value = false;
        return new BoolLitExprNode(value , Location.fromCtx(ctx));
    }

    @Override
    public Node visitStringLit(MXParser.StringLitContext ctx){
        String value = ctx.StringLiteral().getText();
        return new StringLitExprNode(value , Location.fromCtx(ctx));
    }

    @Override
    public Node visitNullLit(MXParser.NullLitContext ctx){
        return new NullLitExprNode(Location.fromCtx(ctx));
    }

    @Override
    public Node visitThis(MXParser.ThisContext ctx){
        return new ThisExprNode(Location.fromCtx(ctx));
    }

    @Override
    public Node visitId(MXParser.IdContext ctx){
        String id = ctx.ID().getText();
        return new IdExprNode(id , Location.fromCtx(ctx));
    }

    @Override
    public Node visitAssignExpr(MXParser.AssignExprContext ctx){
        ExprNode left , right;
        left = (ExprNode) visit(ctx.left);
        right = (ExprNode) visit(ctx.right);
        return new AssignExprNode(left , right , Location.fromCtx(ctx));
    }

    @Override
    public Node visitParenExpr(MXParser.ParenExprContext ctx){
        return visit(ctx.expr());
    }

    @Override
    public Node visitErrorCreator(MXParser.ErrorCreatorContext ctx){
        throw new Error("invalid creator");
    }

    @Override
    public Node visitArrayCreator(MXParser.ArrayCreatorContext ctx){
        TypeNode type = (TypeNode)visit(ctx.nonArrayType());
        List<ExprNode> dims = new ArrayList<>();
        for(ParserRuleContext expr : ctx.expr()){
            dims.add((ExprNode)visit(expr));
        }
        int dimNum = (ctx.getChildCount() - 1 - dims.size())/2;
        for(int i = 0 ; i < dimNum ; ++i) type.setType(new ArrayType(type.getType()));
        return new CreatorExprNode(type , dims , dimNum , Location.fromCtx(ctx));
    }

    @Override
    public Node visitNonArrayCreator(MXParser.NonArrayCreatorContext ctx){
        TypeNode type = (TypeNode) visit(ctx.nonArrayTypeCreator());
        return new CreatorExprNode(type , null , 0 , Location.fromCtx(ctx));
    }

    @Override
    public Node visitNonArrayTypeCreator(MXParser.NonArrayTypeCreatorContext ctx){
        if(ctx.ID() != null) {
            return new TypeNode(new ClassType(ctx.ID().getText()), Location.fromCtx(ctx));
        }
        Type type;
        if(ctx.INT() != null){
            type = IntType.getType();
        }
        else if(ctx.BOOL() != null){
            type = BoolType.getType();
        }
        else{
            type = StringType.getType();
        }
        return new TypeNode(type , Location.fromCtx(ctx));
    }
}
