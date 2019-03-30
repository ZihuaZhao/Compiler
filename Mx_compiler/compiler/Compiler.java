package Mx_compiler.compiler;

import Mx_compiler.Scope.Scope;
import Mx_compiler.frontend.AstBuilder;
import Mx_compiler.frontend.ClassVarMemScanner;
import Mx_compiler.frontend.FuncScopeScanner;
import Mx_compiler.frontend.GlobalScopePreScanner;
import Mx_compiler.node.ProgramNode;
import Mx_grammar.MXLexer;
import Mx_grammar.MXParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.InputStream;

public class Compiler {
    private InputStream inS;
    private ProgramNode ast;

    public Compiler(InputStream inS){
        this.inS = inS;
    }

    private void buildAst() throws Exception{
        CharStream input = CharStreams.fromStream(inS);
        MXLexer lexer = new MXLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MXParser parser = new MXParser(tokens);
        ParseTree tree = parser.program();
        AstBuilder astbuilder = new AstBuilder();
        ast = (ProgramNode) astbuilder.visit(tree);
    }

    public void compile() throws Exception{
        buildAst();
        GlobalScopePreScanner globalScopePreScanner = new GlobalScopePreScanner();
        globalScopePreScanner.visit(ast);
        Scope globalScope = globalScopePreScanner.getScope();
        ClassVarMemScanner cs = new ClassVarMemScanner(globalScope);
        cs.visit(ast);
        FuncScopeScanner fs = new FuncScopeScanner(globalScope);
        fs.visit(ast);

    }

}
