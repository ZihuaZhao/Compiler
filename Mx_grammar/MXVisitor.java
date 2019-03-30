// Generated from H:/IDEA/Mx_compiler/src/Mx_grammar\MX.g4 by ANTLR 4.7.2
package Mx_grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MXParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MXVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MXParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MXParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#context}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContext(MXParser.ContextContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#varDeclField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclField(MXParser.VarDeclFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MXParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#nonArrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonArrayType(MXParser.NonArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType(MXParser.VarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#classType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassType(MXParser.ClassTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#varField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarField(MXParser.VarFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#varInitField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarInitField(MXParser.VarInitFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#funcDeclField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclField(MXParser.FuncDeclFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#funcField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncField(MXParser.FuncFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#funcName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncName(MXParser.FuncNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#paramField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamField(MXParser.ParamFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(MXParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#paraname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParaname(MXParser.ParanameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#funcBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncBody(MXParser.FuncBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MXParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStmt(MXParser.BlockStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDeclStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclStmt(MXParser.VarDeclStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(MXParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(MXParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loopStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStmt(MXParser.LoopStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jumpStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStmt(MXParser.JumpStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#exprField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprField(MXParser.ExprFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#ifField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfField(MXParser.IfFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#elseField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseField(MXParser.ElseFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(MXParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#loopField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopField(MXParser.LoopFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#forField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForField(MXParser.ForFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#whileField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileField(MXParser.WhileFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#jumpField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpField(MXParser.JumpFieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnInst}
	 * labeled alternative in {@link MXParser#jumpInst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnInst(MXParser.ReturnInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakInst}
	 * labeled alternative in {@link MXParser#jumpInst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakInst(MXParser.BreakInstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueInst}
	 * labeled alternative in {@link MXParser#jumpInst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueInst(MXParser.ContinueInstContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#classDeclField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclField(MXParser.ClassDeclFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(MXParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#classBuildField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBuildField(MXParser.ClassBuildFieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(MXParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixExpr(MXParser.PrefixExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subscriptExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptExpr(MXParser.SubscriptExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lit}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLit(MXParser.LitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(MXParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code this}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThis(MXParser.ThisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(MXParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code suffixIncDec}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuffixIncDec(MXParser.SuffixIncDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(MXParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(MXParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(MXParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberCall}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberCall(MXParser.MemberCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLit(MXParser.BoolLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLit(MXParser.IntLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLit(MXParser.StringLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullLit(MXParser.NullLitContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(MXParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(MXParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code errorCreator}
	 * labeled alternative in {@link MXParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitErrorCreator(MXParser.ErrorCreatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MXParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreator(MXParser.ArrayCreatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nonArrayCreator}
	 * labeled alternative in {@link MXParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonArrayCreator(MXParser.NonArrayCreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MXParser#nonArrayTypeCreator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonArrayTypeCreator(MXParser.NonArrayTypeCreatorContext ctx);
}