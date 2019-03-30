// Generated from H:/IDEA/Mx_compiler/src/Mx_grammar\MX.g4 by ANTLR 4.7.2
package Mx_grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MXLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"BOOL", "INT", "STRING", "NULL", "VOID", "IF", "ELSE", "FOR", "WHILE", 
			"BREAK", "CONTINUE", "RETURN", "NEW", "CLASS", "THIS", "LPAREN", "RPAREN", 
			"LBRACK", "RBRACK", "LBRACE", "RBRACE", "COMMA", "SEMI", "ADD", "SUB", 
			"MUL", "DIV", "MOD", "LT", "GT", "EQ", "NEQ", "GTE", "LTE", "AND", "OR", 
			"NOT", "LSFT", "RSFT", "BITNOT", "BITOR", "BITXOR", "BITAND", "ASSIGN", 
			"INC", "DEC", "DOT", "BoolLiteral", "TRUE", "FALSE", "IntLiteral", "StringLiteral", 
			"StringCharacters", "StringCharacter", "EscapeSequence", "ID", "LineComment", 
			"MultilineComment", "WS"
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


	public MXLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MX.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\28\u0162\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3\'\3"+
		"\'\3\'\3(\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3"+
		"\60\3\61\3\61\5\61\u0117\n\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\3\64\6\64\u0125\n\64\r\64\16\64\u0126\3\65\3\65\5\65\u012b"+
		"\n\65\3\65\3\65\3\66\6\66\u0130\n\66\r\66\16\66\u0131\3\67\3\67\5\67\u0136"+
		"\n\67\38\38\39\39\79\u013c\n9\f9\169\u013f\139\3:\3:\3:\3:\7:\u0145\n"+
		":\f:\16:\u0148\13:\3:\3:\3:\3:\3;\3;\3;\3;\7;\u0152\n;\f;\16;\u0155\13"+
		";\3;\3;\3;\3;\3;\3<\6<\u015d\n<\r<\16<\u015e\3<\3<\4\u0146\u0153\2=\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37="+
		" ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\2e\2g\63i\64k\2m\2o\2q"+
		"\65s\66u\67w8\3\2\7\3\2\62;\4\2$$^^\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17"+
		"\17\"\"\2\u0165\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2"+
		"_\3\2\2\2\2a\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3"+
		"\2\2\2\2w\3\2\2\2\3y\3\2\2\2\5~\3\2\2\2\7\u0082\3\2\2\2\t\u0089\3\2\2"+
		"\2\13\u008e\3\2\2\2\r\u0093\3\2\2\2\17\u0096\3\2\2\2\21\u009b\3\2\2\2"+
		"\23\u009f\3\2\2\2\25\u00a5\3\2\2\2\27\u00ab\3\2\2\2\31\u00b4\3\2\2\2\33"+
		"\u00bb\3\2\2\2\35\u00bf\3\2\2\2\37\u00c5\3\2\2\2!\u00ca\3\2\2\2#\u00cc"+
		"\3\2\2\2%\u00ce\3\2\2\2\'\u00d0\3\2\2\2)\u00d2\3\2\2\2+\u00d4\3\2\2\2"+
		"-\u00d6\3\2\2\2/\u00d8\3\2\2\2\61\u00da\3\2\2\2\63\u00dc\3\2\2\2\65\u00de"+
		"\3\2\2\2\67\u00e0\3\2\2\29\u00e2\3\2\2\2;\u00e4\3\2\2\2=\u00e6\3\2\2\2"+
		"?\u00e8\3\2\2\2A\u00eb\3\2\2\2C\u00ee\3\2\2\2E\u00f1\3\2\2\2G\u00f4\3"+
		"\2\2\2I\u00f7\3\2\2\2K\u00fa\3\2\2\2M\u00fc\3\2\2\2O\u00ff\3\2\2\2Q\u0102"+
		"\3\2\2\2S\u0104\3\2\2\2U\u0106\3\2\2\2W\u0108\3\2\2\2Y\u010a\3\2\2\2["+
		"\u010c\3\2\2\2]\u010f\3\2\2\2_\u0112\3\2\2\2a\u0116\3\2\2\2c\u0118\3\2"+
		"\2\2e\u011d\3\2\2\2g\u0124\3\2\2\2i\u0128\3\2\2\2k\u012f\3\2\2\2m\u0135"+
		"\3\2\2\2o\u0137\3\2\2\2q\u0139\3\2\2\2s\u0140\3\2\2\2u\u014d\3\2\2\2w"+
		"\u015c\3\2\2\2yz\7d\2\2z{\7q\2\2{|\7q\2\2|}\7n\2\2}\4\3\2\2\2~\177\7k"+
		"\2\2\177\u0080\7p\2\2\u0080\u0081\7v\2\2\u0081\6\3\2\2\2\u0082\u0083\7"+
		"u\2\2\u0083\u0084\7v\2\2\u0084\u0085\7t\2\2\u0085\u0086\7k\2\2\u0086\u0087"+
		"\7p\2\2\u0087\u0088\7i\2\2\u0088\b\3\2\2\2\u0089\u008a\7p\2\2\u008a\u008b"+
		"\7w\2\2\u008b\u008c\7n\2\2\u008c\u008d\7n\2\2\u008d\n\3\2\2\2\u008e\u008f"+
		"\7x\2\2\u008f\u0090\7q\2\2\u0090\u0091\7k\2\2\u0091\u0092\7f\2\2\u0092"+
		"\f\3\2\2\2\u0093\u0094\7k\2\2\u0094\u0095\7h\2\2\u0095\16\3\2\2\2\u0096"+
		"\u0097\7g\2\2\u0097\u0098\7n\2\2\u0098\u0099\7u\2\2\u0099\u009a\7g\2\2"+
		"\u009a\20\3\2\2\2\u009b\u009c\7h\2\2\u009c\u009d\7q\2\2\u009d\u009e\7"+
		"t\2\2\u009e\22\3\2\2\2\u009f\u00a0\7y\2\2\u00a0\u00a1\7j\2\2\u00a1\u00a2"+
		"\7k\2\2\u00a2\u00a3\7n\2\2\u00a3\u00a4\7g\2\2\u00a4\24\3\2\2\2\u00a5\u00a6"+
		"\7d\2\2\u00a6\u00a7\7t\2\2\u00a7\u00a8\7g\2\2\u00a8\u00a9\7c\2\2\u00a9"+
		"\u00aa\7m\2\2\u00aa\26\3\2\2\2\u00ab\u00ac\7e\2\2\u00ac\u00ad\7q\2\2\u00ad"+
		"\u00ae\7p\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7p\2\2"+
		"\u00b1\u00b2\7w\2\2\u00b2\u00b3\7g\2\2\u00b3\30\3\2\2\2\u00b4\u00b5\7"+
		"t\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7\7v\2\2\u00b7\u00b8\7w\2\2\u00b8\u00b9"+
		"\7t\2\2\u00b9\u00ba\7p\2\2\u00ba\32\3\2\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd"+
		"\7g\2\2\u00bd\u00be\7y\2\2\u00be\34\3\2\2\2\u00bf\u00c0\7e\2\2\u00c0\u00c1"+
		"\7n\2\2\u00c1\u00c2\7c\2\2\u00c2\u00c3\7u\2\2\u00c3\u00c4\7u\2\2\u00c4"+
		"\36\3\2\2\2\u00c5\u00c6\7v\2\2\u00c6\u00c7\7j\2\2\u00c7\u00c8\7k\2\2\u00c8"+
		"\u00c9\7u\2\2\u00c9 \3\2\2\2\u00ca\u00cb\7*\2\2\u00cb\"\3\2\2\2\u00cc"+
		"\u00cd\7+\2\2\u00cd$\3\2\2\2\u00ce\u00cf\7]\2\2\u00cf&\3\2\2\2\u00d0\u00d1"+
		"\7_\2\2\u00d1(\3\2\2\2\u00d2\u00d3\7}\2\2\u00d3*\3\2\2\2\u00d4\u00d5\7"+
		"\177\2\2\u00d5,\3\2\2\2\u00d6\u00d7\7.\2\2\u00d7.\3\2\2\2\u00d8\u00d9"+
		"\7=\2\2\u00d9\60\3\2\2\2\u00da\u00db\7-\2\2\u00db\62\3\2\2\2\u00dc\u00dd"+
		"\7/\2\2\u00dd\64\3\2\2\2\u00de\u00df\7,\2\2\u00df\66\3\2\2\2\u00e0\u00e1"+
		"\7\61\2\2\u00e18\3\2\2\2\u00e2\u00e3\7\'\2\2\u00e3:\3\2\2\2\u00e4\u00e5"+
		"\7>\2\2\u00e5<\3\2\2\2\u00e6\u00e7\7@\2\2\u00e7>\3\2\2\2\u00e8\u00e9\7"+
		"?\2\2\u00e9\u00ea\7?\2\2\u00ea@\3\2\2\2\u00eb\u00ec\7#\2\2\u00ec\u00ed"+
		"\7?\2\2\u00edB\3\2\2\2\u00ee\u00ef\7@\2\2\u00ef\u00f0\7?\2\2\u00f0D\3"+
		"\2\2\2\u00f1\u00f2\7>\2\2\u00f2\u00f3\7?\2\2\u00f3F\3\2\2\2\u00f4\u00f5"+
		"\7(\2\2\u00f5\u00f6\7(\2\2\u00f6H\3\2\2\2\u00f7\u00f8\7~\2\2\u00f8\u00f9"+
		"\7~\2\2\u00f9J\3\2\2\2\u00fa\u00fb\7#\2\2\u00fbL\3\2\2\2\u00fc\u00fd\7"+
		">\2\2\u00fd\u00fe\7>\2\2\u00feN\3\2\2\2\u00ff\u0100\7@\2\2\u0100\u0101"+
		"\7@\2\2\u0101P\3\2\2\2\u0102\u0103\7\u0080\2\2\u0103R\3\2\2\2\u0104\u0105"+
		"\7~\2\2\u0105T\3\2\2\2\u0106\u0107\7`\2\2\u0107V\3\2\2\2\u0108\u0109\7"+
		"(\2\2\u0109X\3\2\2\2\u010a\u010b\7?\2\2\u010bZ\3\2\2\2\u010c\u010d\7-"+
		"\2\2\u010d\u010e\7-\2\2\u010e\\\3\2\2\2\u010f\u0110\7/\2\2\u0110\u0111"+
		"\7/\2\2\u0111^\3\2\2\2\u0112\u0113\7\60\2\2\u0113`\3\2\2\2\u0114\u0117"+
		"\5c\62\2\u0115\u0117\5e\63\2\u0116\u0114\3\2\2\2\u0116\u0115\3\2\2\2\u0117"+
		"b\3\2\2\2\u0118\u0119\7v\2\2\u0119\u011a\7t\2\2\u011a\u011b\7w\2\2\u011b"+
		"\u011c\7g\2\2\u011cd\3\2\2\2\u011d\u011e\7h\2\2\u011e\u011f\7c\2\2\u011f"+
		"\u0120\7n\2\2\u0120\u0121\7u\2\2\u0121\u0122\7g\2\2\u0122f\3\2\2\2\u0123"+
		"\u0125\t\2\2\2\u0124\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0124\3\2"+
		"\2\2\u0126\u0127\3\2\2\2\u0127h\3\2\2\2\u0128\u012a\7$\2\2\u0129\u012b"+
		"\5k\66\2\u012a\u0129\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c"+
		"\u012d\7$\2\2\u012dj\3\2\2\2\u012e\u0130\5m\67\2\u012f\u012e\3\2\2\2\u0130"+
		"\u0131\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132l\3\2\2\2"+
		"\u0133\u0136\n\3\2\2\u0134\u0136\5o8\2\u0135\u0133\3\2\2\2\u0135\u0134"+
		"\3\2\2\2\u0136n\3\2\2\2\u0137\u0138\7^\2\2\u0138p\3\2\2\2\u0139\u013d"+
		"\t\4\2\2\u013a\u013c\t\5\2\2\u013b\u013a\3\2\2\2\u013c\u013f\3\2\2\2\u013d"+
		"\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013er\3\2\2\2\u013f\u013d\3\2\2\2"+
		"\u0140\u0141\7\61\2\2\u0141\u0142\7\61\2\2\u0142\u0146\3\2\2\2\u0143\u0145"+
		"\13\2\2\2\u0144\u0143\3\2\2\2\u0145\u0148\3\2\2\2\u0146\u0147\3\2\2\2"+
		"\u0146\u0144\3\2\2\2\u0147\u0149\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u014a"+
		"\7\f\2\2\u014a\u014b\3\2\2\2\u014b\u014c\b:\2\2\u014ct\3\2\2\2\u014d\u014e"+
		"\7\61\2\2\u014e\u014f\7,\2\2\u014f\u0153\3\2\2\2\u0150\u0152\13\2\2\2"+
		"\u0151\u0150\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0154\3\2\2\2\u0153\u0151"+
		"\3\2\2\2\u0154\u0156\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u0157\7,\2\2\u0157"+
		"\u0158\7\61\2\2\u0158\u0159\3\2\2\2\u0159\u015a\b;\2\2\u015av\3\2\2\2"+
		"\u015b\u015d\t\6\2\2\u015c\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015c"+
		"\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0161\b<\3\2\u0161"+
		"x\3\2\2\2\f\2\u0116\u0126\u012a\u0131\u0135\u013d\u0146\u0153\u015e\4"+
		"\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}