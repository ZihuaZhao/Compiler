grammar MX;

import MXlexer;

program:context* EOF;

context:
	varDeclField
	|funcDeclField
	|classDeclField
	;

varDeclField:type varField SEMI;

type:
    type LBRACK RBRACK
    |nonArrayType
    ;

nonArrayType:
	varType
	|classType
	;

varType:
	INT
	|BOOL
	|STRING
	;

classType:ID;

varField:ID (ASSIGN varInitField)?;

varInitField:expr;

funcDeclField:
	type funcField
	|VOID funcField
	;

funcField:funcName paramField funcBody;

funcName:ID;

paramField:LPAREN (param(COMMA param)*)? RPAREN;

param:type paraname;

paraname:ID;

funcBody:block;

block:LBRACE statement* RBRACE;

statement:
	block               #blockStmt
	|varDeclField       #varDeclStmt
	|exprField          #exprStmt
	|ifField            #ifStmt
	|loopField          #loopStmt
	|jumpField          #jumpStmt
	;

exprField:expr? SEMI;

ifField:IF LPAREN cond = expr RPAREN body elseField?;

elseField:ELSE body;

body:statement;

loopField:
	forField
	|whileField
	;

forField:FOR LPAREN init = expr? SEMI cond = expr? SEMI step = expr? RPAREN body;

whileField:WHILE LPAREN cond = expr? RPAREN body;

jumpField:jumpInst SEMI;

jumpInst:
	RETURN expr?                #returnInst
	|BREAK                      #breakInst
	|CONTINUE                   #continueInst
	;

classDeclField:CLASS ID LBRACE classBody* RBRACE;

classBody:
	varDeclField
	|funcDeclField
	|classBuildField
	;

classBuildField:ID paramField funcBody;

expr:
	expr op = (INC | DEC)                                           #suffixIncDec
	|expr arguments                                                 #functionCall
	|arr = expr LBRACK sub = expr RBRACK                            #subscriptExpr
	|expr DOT ID                                                    #memberCall
	|<assoc = right> op = (INC | DEC) expr                          #prefixExpr
	|<assoc = right> op = (ADD | SUB) expr                          #prefixExpr
	|<assoc = right> op = (NOT | BITNOT) expr                       #prefixExpr
	|<assoc = right> NEW creator                                    #newExpr
	|left = expr op = (MUL | DIV | MOD) right = expr                #binaryExpr
	|left = expr op = (ADD | SUB) right = expr                      #binaryExpr
	|left = expr op = (LSFT | RSFT) right = expr                    #binaryExpr
	|left = expr op = (LT | GT | LTE | GTE) right = expr            #binaryExpr
	|left = expr op = (EQ | NEQ) right = expr                       #binaryExpr
	|left = expr op = BITAND right = expr                           #binaryExpr
	|left = expr op = BITXOR right = expr                           #binaryExpr
	|left = expr op = BITOR right = expr                            #binaryExpr
	|left = expr op = AND right = expr                              #binaryExpr
	|left = expr op = OR right = expr                               #binaryExpr
	|<assoc = right> left = expr op = ASSIGN right = expr           #assignExpr
	|literal                                                        #lit
	|THIS                                                           #this
	|ID                                                             #id
	|LPAREN expr RPAREN                                             #parenExpr
	;

literal:
	BoolLiteral                     #boolLit
	|IntLiteral                     #intLit
	|StringLiteral                  #stringLit
	|NULL                           #nullLit
	;

arguments:LPAREN exprList? RPAREN;

exprList:expr(COMMA expr)*;

creator
    : nonArrayType (LBRACK expr RBRACK)+ (LBRACK RBRACK)+ (LBRACK expr RBRACK)+         #errorCreator
    | nonArrayType (LBRACK expr RBRACK)+ (LBRACK RBRACK)*                               #arrayCreator
    | nonArrayTypeCreator                                                               #nonArrayCreator
    ;

nonArrayTypeCreator
    : INT
    | BOOL
    | STRING
    | ID (LPAREN RPAREN)?
    ;