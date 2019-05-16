package Mx_compiler.backend;

import Mx_compiler.IR.*;
import Mx_compiler.visitor.IRVisitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static Mx_compiler.IR.IRBinaryOperation.IRBinaryOp.*;

public class NASMPrinter implements IRVisitor {
    private PrintStream out;
    private Map<String , Integer> idCounter = new HashMap<>();
    private Map<Object , String> idMap = new HashMap<>();
    private PhysicalReg preg0 , preg1;

    public NASMPrinter(PrintStream out){
        this.out = out;
    }

    public boolean isBSection , isDSection;

    private String newId(String id){
        int nowCnt = idCounter.getOrDefault(id , 0) + 1;
        idCounter.put(id , nowCnt);
        return id + "_" + nowCnt;
    }

    private String dataId(StaticData data){
        String id = idMap.get(data);
        if(id == null){
            id = "__static_data_" + newId(data.getName());
            idMap.put(data , id);
        }
        return id;
    }

    private String blockId(BasicBlock block){
        String id = idMap.get(block);
        if(id == null){
            id = "_block_" + newId(block.getName());
            idMap.put(block , id);
        }
        return id;
    }

    @Override
    public void visit(IRRoot node){
        preg0 = node.getPreg0();
        preg1 = node.getPreg1();
        idMap.put(node.getFuncs().get("main").getStartBlock() , "main");
        out.println("\t\tglobal\tmain");
        out.println();
        out.println("\t\textern\tmalloc");
        out.println();
        if(node.getStaticDataList().size() > 0) {
            isBSection = true;
            out.println("\t\tsection\t.bss");
            for (StaticData staticData : node.getStaticDataList()) {
                staticData.accept(this);
            }
            out.println();
            isBSection = false;
        }
        if(node.getStaticStringMap().size() > 0){
            isDSection = true;
            out.println("\t\tsection\t.data");
            for(StaticString staticString : node.getStaticStringMap().values()){
                staticString.accept(this);
            }
            out.println();
            isDSection = false;
        }
        out.println("\t\tsection\t.text\n");
        for(IRFunc irFunc : node.getFuncs().values()){
            irFunc.accept(this);
        }
        out.println();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Mx_compiler/c2nasm/builtin_functions.asm"));
            String line;
            while((line = bufferedReader.readLine()) != null){
                out.println(line);
            }
        }
        catch(IOException error){
            throw new Error("IO exception for builtin");
        }
    }

    @Override
    public void visit(IRFunc node){
        out.printf("# function %s\n\n" , node.getName());
        int blockIndex = 0;
        for(BasicBlock block : node.getReversePostOrder()){
            block.accept(this);
            ++blockIndex;
        }
    }

    @Override
    public void visit(BasicBlock node){
        out.printf("%s:\n" , blockId(node));
        for(IRInstruction inst = node.getFirstInst() ; inst != null ; inst = inst.getSuccInst()){
            inst.accept(this);
        }
        out.println();
    }

    @Override
    public void visit(IRBranch node){
        if(node.getCond() instanceof Imm){
            int boolValue = ((Imm) node.getCond()).getValue();
            out.printf("\t\tjmp\t\t%s\n" , boolValue == 1 ? blockId(node.getThenBlock()) : blockId(node.getElseBlock()));
            return;
        }
        out.print("\t\tcmp\t\t");
        node.getCond().accept(this);
        out.println(", 1");
        out.printf("\t\tje\t\t%s\n" , blockId(node.getThenBlock()));
        if(node.getElseBlock().getPostNum() + 1 == node.getBlock().getPostNum()) return;
        out.printf("\t\tjmp\t\t%s\n" , blockId(node.getElseBlock()));
    }

    @Override
    public void visit(IRJump node){
        if(node.getTarBlock().getPostNum() + 1 == node.getBlock().getPostNum()) return;
        out.printf("\t\tjmp\t\t%s\n" , blockId(node.getTarBlock()));
    }

    @Override
    public void visit(IRReturn node){
        out.println("\t\tret");
    }

    @Override
    public void visit(IRUnaryOperation node){
        String op;
        switch(node.getOp()){
            case BITWISE_NOT:
                op = "not";
                break;
            case NEG:
                op = "neg";
                break;
            default:
                throw new Error("invalid unary op");
        }
        out.print("\t\tmov\t\t");
        node.getDest().accept(this);
        out.print(", ");
        node.getRhs().accept(this);
        out.print("\n\t\t" + op + "\t\t");
        node.getDest().accept(this);
        out.println();
    }

    @Override
    public void visit(IRBinaryOperation node){
        if(node.getOp() == DIV || node.getOp() == MOD){
            out.print("\t\tmov\t\trbx, ");
            node.getRhs().accept(this);
            out.println();
            out.print("\t\tmov\t\trax, ");
            node.getLhs().accept(this);
            out.println();
            out.println("\t\tmov\t\t" + preg0.getName() + ", rdx");
            out.println("\t\tcdq");
            out.println("\t\tidiv\trbx");
            out.print("\t\tmov\t\t");
            node.getDest().accept(this);
            if(node.getOp() == DIV){
                out.println(", rax");
            }
            else{
                out.println(", rdx");
            }
            out.println("\t\tmov\t\trdx, " + preg0.getName());
        }
        else if(node.getOp() == LSFT || node.getOp() == RSFT){
            out.println("\t\tmov\t\trbx, rcx");
            out.print("\t\tmov\t\trcx, ");
            node.getRhs().accept(this);
            if(node.getOp() == LSFT){
                out.print("\n\t\tsal\t\t");
            }
            else{
                out.print("\n\t\tsar\t\t");
            }
            node.getLhs().accept(this);
            out.println(", cl");
            out.println("\t\tmov\t\trcx, rbx");
            out.print("\t\tand\t\t");
            node.getLhs().accept(this);
            out.println(", -1");
        }
        else{
            if(node.getDest() != node.getLhs()){
                throw new Error("binary op should have same lhs and dest");
            }
            String op;
            switch(node.getOp()){
                case ADD:
                    if(node.getRhs() instanceof Imm && ((Imm) node.getRhs()).getValue() == 1){
                        out.print("\t\tinc\t\t");
                        node.getLhs().accept(this);
                        out.println();
                        return;
                    }
                    op = "add\t";
                    break;
                case SUB:
                    if(node.getRhs() instanceof Imm && ((Imm) node.getRhs()).getValue() == 1){
                        out.print("\t\tdec\t\t");
                        node.getLhs().accept(this);
                        out.println();
                        return;
                    }
                    op = "sub\t";
                    break;
                case MUL:
                    if(node.getRhs() instanceof  Imm && ((Imm) node.getRhs()).getValue() == 1){
                        return;
                    }
                    op = "imul";
                    break;
                case BITWISE_OR:
                    op = "or\t";
                    break;
                case BITWISE_XOR:
                    op = "xor\t";
                    break;
                case BITWISE_AND:
                    op = "and\t";
                    break;
                default:
                    throw new Error("invalid binary op");
            }
            out.print("\t\t" + op + "\t");
            node.getLhs().accept(this);
            out.print(", ");
            node.getRhs().accept(this);
            out.println();
        }
    }

    @Override
    public void visit(IRCmpOperation node){
        if(node.getLhs() instanceof PhysicalReg){
            out.print("\t\tand\t\t");
            node.getLhs().accept(this);
            out.println(", -1");
        }
        if(node.getRhs() instanceof PhysicalReg){
            out.print("\t\tand\t\t");
            node.getRhs().accept(this);
            out.println(", -1");
        }
        out.println("\t\txor\t\trax, rax");
        out.print("\t\tcmp\t\t");
        node.getLhs().accept(this);
        out.print(", ");
        node.getRhs().accept(this);
        out.println();
        String op;
        switch(node.getOp()){
            case EQ:
                op = "sete";
                break;
            case NEQ:
                op = "setne";
                break;
            case LT:
                op = "setl";
                break;
            case GT:
                op = "setg";
                break;
            case GTE:
                op = "setge";
                break;
            case LTE:
                op = "setle";
                break;
            default:
                throw new Error("invalid cmp op");
        }
        out.println("\t\t" + op + "\tal");
        out.print("\t\tmov\t\t");
        node.getDest().accept(this);
        out.println(", rax");
    }

    @Override
    public void visit(IRMove node){
        out.print("\t\tmov\t\t");
        node.getLhs().accept(this);
        out.print(", ");
        node.getRhs().accept(this);
        out.println();
    }

    private String sizeStr(int memSize){
        String sizeStr;
        switch(memSize){
            case 1:
                sizeStr = "byte";
                break;
            case 2:
                sizeStr = "word";
                break;
            case 4:
                sizeStr = "dword";
                break;
            case 8:
                sizeStr = "qword";
                break;
            default:
                throw new Error("invalid load size");
        }
        return sizeStr;
    }

    @Override
    public void visit(IRLoad node){
        if(node.getAddr() instanceof StaticString){
            out.print("\t\tmov\t\t");
            node.getDest().accept(this);
            out.print(", " + sizeStr(node.getSize()) + " ");
            node.getAddr().accept(this);
            out.println();
            return;
        }
        out.print("\t\tmov\t\t");
        node.getDest().accept(this);
        out.print(", " + sizeStr(node.getSize()) + " [");
        node.getAddr().accept(this);
        if(node.getOffset() < 0){
            out.print(node.getOffset());
        }
        else if(node.getOffset() > 0){
            out.print("+" + node.getOffset());
        }
        out.println("]");
    }

    @Override
    public void visit(IRStore node){
        if(node.getAddr() instanceof StaticString){
            out.print("\t\tmov\t\t" + sizeStr(node.getSize()) + " ");
            node.getAddr().accept(this);
            out.print(" ");
            node.getValue().accept(this);
            out.println();
            return;
        }
        out.print("\t\tmov\t\t" + sizeStr(node.getSize()) + " [");
        node.getAddr().accept(this);
        if(node.getOffset() < 0){
            out.print(node.getOffset());
        }
        else if(node.getOffset() > 0){
            out.print("+" + node.getOffset());
        }
        out.print("],");
        node.getValue().accept(this);
        out.println();
    }

    @Override
    public void visit(IRFunctionCall node){
        if(node.getIrFunc().isBuiltIn()) out.println("\t\tcall\t" + node.getIrFunc().getBuiltInCallLabel());
        else out.println("\t\tcall\t" + blockId(node.getIrFunc().getStartBlock()));
    }

    @Override
    public void visit(IRHeapAlloc node){
        out.println("\t\tcall\tmalloc");
    }

    @Override
    public void visit(IRPush node){
        out.print("\t\tpush\t");
        node.getValue().accept(this);
        out.println();
    }

    @Override
    public void visit(IRPop node){
        out.print("\t\tpop\t\t");
        node.getPreg().accept(this);
        out.println();
    }

    @Override
    public void visit(VirtualReg node){
        throw new Error("vreg visited");
    }

    @Override
    public void visit(PhysicalReg node){
        out.print(node.getName());
    }

    @Override
    public void visit(Imm node){
        out.print(node.getValue());
    }

    @Override
    public void visit(StaticVar node){
        if(isBSection) {
            String op;
            switch (node.getSize()) {
                case 1:
                    op = "resb";
                    break;
                case 2:
                    op = "resw";
                    break;
                case 4:
                    op = "resd";
                    break;
                case 8:
                    op = "resq";
                    break;
                default:
                    throw new Error("invalid var size");
            }
            out.printf("%s:\t%s\t1\n" , dataId(node) , op);
        }
        else{
            out.print(dataId(node));
        }
    }

    private String staticStrDataSection(String str){
        StringBuilder builder = new StringBuilder();
        for(int i = 0 , n = str.length() ; i < n ; ++i){
            char c = str.charAt(i);
            builder.append((int) c);
            builder.append(", ");
        }
        builder.append(0);
        return builder.toString();
    }

    @Override
    public void visit(StaticString node){
        if(isDSection){
            out.printf("%s:\n" , dataId(node));
            out.printf("\t\tdq\t\t%d\n" , node.getValue().length());
            out.printf("\t\tdb\t\t%s\n" , staticStrDataSection(node.getValue()));
        }
        else{
            out.print(dataId(node));
        }
    }
}
