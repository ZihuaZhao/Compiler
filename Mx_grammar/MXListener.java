// Generated from H:/IDEA/Mx_compiler/src/Mx_grammar\MX.g4 by ANTLR 4.7.2
package Mx_grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MXParser}.
 */
public interface MXListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MXParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MXParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MXParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#context}.
	 * @param ctx the parse tree
	 */
	void enterContext(MXParser.ContextContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#context}.
	 * @param ctx the parse tree
	 */
	void exitContext(MXParser.ContextContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#varDeclField}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclField(MXParser.VarDeclFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#varDeclField}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclField(MXParser.VarDeclFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MXParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MXParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#nonArrayType}.
	 * @param ctx the parse tree
	 */
	void enterNonArrayType(MXParser.NonArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#nonArrayType}.
	 * @param ctx the parse tree
	 */
	void exitNonArrayType(MXParser.NonArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterVarType(MXParser.VarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitVarType(MXParser.VarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#classType}.
	 * @param ctx the parse tree
	 */
	void enterClassType(MXParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#classType}.
	 * @param ctx the parse tree
	 */
	void exitClassType(MXParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#varField}.
	 * @param ctx the parse tree
	 */
	void enterVarField(MXParser.VarFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#varField}.
	 * @param ctx the parse tree
	 */
	void exitVarField(MXParser.VarFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#varInitField}.
	 * @param ctx the parse tree
	 */
	void enterVarInitField(MXParser.VarInitFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#varInitField}.
	 * @param ctx the parse tree
	 */
	void exitVarInitField(MXParser.VarInitFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#funcDeclField}.
	 * @param ctx the parse tree
	 */
	void enterFuncDeclField(MXParser.FuncDeclFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#funcDeclField}.
	 * @param ctx the parse tree
	 */
	void exitFuncDeclField(MXParser.FuncDeclFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#funcField}.
	 * @param ctx the parse tree
	 */
	void enterFuncField(MXParser.FuncFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#funcField}.
	 * @param ctx the parse tree
	 */
	void exitFuncField(MXParser.FuncFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#funcName}.
	 * @param ctx the parse tree
	 */
	void enterFuncName(MXParser.FuncNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#funcName}.
	 * @param ctx the parse tree
	 */
	void exitFuncName(MXParser.FuncNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#paramField}.
	 * @param ctx the parse tree
	 */
	void enterParamField(MXParser.ParamFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#paramField}.
	 * @param ctx the parse tree
	 */
	void exitParamField(MXParser.ParamFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(MXParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(MXParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#paraname}.
	 * @param ctx the parse tree
	 */
	void enterParaname(MXParser.ParanameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#paraname}.
	 * @param ctx the parse tree
	 */
	void exitParaname(MXParser.ParanameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#funcBody}.
	 * @param ctx the parse tree
	 */
	void enterFuncBody(MXParser.FuncBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#funcBody}.
	 * @param ctx the parse tree
	 */
	void exitFuncBody(MXParser.FuncBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MXParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MXParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmt(MXParser.BlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmt(MXParser.BlockStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDeclStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclStmt(MXParser.VarDeclStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDeclStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclStmt(MXParser.VarDeclStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(MXParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(MXParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MXParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MXParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code loopStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStmt(MXParser.LoopStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code loopStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStmt(MXParser.LoopStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jumpStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStmt(MXParser.JumpStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jumpStmt}
	 * labeled alternative in {@link MXParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStmt(MXParser.JumpStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#exprField}.
	 * @param ctx the parse tree
	 */
	void enterExprField(MXParser.ExprFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#exprField}.
	 * @param ctx the parse tree
	 */
	void exitExprField(MXParser.ExprFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#ifField}.
	 * @param ctx the parse tree
	 */
	void enterIfField(MXParser.IfFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#ifField}.
	 * @param ctx the parse tree
	 */
	void exitIfField(MXParser.IfFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#elseField}.
	 * @param ctx the parse tree
	 */
	void enterElseField(MXParser.ElseFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#elseField}.
	 * @param ctx the parse tree
	 */
	void exitElseField(MXParser.ElseFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(MXParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(MXParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#loopField}.
	 * @param ctx the parse tree
	 */
	void enterLoopField(MXParser.LoopFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#loopField}.
	 * @param ctx the parse tree
	 */
	void exitLoopField(MXParser.LoopFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#forField}.
	 * @param ctx the parse tree
	 */
	void enterForField(MXParser.ForFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#forField}.
	 * @param ctx the parse tree
	 */
	void exitForField(MXParser.ForFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#whileField}.
	 * @param ctx the parse tree
	 */
	void enterWhileField(MXParser.WhileFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#whileField}.
	 * @param ctx the parse tree
	 */
	void exitWhileField(MXParser.WhileFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#jumpField}.
	 * @param ctx the parse tree
	 */
	void enterJumpField(MXParser.JumpFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#jumpField}.
	 * @param ctx the parse tree
	 */
	void exitJumpField(MXParser.JumpFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnInst}
	 * labeled alternative in {@link MXParser#jumpInst}.
	 * @param ctx the parse tree
	 */
	void enterReturnInst(MXParser.ReturnInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnInst}
	 * labeled alternative in {@link MXParser#jumpInst}.
	 * @param ctx the parse tree
	 */
	void exitReturnInst(MXParser.ReturnInstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakInst}
	 * labeled alternative in {@link MXParser#jumpInst}.
	 * @param ctx the parse tree
	 */
	void enterBreakInst(MXParser.BreakInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakInst}
	 * labeled alternative in {@link MXParser#jumpInst}.
	 * @param ctx the parse tree
	 */
	void exitBreakInst(MXParser.BreakInstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueInst}
	 * labeled alternative in {@link MXParser#jumpInst}.
	 * @param ctx the parse tree
	 */
	void enterContinueInst(MXParser.ContinueInstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueInst}
	 * labeled alternative in {@link MXParser#jumpInst}.
	 * @param ctx the parse tree
	 */
	void exitContinueInst(MXParser.ContinueInstContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#classDeclField}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclField(MXParser.ClassDeclFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#classDeclField}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclField(MXParser.ClassDeclFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(MXParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(MXParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#classBuildField}.
	 * @param ctx the parse tree
	 */
	void enterClassBuildField(MXParser.ClassBuildFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#classBuildField}.
	 * @param ctx the parse tree
	 */
	void exitClassBuildField(MXParser.ClassBuildFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MXParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MXParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpr(MXParser.PrefixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpr(MXParser.PrefixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptExpr(MXParser.SubscriptExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptExpr(MXParser.SubscriptExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lit}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLit(MXParser.LitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lit}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLit(MXParser.LitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(MXParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(MXParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code this}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterThis(MXParser.ThisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code this}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitThis(MXParser.ThisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MXParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MXParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixIncDec}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSuffixIncDec(MXParser.SuffixIncDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixIncDec}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSuffixIncDec(MXParser.SuffixIncDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(MXParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(MXParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(MXParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(MXParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(MXParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(MXParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberCall}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMemberCall(MXParser.MemberCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberCall}
	 * labeled alternative in {@link MXParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMemberCall(MXParser.MemberCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBoolLit(MXParser.BoolLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBoolLit(MXParser.BoolLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterIntLit(MXParser.IntLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitIntLit(MXParser.IntLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterStringLit(MXParser.StringLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitStringLit(MXParser.StringLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNullLit(MXParser.NullLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullLit}
	 * labeled alternative in {@link MXParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNullLit(MXParser.NullLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(MXParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(MXParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(MXParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(MXParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code errorCreator}
	 * labeled alternative in {@link MXParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterErrorCreator(MXParser.ErrorCreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code errorCreator}
	 * labeled alternative in {@link MXParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitErrorCreator(MXParser.ErrorCreatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MXParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreator(MXParser.ArrayCreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayCreator}
	 * labeled alternative in {@link MXParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreator(MXParser.ArrayCreatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nonArrayCreator}
	 * labeled alternative in {@link MXParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterNonArrayCreator(MXParser.NonArrayCreatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nonArrayCreator}
	 * labeled alternative in {@link MXParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitNonArrayCreator(MXParser.NonArrayCreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MXParser#nonArrayTypeCreator}.
	 * @param ctx the parse tree
	 */
	void enterNonArrayTypeCreator(MXParser.NonArrayTypeCreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MXParser#nonArrayTypeCreator}.
	 * @param ctx the parse tree
	 */
	void exitNonArrayTypeCreator(MXParser.NonArrayTypeCreatorContext ctx);
}