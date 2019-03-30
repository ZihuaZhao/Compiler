package Mx_compiler.compiler;

import java.io.*;

public class Main {
    public static void main(String[] args)throws Exception{
//        File infile = new File("H:\\IDEA\\Mx_compiler\\src\\text.txt");
//        File astOutFile = new File("H:\\IDEA\\Mx_compiler\\src\\astOutFile.txt");
        InputStream inS = System.in;
//        PrintStream out = new PrintStream(new FileOutputStream(astOutFile));
        Compiler compiler = new Compiler(inS);
        compiler.compile();
    }
}
