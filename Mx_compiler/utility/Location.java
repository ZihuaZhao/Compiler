package Mx_compiler.utility;
import Mx_grammar.MXParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;


public class Location {
    private final int line;
    private final int col;

    public Location(int line , int col){
        this.line = line;
        this.col = col;
    }

    public Location(Token token) {
        this.line = token.getLine();
        this.col = token.getCharPositionInLine();
    }

    public Location(MXParser.ProgramContext ctx){
        this.line = ctx.start.getLine();
        this.col = ctx.start.getCharPositionInLine();
    }

    static public Location fromCtx(MXParser.ProgramContext ctx){
        return new Location(ctx.start.getLine() , ctx.start.getCharPositionInLine());
    }

    static public Location fromCtx(ParserRuleContext ctx){
        return new Location(ctx.getStart());
    }
}
