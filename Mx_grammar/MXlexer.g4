lexer grammar MXlexer;

//reserved keywords;
BOOL:'bool';
INT:'int';
STRING:'string';
NULL:'null';
VOID:'void';
IF:'if';
ELSE:'else';
FOR:'for';
WHILE:'while';
BREAK:'break';
CONTINUE:'continue';
RETURN:'return';
NEW:'new';
CLASS:'class';
THIS:'this';

//symbols
//seperator
LPAREN:'(';
RPAREN:')';
LBRACK:'[';
RBRACK:']';
LBRACE:'{';
RBRACE:'}';
COMMA:',';
SEMI:';';
//operator
//mathmetic
ADD:'+';
SUB:'-';
MUL:'*';
DIV:'/';
MOD:'%';
//compare
LT:'<';
GT:'>';
EQ:'==';
NEQ:'!=';
GTE:'>=';
LTE:'<=';
//logic
AND:'&&';
OR:'||';
NOT:'!';
//bit operater
LSFT:'<<';
RSFT:'>>';
BITNOT:'~';
BITOR:'|';
BITXOR:'^';
BITAND:'&';
//assign
ASSIGN:'=';
//self operator
INC:'++';
DEC:'--';
//dot
DOT:'.';

//defines
BoolLiteral:TRUE|FALSE;
fragment TRUE:'true';
fragment FALSE:'false';

IntLiteral:[0-9]+;

StringLiteral:'"'StringCharacters?'"' | '\'' StringCharacters?'\'';
fragment StringCharacters:StringCharacter+;
fragment StringCharacter:~["\\]|EscapeSequence;
fragment EscapeSequence:'\\'[btnfr"'\\];

ID:[a-zA-Z][0-9a-zA-Z_]*;

LineComment:'//'.*?'\n'->skip;
MultilineComment:'/*'.*?'*/'->skip;

WS:[ \t\r\n]+->channel(HIDDEN);

