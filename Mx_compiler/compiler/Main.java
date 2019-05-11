package Mx_compiler.compiler;

import java.io.*;

public class Main {
    public static void main(String[] args)throws Exception{
        //File infile = new File("H:\\IDEA\\Mx_compiler\\src\\text.txt");
        //File irOutFile = new File("H:\\IDEA\\Mx_compiler\\src\\outFile.txt");
        //File nasmOutFile = new File("H:\\IDEA\\Mx_compiler\\src\\nasmOutFile.txt");
        //InputStream inS = new FileInputStream(infile);
        //PrintStream irOut = new PrintStream(new FileOutputStream(irOutFile));
        //PrintStream nasmOut = new PrintStream(new FileOutputStream(nasmOutFile));
        InputStream inS = System.in;
        OutputStream outS = System.out;
        Compiler compiler = new Compiler(inS , outS);
        compiler.compile();
    }
}
