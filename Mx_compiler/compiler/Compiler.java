package Mx_compiler.compiler;

import Mx_compiler.IR.IRRoot;
import Mx_compiler.Scope.Scope;
import Mx_compiler.backend.*;
import Mx_compiler.frontend.*;
import Mx_compiler.node.ProgramNode;
import Mx_grammar.MXLexer;
import Mx_grammar.MXParser;
import Mx_grammar.SyntaxErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Compiler {
    private InputStream inS;
    private ProgramNode ast;
    private PrintStream irOut;
    private PrintStream nasmOut;
    public boolean flag = false;


    public Compiler(InputStream inS , PrintStream outS){
        this.inS = inS;
        this.nasmOut = outS;
    }

    public Compiler(InputStream inS , PrintStream irOut , PrintStream nasmOut){
        this.inS = inS;
        this.irOut = irOut;
        this.nasmOut = nasmOut;
    }

    private void buildAst() throws Exception{
        CharStream input = CharStreams.fromStream(inS);
        MXLexer lexer = new MXLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MXParser parser = new MXParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new SyntaxErrorListener());
        ParseTree tree = parser.program();
        AstBuilder astbuilder = new AstBuilder(nasmOut);
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
        IRBuilder irBuilder = new IRBuilder(globalScope);
        irBuilder.visit(ast);
        IRRoot irRoot = irBuilder.getIrRoot();
        new TwoRegTransform(irRoot).run();
        new FuncInlineProcessor(irRoot).run();
        //new IRPrinter(irOut).visit(irRoot);
        new StaticDataProcessor(irRoot).run();
        new RegPreprocessor(irRoot).run();
        new RegLiveliness(irRoot).run();
        new RegAllocator(irRoot).run();
        new NASMTransform(irRoot).run();
        new ExtraInstOptim(irRoot).run();
        new NASMPrinter(nasmOut).visit(irRoot);
    }
}
