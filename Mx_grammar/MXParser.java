// Generated from H:/IDEA/Mx_compiler/src/Mx_grammar\MX.g4 by ANTLR 4.7.2
package Mx_grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MXParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BOOL=1, INT=2, STRING=3, NULL=4, VOID=5, IF=6, ELSE=7, FOR=8, WHILE=9, 
		BREAK=10, CONTINUE=11, RETURN=12, NEW=13, CLASS=14, THIS=15, LPAREN=16, 
		RPAREN=17, LBRACK=18, RBRACK=19, LBRACE=20, RBRACE=21, COMMA=22, SEMI=23, 
		ADD=24, SUB=25, MUL=26, DIV=27, MOD=28, LT=29, GT=30, EQ=31, NEQ=32, GTE=33, 
		LTE=34, AND=35, OR=36, NOT=37, LSFT=38, RSFT=39, BITNOT=40, BITOR=41, 
		BITXOR=42, BITAND=43, ASSIGN=44, INC=45, DEC=46, DOT=47, BoolLiteral=48, 
		IntLiteral=49, StringLiteral=50, ID=51, LineComment=52, MultilineComment=53, 
		WS=54;
	public static final int
		RULE_program = 0, RULE_context = 1, RULE_varDeclField = 2, RULE_type = 3, 
		RULE_nonArrayType = 4, RULE_varType = 5, RULE_classType = 6, RULE_varField = 7, 
		RULE_varInitField = 8, RULE_funcDeclField = 9, RULE_funcField = 10, RULE_funcName = 11, 
		RULE_paramField = 12, RULE_param = 13, RULE_paraname = 14, RULE_funcBody = 15, 
		RULE_block = 16, RULE_statement = 17, RULE_exprField = 18, RULE_ifField = 19, 
		RULE_elseField = 20, RULE_body = 21, RULE_loopField = 22, RULE_forField = 23, 
		RULE_whileField = 24, RULE_jumpField = 25, RULE_jumpInst = 26, RULE_classDeclField = 27, 
		RULE_classBody = 28, RULE_classBuildField = 29, RULE_expr = 30, RULE_literal = 31, 
		RULE_arguments = 32, RULE_exprList = 33, RULE_creator = 34, RULE_nonArrayTypeCreator = 35;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "context", "varDeclField", "type", "nonArrayType", "varType", 
			"classType", "varField", "varInitField", "funcDeclField", "funcField", 
			"funcName", "paramField", "param", "paraname", "funcBody", "block", "statement", 
			"exprField", "ifField", "elseField", "body", "loopField", "forField", 
			"whileField", "jumpField", "jumpInst", "classDeclField", "classBody", 
			"classBuildField", "expr", "literal", "arguments", "exprList", "creator", 
			"nonArrayTypeCreator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'bool'", "'int'", "'string'", "'null'", "'void'", "'if'", "'else'", 
			"'for'", "'while'", "'break'", "'continue'", "'return'", "'new'", "'class'", 
			"'this'", "'('", "')'", "'['", "']'", "'{'", "'}'", "','", "';'", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'<'", "'>'", "'=='", "'!='", "'>='", "'<='", 
			"'&&'", "'||'", "'!'", "'<<'", "'>>'", "'~'", "'|'", "'^'", "'&'", "'='", 
			"'++'", "'--'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BOOL", "INT", "STRING", "NULL", "VOID", "IF", "ELSE", "FOR", "WHILE", 
			"BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", "THIS", "LPAREN", "RPAREN", 
			"LBRACK", "RBRACK", "LBRACE", "RBRACE", "COMMA", "SEMI", "ADD", "SUB", 
			"MUL", "DIV", "MOD", "LT", "GT", "EQ", "NEQ", "GTE", "LTE", "AND", "OR", 
			"NOT", "LSFT", "RSFT", "BITNOT", "BITOR", "BITXOR", "BITAND", "ASSIGN", 
			"INC", "DEC", "DOT", "BoolLiteral", "IntLiteral", "StringLiteral", "ID", 
			"LineComment", "MultilineComment", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MX.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MXParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MXParser.EOF, 0); }
		public List<ContextContext> context() {
			return getRuleContexts(ContextContext.class);
		}
		public ContextContext context(int i) {
			return getRuleContext(ContextContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << CLASS) | (1L << ID))) != 0)) {
				{
				{
				setState(72);
				context();
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContextContext extends ParserRuleContext {
		public VarDeclFieldContext varDeclField() {
			return getRuleContext(VarDeclFieldContext.class,0);
		}
		public FuncDeclFieldContext funcDeclField() {
			return getRuleContext(FuncDeclFieldContext.class,0);
		}
		public ClassDeclFieldContext classDeclField() {
			return getRuleContext(ClassDeclFieldContext.class,0);
		}
		public ContextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_context; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitContext(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContextContext context() throws RecognitionException {
		ContextContext _localctx = new ContextContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_context);
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				varDeclField();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				funcDeclField();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(82);
				classDeclField();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclFieldContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarFieldContext varField() {
			return getRuleContext(VarFieldContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MXParser.SEMI, 0); }
		public VarDeclFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitVarDeclField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclFieldContext varDeclField() throws RecognitionException {
		VarDeclFieldContext _localctx = new VarDeclFieldContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varDeclField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			type(0);
			setState(86);
			varField();
			setState(87);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public NonArrayTypeContext nonArrayType() {
			return getRuleContext(NonArrayTypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(MXParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(MXParser.RBRACK, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(90);
			nonArrayType();
			}
			_ctx.stop = _input.LT(-1);
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(92);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(93);
					match(LBRACK);
					setState(94);
					match(RBRACK);
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NonArrayTypeContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public NonArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonArrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitNonArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonArrayTypeContext nonArrayType() throws RecognitionException {
		NonArrayTypeContext _localctx = new NonArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_nonArrayType);
		try {
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case INT:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				varType();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				classType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MXParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(MXParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(MXParser.STRING, 0); }
		public VarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitVarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarTypeContext varType() throws RecognitionException {
		VarTypeContext _localctx = new VarTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassTypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MXParser.ID, 0); }
		public ClassTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitClassType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassTypeContext classType() throws RecognitionException {
		ClassTypeContext _localctx = new ClassTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarFieldContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MXParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(MXParser.ASSIGN, 0); }
		public VarInitFieldContext varInitField() {
			return getRuleContext(VarInitFieldContext.class,0);
		}
		public VarFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitVarField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarFieldContext varField() throws RecognitionException {
		VarFieldContext _localctx = new VarFieldContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(ID);
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(109);
				match(ASSIGN);
				setState(110);
				varInitField();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarInitFieldContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarInitFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varInitField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitVarInitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarInitFieldContext varInitField() throws RecognitionException {
		VarInitFieldContext _localctx = new VarInitFieldContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varInitField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDeclFieldContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FuncFieldContext funcField() {
			return getRuleContext(FuncFieldContext.class,0);
		}
		public TerminalNode VOID() { return getToken(MXParser.VOID, 0); }
		public FuncDeclFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDeclField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitFuncDeclField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclFieldContext funcDeclField() throws RecognitionException {
		FuncDeclFieldContext _localctx = new FuncDeclFieldContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_funcDeclField);
		try {
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case INT:
			case STRING:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				type(0);
				setState(116);
				funcField();
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				match(VOID);
				setState(119);
				funcField();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncFieldContext extends ParserRuleContext {
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public ParamFieldContext paramField() {
			return getRuleContext(ParamFieldContext.class,0);
		}
		public FuncBodyContext funcBody() {
			return getRuleContext(FuncBodyContext.class,0);
		}
		public FuncFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitFuncField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncFieldContext funcField() throws RecognitionException {
		FuncFieldContext _localctx = new FuncFieldContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_funcField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			funcName();
			setState(123);
			paramField();
			setState(124);
			funcBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MXParser.ID, 0); }
		public FuncNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitFuncName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncNameContext funcName() throws RecognitionException {
		FuncNameContext _localctx = new FuncNameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamFieldContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(MXParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MXParser.RPAREN, 0); }
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MXParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MXParser.COMMA, i);
		}
		public ParamFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitParamField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamFieldContext paramField() throws RecognitionException {
		ParamFieldContext _localctx = new ParamFieldContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_paramField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(LPAREN);
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << ID))) != 0)) {
				{
				setState(129);
				param();
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(130);
					match(COMMA);
					setState(131);
					param();
					}
					}
					setState(136);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(139);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParanameContext paraname() {
			return getRuleContext(ParanameContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			type(0);
			setState(142);
			paraname();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParanameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MXParser.ID, 0); }
		public ParanameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paraname; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitParaname(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParanameContext paraname() throws RecognitionException {
		ParanameContext _localctx = new ParanameContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_paraname);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncBodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FuncBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitFuncBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncBodyContext funcBody() throws RecognitionException {
		FuncBodyContext _localctx = new FuncBodyContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_funcBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(MXParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(MXParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(LBRACE);
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << NULL) | (1L << IF) | (1L << FOR) | (1L << WHILE) | (1L << BREAK) | (1L << CONTINUE) | (1L << RETURN) | (1L << NEW) | (1L << THIS) | (1L << LPAREN) | (1L << LBRACE) | (1L << SEMI) | (1L << ADD) | (1L << SUB) | (1L << NOT) | (1L << BITNOT) | (1L << INC) | (1L << DEC) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << ID))) != 0)) {
				{
				{
				setState(149);
				statement();
				}
				}
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(155);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class JumpStmtContext extends StatementContext {
		public JumpFieldContext jumpField() {
			return getRuleContext(JumpFieldContext.class,0);
		}
		public JumpStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitJumpStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprStmtContext extends StatementContext {
		public ExprFieldContext exprField() {
			return getRuleContext(ExprFieldContext.class,0);
		}
		public ExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LoopStmtContext extends StatementContext {
		public LoopFieldContext loopField() {
			return getRuleContext(LoopFieldContext.class,0);
		}
		public LoopStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitLoopStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmtContext extends StatementContext {
		public IfFieldContext ifField() {
			return getRuleContext(IfFieldContext.class,0);
		}
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockStmtContext extends StatementContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitBlockStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarDeclStmtContext extends StatementContext {
		public VarDeclFieldContext varDeclField() {
			return getRuleContext(VarDeclFieldContext.class,0);
		}
		public VarDeclStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitVarDeclStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_statement);
		try {
			setState(163);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new BlockStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				block();
				}
				break;
			case 2:
				_localctx = new VarDeclStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				varDeclField();
				}
				break;
			case 3:
				_localctx = new ExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(159);
				exprField();
				}
				break;
			case 4:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(160);
				ifField();
				}
				break;
			case 5:
				_localctx = new LoopStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(161);
				loopField();
				}
				break;
			case 6:
				_localctx = new JumpStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(162);
				jumpField();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprFieldContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(MXParser.SEMI, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitExprField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprFieldContext exprField() throws RecognitionException {
		ExprFieldContext _localctx = new ExprFieldContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exprField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << NEW) | (1L << THIS) | (1L << LPAREN) | (1L << ADD) | (1L << SUB) | (1L << NOT) | (1L << BITNOT) | (1L << INC) | (1L << DEC) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << ID))) != 0)) {
				{
				setState(165);
				expr(0);
				}
			}

			setState(168);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfFieldContext extends ParserRuleContext {
		public ExprContext cond;
		public TerminalNode IF() { return getToken(MXParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(MXParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MXParser.RPAREN, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ElseFieldContext elseField() {
			return getRuleContext(ElseFieldContext.class,0);
		}
		public IfFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitIfField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfFieldContext ifField() throws RecognitionException {
		IfFieldContext _localctx = new IfFieldContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_ifField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(IF);
			setState(171);
			match(LPAREN);
			setState(172);
			((IfFieldContext)_localctx).cond = expr(0);
			setState(173);
			match(RPAREN);
			setState(174);
			body();
			setState(176);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(175);
				elseField();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElseFieldContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(MXParser.ELSE, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ElseFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitElseField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseFieldContext elseField() throws RecognitionException {
		ElseFieldContext _localctx = new ElseFieldContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_elseField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(ELSE);
			setState(179);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopFieldContext extends ParserRuleContext {
		public ForFieldContext forField() {
			return getRuleContext(ForFieldContext.class,0);
		}
		public WhileFieldContext whileField() {
			return getRuleContext(WhileFieldContext.class,0);
		}
		public LoopFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitLoopField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopFieldContext loopField() throws RecognitionException {
		LoopFieldContext _localctx = new LoopFieldContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_loopField);
		try {
			setState(185);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				forField();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				whileField();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForFieldContext extends ParserRuleContext {
		public ExprContext init;
		public ExprContext cond;
		public ExprContext step;
		public TerminalNode FOR() { return getToken(MXParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(MXParser.LPAREN, 0); }
		public List<TerminalNode> SEMI() { return getTokens(MXParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(MXParser.SEMI, i);
		}
		public TerminalNode RPAREN() { return getToken(MXParser.RPAREN, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ForFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitForField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForFieldContext forField() throws RecognitionException {
		ForFieldContext _localctx = new ForFieldContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_forField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(FOR);
			setState(188);
			match(LPAREN);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << NEW) | (1L << THIS) | (1L << LPAREN) | (1L << ADD) | (1L << SUB) | (1L << NOT) | (1L << BITNOT) | (1L << INC) | (1L << DEC) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << ID))) != 0)) {
				{
				setState(189);
				((ForFieldContext)_localctx).init = expr(0);
				}
			}

			setState(192);
			match(SEMI);
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << NEW) | (1L << THIS) | (1L << LPAREN) | (1L << ADD) | (1L << SUB) | (1L << NOT) | (1L << BITNOT) | (1L << INC) | (1L << DEC) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << ID))) != 0)) {
				{
				setState(193);
				((ForFieldContext)_localctx).cond = expr(0);
				}
			}

			setState(196);
			match(SEMI);
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << NEW) | (1L << THIS) | (1L << LPAREN) | (1L << ADD) | (1L << SUB) | (1L << NOT) | (1L << BITNOT) | (1L << INC) | (1L << DEC) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << ID))) != 0)) {
				{
				setState(197);
				((ForFieldContext)_localctx).step = expr(0);
				}
			}

			setState(200);
			match(RPAREN);
			setState(201);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileFieldContext extends ParserRuleContext {
		public ExprContext cond;
		public TerminalNode WHILE() { return getToken(MXParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(MXParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MXParser.RPAREN, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public WhileFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitWhileField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileFieldContext whileField() throws RecognitionException {
		WhileFieldContext _localctx = new WhileFieldContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_whileField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(WHILE);
			setState(204);
			match(LPAREN);
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << NEW) | (1L << THIS) | (1L << LPAREN) | (1L << ADD) | (1L << SUB) | (1L << NOT) | (1L << BITNOT) | (1L << INC) | (1L << DEC) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << ID))) != 0)) {
				{
				setState(205);
				((WhileFieldContext)_localctx).cond = expr(0);
				}
			}

			setState(208);
			match(RPAREN);
			setState(209);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JumpFieldContext extends ParserRuleContext {
		public JumpInstContext jumpInst() {
			return getRuleContext(JumpInstContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MXParser.SEMI, 0); }
		public JumpFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitJumpField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpFieldContext jumpField() throws RecognitionException {
		JumpFieldContext _localctx = new JumpFieldContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_jumpField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			jumpInst();
			setState(212);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JumpInstContext extends ParserRuleContext {
		public JumpInstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jumpInst; }
	 
		public JumpInstContext() { }
		public void copyFrom(JumpInstContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ContinueInstContext extends JumpInstContext {
		public TerminalNode CONTINUE() { return getToken(MXParser.CONTINUE, 0); }
		public ContinueInstContext(JumpInstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitContinueInst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnInstContext extends JumpInstContext {
		public TerminalNode RETURN() { return getToken(MXParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnInstContext(JumpInstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitReturnInst(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakInstContext extends JumpInstContext {
		public TerminalNode BREAK() { return getToken(MXParser.BREAK, 0); }
		public BreakInstContext(JumpInstContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitBreakInst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpInstContext jumpInst() throws RecognitionException {
		JumpInstContext _localctx = new JumpInstContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_jumpInst);
		int _la;
		try {
			setState(220);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RETURN:
				_localctx = new ReturnInstContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				match(RETURN);
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << NEW) | (1L << THIS) | (1L << LPAREN) | (1L << ADD) | (1L << SUB) | (1L << NOT) | (1L << BITNOT) | (1L << INC) | (1L << DEC) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << ID))) != 0)) {
					{
					setState(215);
					expr(0);
					}
				}

				}
				break;
			case BREAK:
				_localctx = new BreakInstContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				match(BREAK);
				}
				break;
			case CONTINUE:
				_localctx = new ContinueInstContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(219);
				match(CONTINUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclFieldContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(MXParser.CLASS, 0); }
		public TerminalNode ID() { return getToken(MXParser.ID, 0); }
		public TerminalNode LBRACE() { return getToken(MXParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(MXParser.RBRACE, 0); }
		public List<ClassBodyContext> classBody() {
			return getRuleContexts(ClassBodyContext.class);
		}
		public ClassBodyContext classBody(int i) {
			return getRuleContext(ClassBodyContext.class,i);
		}
		public ClassDeclFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitClassDeclField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclFieldContext classDeclField() throws RecognitionException {
		ClassDeclFieldContext _localctx = new ClassDeclFieldContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_classDeclField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(CLASS);
			setState(223);
			match(ID);
			setState(224);
			match(LBRACE);
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << STRING) | (1L << VOID) | (1L << ID))) != 0)) {
				{
				{
				setState(225);
				classBody();
				}
				}
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(231);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyContext extends ParserRuleContext {
		public VarDeclFieldContext varDeclField() {
			return getRuleContext(VarDeclFieldContext.class,0);
		}
		public FuncDeclFieldContext funcDeclField() {
			return getRuleContext(FuncDeclFieldContext.class,0);
		}
		public ClassBuildFieldContext classBuildField() {
			return getRuleContext(ClassBuildFieldContext.class,0);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitClassBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_classBody);
		try {
			setState(236);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(233);
				varDeclField();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(234);
				funcDeclField();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(235);
				classBuildField();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBuildFieldContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MXParser.ID, 0); }
		public ParamFieldContext paramField() {
			return getRuleContext(ParamFieldContext.class,0);
		}
		public FuncBodyContext funcBody() {
			return getRuleContext(FuncBodyContext.class,0);
		}
		public ClassBuildFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBuildField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitClassBuildField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBuildFieldContext classBuildField() throws RecognitionException {
		ClassBuildFieldContext _localctx = new ClassBuildFieldContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_classBuildField);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(ID);
			setState(239);
			paramField();
			setState(240);
			funcBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExprContext {
		public TerminalNode NEW() { return getToken(MXParser.NEW, 0); }
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public NewExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrefixExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode INC() { return getToken(MXParser.INC, 0); }
		public TerminalNode DEC() { return getToken(MXParser.DEC, 0); }
		public TerminalNode ADD() { return getToken(MXParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MXParser.SUB, 0); }
		public TerminalNode NOT() { return getToken(MXParser.NOT, 0); }
		public TerminalNode BITNOT() { return getToken(MXParser.BITNOT, 0); }
		public PrefixExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitPrefixExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubscriptExprContext extends ExprContext {
		public ExprContext arr;
		public ExprContext sub;
		public TerminalNode LBRACK() { return getToken(MXParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(MXParser.RBRACK, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public SubscriptExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitSubscriptExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LitContext extends ExprContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitLit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionCallContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public FunctionCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisContext extends ExprContext {
		public TerminalNode THIS() { return getToken(MXParser.THIS, 0); }
		public ThisContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitThis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(MXParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(MXParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MXParser.MOD, 0); }
		public TerminalNode ADD() { return getToken(MXParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MXParser.SUB, 0); }
		public TerminalNode LSFT() { return getToken(MXParser.LSFT, 0); }
		public TerminalNode RSFT() { return getToken(MXParser.RSFT, 0); }
		public TerminalNode LT() { return getToken(MXParser.LT, 0); }
		public TerminalNode GT() { return getToken(MXParser.GT, 0); }
		public TerminalNode LTE() { return getToken(MXParser.LTE, 0); }
		public TerminalNode GTE() { return getToken(MXParser.GTE, 0); }
		public TerminalNode EQ() { return getToken(MXParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(MXParser.NEQ, 0); }
		public TerminalNode BITAND() { return getToken(MXParser.BITAND, 0); }
		public TerminalNode BITXOR() { return getToken(MXParser.BITXOR, 0); }
		public TerminalNode BITOR() { return getToken(MXParser.BITOR, 0); }
		public TerminalNode AND() { return getToken(MXParser.AND, 0); }
		public TerminalNode OR() { return getToken(MXParser.OR, 0); }
		public BinaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuffixIncDecContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode INC() { return getToken(MXParser.INC, 0); }
		public TerminalNode DEC() { return getToken(MXParser.DEC, 0); }
		public SuffixIncDecContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitSuffixIncDec(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdContext extends ExprContext {
		public TerminalNode ID() { return getToken(MXParser.ID, 0); }
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignExprContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(MXParser.ASSIGN, 0); }
		public AssignExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(MXParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MXParser.RPAREN, 0); }
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberCallContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MXParser.DOT, 0); }
		public TerminalNode ID() { return getToken(MXParser.ID, 0); }
		public MemberCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitMemberCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 60;
		enterRecursionRule(_localctx, 60, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INC:
			case DEC:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(243);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==INC || _la==DEC) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(244);
				expr(19);
				}
				break;
			case ADD:
			case SUB:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(245);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(246);
				expr(18);
				}
				break;
			case NOT:
			case BITNOT:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(247);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==NOT || _la==BITNOT) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(248);
				expr(17);
				}
				break;
			case NEW:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(249);
				match(NEW);
				setState(250);
				creator();
				}
				break;
			case NULL:
			case BoolLiteral:
			case IntLiteral:
			case StringLiteral:
				{
				_localctx = new LitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(251);
				literal();
				}
				break;
			case THIS:
				{
				_localctx = new ThisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(252);
				match(THIS);
				}
				break;
			case ID:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(253);
				match(ID);
				}
				break;
			case LPAREN:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(254);
				match(LPAREN);
				setState(255);
				expr(0);
				setState(256);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(307);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(305);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(260);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(261);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(262);
						((BinaryExprContext)_localctx).right = expr(16);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(263);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(264);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(265);
						((BinaryExprContext)_localctx).right = expr(15);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(266);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(267);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LSFT || _la==RSFT) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(268);
						((BinaryExprContext)_localctx).right = expr(14);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(269);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(270);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT) | (1L << GT) | (1L << GTE) | (1L << LTE))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(271);
						((BinaryExprContext)_localctx).right = expr(13);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(272);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(273);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(274);
						((BinaryExprContext)_localctx).right = expr(12);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(275);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(276);
						((BinaryExprContext)_localctx).op = match(BITAND);
						setState(277);
						((BinaryExprContext)_localctx).right = expr(11);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(278);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(279);
						((BinaryExprContext)_localctx).op = match(BITXOR);
						setState(280);
						((BinaryExprContext)_localctx).right = expr(10);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(281);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(282);
						((BinaryExprContext)_localctx).op = match(BITOR);
						setState(283);
						((BinaryExprContext)_localctx).right = expr(9);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(284);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(285);
						((BinaryExprContext)_localctx).op = match(AND);
						setState(286);
						((BinaryExprContext)_localctx).right = expr(8);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(287);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(288);
						((BinaryExprContext)_localctx).op = match(OR);
						setState(289);
						((BinaryExprContext)_localctx).right = expr(7);
						}
						break;
					case 11:
						{
						_localctx = new AssignExprContext(new ExprContext(_parentctx, _parentState));
						((AssignExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(290);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(291);
						((AssignExprContext)_localctx).op = match(ASSIGN);
						setState(292);
						((AssignExprContext)_localctx).right = expr(5);
						}
						break;
					case 12:
						{
						_localctx = new SuffixIncDecContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(293);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(294);
						((SuffixIncDecContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==INC || _la==DEC) ) {
							((SuffixIncDecContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 13:
						{
						_localctx = new FunctionCallContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(295);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(296);
						arguments();
						}
						break;
					case 14:
						{
						_localctx = new SubscriptExprContext(new ExprContext(_parentctx, _parentState));
						((SubscriptExprContext)_localctx).arr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(297);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(298);
						match(LBRACK);
						setState(299);
						((SubscriptExprContext)_localctx).sub = expr(0);
						setState(300);
						match(RBRACK);
						}
						break;
					case 15:
						{
						_localctx = new MemberCallContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(302);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(303);
						match(DOT);
						setState(304);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(309);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringLitContext extends LiteralContext {
		public TerminalNode StringLiteral() { return getToken(MXParser.StringLiteral, 0); }
		public StringLitContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitStringLit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolLitContext extends LiteralContext {
		public TerminalNode BoolLiteral() { return getToken(MXParser.BoolLiteral, 0); }
		public BoolLitContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitBoolLit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntLitContext extends LiteralContext {
		public TerminalNode IntLiteral() { return getToken(MXParser.IntLiteral, 0); }
		public IntLitContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitIntLit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullLitContext extends LiteralContext {
		public TerminalNode NULL() { return getToken(MXParser.NULL, 0); }
		public NullLitContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitNullLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_literal);
		try {
			setState(314);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BoolLiteral:
				_localctx = new BoolLitContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(310);
				match(BoolLiteral);
				}
				break;
			case IntLiteral:
				_localctx = new IntLitContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(311);
				match(IntLiteral);
				}
				break;
			case StringLiteral:
				_localctx = new StringLitContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(312);
				match(StringLiteral);
				}
				break;
			case NULL:
				_localctx = new NullLitContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(313);
				match(NULL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(MXParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MXParser.RPAREN, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(LPAREN);
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NULL) | (1L << NEW) | (1L << THIS) | (1L << LPAREN) | (1L << ADD) | (1L << SUB) | (1L << NOT) | (1L << BITNOT) | (1L << INC) | (1L << DEC) | (1L << BoolLiteral) | (1L << IntLiteral) | (1L << StringLiteral) | (1L << ID))) != 0)) {
				{
				setState(317);
				exprList();
				}
			}

			setState(320);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MXParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MXParser.COMMA, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			expr(0);
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(323);
				match(COMMA);
				setState(324);
				expr(0);
				}
				}
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatorContext extends ParserRuleContext {
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
	 
		public CreatorContext() { }
		public void copyFrom(CreatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NonArrayCreatorContext extends CreatorContext {
		public NonArrayTypeCreatorContext nonArrayTypeCreator() {
			return getRuleContext(NonArrayTypeCreatorContext.class,0);
		}
		public NonArrayCreatorContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitNonArrayCreator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayCreatorContext extends CreatorContext {
		public NonArrayTypeContext nonArrayType() {
			return getRuleContext(NonArrayTypeContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(MXParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(MXParser.LBRACK, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(MXParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(MXParser.RBRACK, i);
		}
		public ArrayCreatorContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitArrayCreator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ErrorCreatorContext extends CreatorContext {
		public NonArrayTypeContext nonArrayType() {
			return getRuleContext(NonArrayTypeContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(MXParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(MXParser.LBRACK, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(MXParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(MXParser.RBRACK, i);
		}
		public ErrorCreatorContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitErrorCreator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_creator);
		try {
			int _alt;
			setState(370);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				_localctx = new ErrorCreatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(330);
				nonArrayType();
				setState(335); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(331);
						match(LBRACK);
						setState(332);
						expr(0);
						setState(333);
						match(RBRACK);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(337); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(341); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(339);
						match(LBRACK);
						setState(340);
						match(RBRACK);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(343); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(349); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(345);
						match(LBRACK);
						setState(346);
						expr(0);
						setState(347);
						match(RBRACK);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(351); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				_localctx = new ArrayCreatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(353);
				nonArrayType();
				setState(358); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(354);
						match(LBRACK);
						setState(355);
						expr(0);
						setState(356);
						match(RBRACK);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(360); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(366);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(362);
						match(LBRACK);
						setState(363);
						match(RBRACK);
						}
						} 
					}
					setState(368);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
				}
				}
				break;
			case 3:
				_localctx = new NonArrayCreatorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(369);
				nonArrayTypeCreator();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonArrayTypeCreatorContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MXParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(MXParser.BOOL, 0); }
		public TerminalNode STRING() { return getToken(MXParser.STRING, 0); }
		public TerminalNode ID() { return getToken(MXParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(MXParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MXParser.RPAREN, 0); }
		public NonArrayTypeCreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonArrayTypeCreator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MXVisitor ) return ((MXVisitor<? extends T>)visitor).visitNonArrayTypeCreator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonArrayTypeCreatorContext nonArrayTypeCreator() throws RecognitionException {
		NonArrayTypeCreatorContext _localctx = new NonArrayTypeCreatorContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_nonArrayTypeCreator);
		try {
			setState(380);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
				match(INT);
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(373);
				match(BOOL);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(374);
				match(STRING);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(375);
				match(ID);
				setState(378);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(376);
					match(LPAREN);
					setState(377);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 30:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 15);
		case 2:
			return precpred(_ctx, 14);
		case 3:
			return precpred(_ctx, 13);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 10);
		case 7:
			return precpred(_ctx, 9);
		case 8:
			return precpred(_ctx, 8);
		case 9:
			return precpred(_ctx, 7);
		case 10:
			return precpred(_ctx, 6);
		case 11:
			return precpred(_ctx, 5);
		case 12:
			return precpred(_ctx, 23);
		case 13:
			return precpred(_ctx, 22);
		case 14:
			return precpred(_ctx, 21);
		case 15:
			return precpred(_ctx, 20);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\38\u0181\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\7\2L\n\2\f\2\16\2O\13\2\3\2\3\2\3\3"+
		"\3\3\3\3\5\3V\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5b\n\5\f\5"+
		"\16\5e\13\5\3\6\3\6\5\6i\n\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\5\tr\n\t\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\5\13{\n\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\7\16\u0087\n\16\f\16\16\16\u008a\13\16\5\16\u008c\n\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\7\22\u0099\n\22"+
		"\f\22\16\22\u009c\13\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00a6"+
		"\n\23\3\24\5\24\u00a9\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25"+
		"\u00b3\n\25\3\26\3\26\3\26\3\27\3\27\3\30\3\30\5\30\u00bc\n\30\3\31\3"+
		"\31\3\31\5\31\u00c1\n\31\3\31\3\31\5\31\u00c5\n\31\3\31\3\31\5\31\u00c9"+
		"\n\31\3\31\3\31\3\31\3\32\3\32\3\32\5\32\u00d1\n\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\34\3\34\5\34\u00db\n\34\3\34\3\34\5\34\u00df\n\34\3\35\3"+
		"\35\3\35\3\35\7\35\u00e5\n\35\f\35\16\35\u00e8\13\35\3\35\3\35\3\36\3"+
		"\36\3\36\5\36\u00ef\n\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \5 \u0105\n \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \7 \u0134\n \f \16 \u0137\13 \3!\3!\3!"+
		"\3!\5!\u013d\n!\3\"\3\"\5\"\u0141\n\"\3\"\3\"\3#\3#\3#\7#\u0148\n#\f#"+
		"\16#\u014b\13#\3$\3$\3$\3$\3$\6$\u0152\n$\r$\16$\u0153\3$\3$\6$\u0158"+
		"\n$\r$\16$\u0159\3$\3$\3$\3$\6$\u0160\n$\r$\16$\u0161\3$\3$\3$\3$\3$\6"+
		"$\u0169\n$\r$\16$\u016a\3$\3$\7$\u016f\n$\f$\16$\u0172\13$\3$\5$\u0175"+
		"\n$\3%\3%\3%\3%\3%\3%\5%\u017d\n%\5%\u017f\n%\3%\2\4\b>&\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFH\2\n\3\2\3\5\3\2"+
		"/\60\3\2\32\33\4\2\'\'**\3\2\34\36\3\2()\4\2\37 #$\3\2!\"\2\u019e\2M\3"+
		"\2\2\2\4U\3\2\2\2\6W\3\2\2\2\b[\3\2\2\2\nh\3\2\2\2\fj\3\2\2\2\16l\3\2"+
		"\2\2\20n\3\2\2\2\22s\3\2\2\2\24z\3\2\2\2\26|\3\2\2\2\30\u0080\3\2\2\2"+
		"\32\u0082\3\2\2\2\34\u008f\3\2\2\2\36\u0092\3\2\2\2 \u0094\3\2\2\2\"\u0096"+
		"\3\2\2\2$\u00a5\3\2\2\2&\u00a8\3\2\2\2(\u00ac\3\2\2\2*\u00b4\3\2\2\2,"+
		"\u00b7\3\2\2\2.\u00bb\3\2\2\2\60\u00bd\3\2\2\2\62\u00cd\3\2\2\2\64\u00d5"+
		"\3\2\2\2\66\u00de\3\2\2\28\u00e0\3\2\2\2:\u00ee\3\2\2\2<\u00f0\3\2\2\2"+
		">\u0104\3\2\2\2@\u013c\3\2\2\2B\u013e\3\2\2\2D\u0144\3\2\2\2F\u0174\3"+
		"\2\2\2H\u017e\3\2\2\2JL\5\4\3\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2"+
		"\2NP\3\2\2\2OM\3\2\2\2PQ\7\2\2\3Q\3\3\2\2\2RV\5\6\4\2SV\5\24\13\2TV\5"+
		"8\35\2UR\3\2\2\2US\3\2\2\2UT\3\2\2\2V\5\3\2\2\2WX\5\b\5\2XY\5\20\t\2Y"+
		"Z\7\31\2\2Z\7\3\2\2\2[\\\b\5\1\2\\]\5\n\6\2]c\3\2\2\2^_\f\4\2\2_`\7\24"+
		"\2\2`b\7\25\2\2a^\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\t\3\2\2\2ec\3"+
		"\2\2\2fi\5\f\7\2gi\5\16\b\2hf\3\2\2\2hg\3\2\2\2i\13\3\2\2\2jk\t\2\2\2"+
		"k\r\3\2\2\2lm\7\65\2\2m\17\3\2\2\2nq\7\65\2\2op\7.\2\2pr\5\22\n\2qo\3"+
		"\2\2\2qr\3\2\2\2r\21\3\2\2\2st\5> \2t\23\3\2\2\2uv\5\b\5\2vw\5\26\f\2"+
		"w{\3\2\2\2xy\7\7\2\2y{\5\26\f\2zu\3\2\2\2zx\3\2\2\2{\25\3\2\2\2|}\5\30"+
		"\r\2}~\5\32\16\2~\177\5 \21\2\177\27\3\2\2\2\u0080\u0081\7\65\2\2\u0081"+
		"\31\3\2\2\2\u0082\u008b\7\22\2\2\u0083\u0088\5\34\17\2\u0084\u0085\7\30"+
		"\2\2\u0085\u0087\5\34\17\2\u0086\u0084\3\2\2\2\u0087\u008a\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2"+
		"\2\2\u008b\u0083\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u008e\7\23\2\2\u008e\33\3\2\2\2\u008f\u0090\5\b\5\2\u0090\u0091\5\36"+
		"\20\2\u0091\35\3\2\2\2\u0092\u0093\7\65\2\2\u0093\37\3\2\2\2\u0094\u0095"+
		"\5\"\22\2\u0095!\3\2\2\2\u0096\u009a\7\26\2\2\u0097\u0099\5$\23\2\u0098"+
		"\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u009e\7\27\2\2\u009e"+
		"#\3\2\2\2\u009f\u00a6\5\"\22\2\u00a0\u00a6\5\6\4\2\u00a1\u00a6\5&\24\2"+
		"\u00a2\u00a6\5(\25\2\u00a3\u00a6\5.\30\2\u00a4\u00a6\5\64\33\2\u00a5\u009f"+
		"\3\2\2\2\u00a5\u00a0\3\2\2\2\u00a5\u00a1\3\2\2\2\u00a5\u00a2\3\2\2\2\u00a5"+
		"\u00a3\3\2\2\2\u00a5\u00a4\3\2\2\2\u00a6%\3\2\2\2\u00a7\u00a9\5> \2\u00a8"+
		"\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\7\31"+
		"\2\2\u00ab\'\3\2\2\2\u00ac\u00ad\7\b\2\2\u00ad\u00ae\7\22\2\2\u00ae\u00af"+
		"\5> \2\u00af\u00b0\7\23\2\2\u00b0\u00b2\5,\27\2\u00b1\u00b3\5*\26\2\u00b2"+
		"\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3)\3\2\2\2\u00b4\u00b5\7\t\2\2"+
		"\u00b5\u00b6\5,\27\2\u00b6+\3\2\2\2\u00b7\u00b8\5$\23\2\u00b8-\3\2\2\2"+
		"\u00b9\u00bc\5\60\31\2\u00ba\u00bc\5\62\32\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00ba\3\2\2\2\u00bc/\3\2\2\2\u00bd\u00be\7\n\2\2\u00be\u00c0\7\22\2\2"+
		"\u00bf\u00c1\5> \2\u00c0\u00bf\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2"+
		"\3\2\2\2\u00c2\u00c4\7\31\2\2\u00c3\u00c5\5> \2\u00c4\u00c3\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c8\7\31\2\2\u00c7\u00c9\5"+
		"> \2\u00c8\u00c7\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00cb\7\23\2\2\u00cb\u00cc\5,\27\2\u00cc\61\3\2\2\2\u00cd\u00ce\7\13"+
		"\2\2\u00ce\u00d0\7\22\2\2\u00cf\u00d1\5> \2\u00d0\u00cf\3\2\2\2\u00d0"+
		"\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\7\23\2\2\u00d3\u00d4\5"+
		",\27\2\u00d4\63\3\2\2\2\u00d5\u00d6\5\66\34\2\u00d6\u00d7\7\31\2\2\u00d7"+
		"\65\3\2\2\2\u00d8\u00da\7\16\2\2\u00d9\u00db\5> \2\u00da\u00d9\3\2\2\2"+
		"\u00da\u00db\3\2\2\2\u00db\u00df\3\2\2\2\u00dc\u00df\7\f\2\2\u00dd\u00df"+
		"\7\r\2\2\u00de\u00d8\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df"+
		"\67\3\2\2\2\u00e0\u00e1\7\20\2\2\u00e1\u00e2\7\65\2\2\u00e2\u00e6\7\26"+
		"\2\2\u00e3\u00e5\5:\36\2\u00e4\u00e3\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6"+
		"\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9\3\2\2\2\u00e8\u00e6\3\2"+
		"\2\2\u00e9\u00ea\7\27\2\2\u00ea9\3\2\2\2\u00eb\u00ef\5\6\4\2\u00ec\u00ef"+
		"\5\24\13\2\u00ed\u00ef\5<\37\2\u00ee\u00eb\3\2\2\2\u00ee\u00ec\3\2\2\2"+
		"\u00ee\u00ed\3\2\2\2\u00ef;\3\2\2\2\u00f0\u00f1\7\65\2\2\u00f1\u00f2\5"+
		"\32\16\2\u00f2\u00f3\5 \21\2\u00f3=\3\2\2\2\u00f4\u00f5\b \1\2\u00f5\u00f6"+
		"\t\3\2\2\u00f6\u0105\5> \25\u00f7\u00f8\t\4\2\2\u00f8\u0105\5> \24\u00f9"+
		"\u00fa\t\5\2\2\u00fa\u0105\5> \23\u00fb\u00fc\7\17\2\2\u00fc\u0105\5F"+
		"$\2\u00fd\u0105\5@!\2\u00fe\u0105\7\21\2\2\u00ff\u0105\7\65\2\2\u0100"+
		"\u0101\7\22\2\2\u0101\u0102\5> \2\u0102\u0103\7\23\2\2\u0103\u0105\3\2"+
		"\2\2\u0104\u00f4\3\2\2\2\u0104\u00f7\3\2\2\2\u0104\u00f9\3\2\2\2\u0104"+
		"\u00fb\3\2\2\2\u0104\u00fd\3\2\2\2\u0104\u00fe\3\2\2\2\u0104\u00ff\3\2"+
		"\2\2\u0104\u0100\3\2\2\2\u0105\u0135\3\2\2\2\u0106\u0107\f\21\2\2\u0107"+
		"\u0108\t\6\2\2\u0108\u0134\5> \22\u0109\u010a\f\20\2\2\u010a\u010b\t\4"+
		"\2\2\u010b\u0134\5> \21\u010c\u010d\f\17\2\2\u010d\u010e\t\7\2\2\u010e"+
		"\u0134\5> \20\u010f\u0110\f\16\2\2\u0110\u0111\t\b\2\2\u0111\u0134\5>"+
		" \17\u0112\u0113\f\r\2\2\u0113\u0114\t\t\2\2\u0114\u0134\5> \16\u0115"+
		"\u0116\f\f\2\2\u0116\u0117\7-\2\2\u0117\u0134\5> \r\u0118\u0119\f\13\2"+
		"\2\u0119\u011a\7,\2\2\u011a\u0134\5> \f\u011b\u011c\f\n\2\2\u011c\u011d"+
		"\7+\2\2\u011d\u0134\5> \13\u011e\u011f\f\t\2\2\u011f\u0120\7%\2\2\u0120"+
		"\u0134\5> \n\u0121\u0122\f\b\2\2\u0122\u0123\7&\2\2\u0123\u0134\5> \t"+
		"\u0124\u0125\f\7\2\2\u0125\u0126\7.\2\2\u0126\u0134\5> \7\u0127\u0128"+
		"\f\31\2\2\u0128\u0134\t\3\2\2\u0129\u012a\f\30\2\2\u012a\u0134\5B\"\2"+
		"\u012b\u012c\f\27\2\2\u012c\u012d\7\24\2\2\u012d\u012e\5> \2\u012e\u012f"+
		"\7\25\2\2\u012f\u0134\3\2\2\2\u0130\u0131\f\26\2\2\u0131\u0132\7\61\2"+
		"\2\u0132\u0134\7\65\2\2\u0133\u0106\3\2\2\2\u0133\u0109\3\2\2\2\u0133"+
		"\u010c\3\2\2\2\u0133\u010f\3\2\2\2\u0133\u0112\3\2\2\2\u0133\u0115\3\2"+
		"\2\2\u0133\u0118\3\2\2\2\u0133\u011b\3\2\2\2\u0133\u011e\3\2\2\2\u0133"+
		"\u0121\3\2\2\2\u0133\u0124\3\2\2\2\u0133\u0127\3\2\2\2\u0133\u0129\3\2"+
		"\2\2\u0133\u012b\3\2\2\2\u0133\u0130\3\2\2\2\u0134\u0137\3\2\2\2\u0135"+
		"\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136?\3\2\2\2\u0137\u0135\3\2\2\2"+
		"\u0138\u013d\7\62\2\2\u0139\u013d\7\63\2\2\u013a\u013d\7\64\2\2\u013b"+
		"\u013d\7\6\2\2\u013c\u0138\3\2\2\2\u013c\u0139\3\2\2\2\u013c\u013a\3\2"+
		"\2\2\u013c\u013b\3\2\2\2\u013dA\3\2\2\2\u013e\u0140\7\22\2\2\u013f\u0141"+
		"\5D#\2\u0140\u013f\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\3\2\2\2\u0142"+
		"\u0143\7\23\2\2\u0143C\3\2\2\2\u0144\u0149\5> \2\u0145\u0146\7\30\2\2"+
		"\u0146\u0148\5> \2\u0147\u0145\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147"+
		"\3\2\2\2\u0149\u014a\3\2\2\2\u014aE\3\2\2\2\u014b\u0149\3\2\2\2\u014c"+
		"\u0151\5\n\6\2\u014d\u014e\7\24\2\2\u014e\u014f\5> \2\u014f\u0150\7\25"+
		"\2\2\u0150\u0152\3\2\2\2\u0151\u014d\3\2\2\2\u0152\u0153\3\2\2\2\u0153"+
		"\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0156\7\24"+
		"\2\2\u0156\u0158\7\25\2\2\u0157\u0155\3\2\2\2\u0158\u0159\3\2\2\2\u0159"+
		"\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015f\3\2\2\2\u015b\u015c\7\24"+
		"\2\2\u015c\u015d\5> \2\u015d\u015e\7\25\2\2\u015e\u0160\3\2\2\2\u015f"+
		"\u015b\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u015f\3\2\2\2\u0161\u0162\3\2"+
		"\2\2\u0162\u0175\3\2\2\2\u0163\u0168\5\n\6\2\u0164\u0165\7\24\2\2\u0165"+
		"\u0166\5> \2\u0166\u0167\7\25\2\2\u0167\u0169\3\2\2\2\u0168\u0164\3\2"+
		"\2\2\u0169\u016a\3\2\2\2\u016a\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b"+
		"\u0170\3\2\2\2\u016c\u016d\7\24\2\2\u016d\u016f\7\25\2\2\u016e\u016c\3"+
		"\2\2\2\u016f\u0172\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2\2\2\u0171"+
		"\u0175\3\2\2\2\u0172\u0170\3\2\2\2\u0173\u0175\5H%\2\u0174\u014c\3\2\2"+
		"\2\u0174\u0163\3\2\2\2\u0174\u0173\3\2\2\2\u0175G\3\2\2\2\u0176\u017f"+
		"\7\4\2\2\u0177\u017f\7\3\2\2\u0178\u017f\7\5\2\2\u0179\u017c\7\65\2\2"+
		"\u017a\u017b\7\22\2\2\u017b\u017d\7\23\2\2\u017c\u017a\3\2\2\2\u017c\u017d"+
		"\3\2\2\2\u017d\u017f\3\2\2\2\u017e\u0176\3\2\2\2\u017e\u0177\3\2\2\2\u017e"+
		"\u0178\3\2\2\2\u017e\u0179\3\2\2\2\u017fI\3\2\2\2%MUchqz\u0088\u008b\u009a"+
		"\u00a5\u00a8\u00b2\u00bb\u00c0\u00c4\u00c8\u00d0\u00da\u00de\u00e6\u00ee"+
		"\u0104\u0133\u0135\u013c\u0140\u0149\u0153\u0159\u0161\u016a\u0170\u0174"+
		"\u017c\u017e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}