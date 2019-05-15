package Mx_compiler.backend;

import Mx_compiler.IR.*;

public class ExtraInstOptim {
    private IRRoot ir;

    public ExtraInstOptim(IRRoot ir) {
        this.ir = ir;
    }

    public void run() {
        for (IRFunc func : ir.getFuncs().values()) {
            for (BasicBlock bb : func.getReversePostOrder()) {
                for (IRInstruction inst = bb.getFirstInst(), lastInst = null; inst != null; inst = inst.getSuccInst()) {
                    boolean remove = false;
                    if (inst instanceof IRMove) {
                        IRMove moveInst = (IRMove) inst;
                        if (moveInst.getLhs() == moveInst.getRhs()) remove = true;
                        else if (lastInst instanceof IRMove &&
                                moveInst.getLhs() == ((IRMove) lastInst).getRhs() &&
                                moveInst.getRhs() == ((IRMove) lastInst).getLhs()) remove = true;
                    } else if (inst instanceof IRLoad) {
                        if (lastInst instanceof IRStore &&
                                ((IRStore) lastInst).getValue() == ((IRLoad) inst).getDest() &&
                                ((IRStore) lastInst).getAddr() == ((IRLoad) inst).getAddr() &&
                                ((IRStore) lastInst).getOffset() == ((IRLoad) inst).getOffset() &&
                                ((IRStore) lastInst).getSize() == ((IRLoad) inst).getSize()) remove = true;
                    } else if (inst instanceof IRStore) {
                        if (lastInst instanceof IRLoad &&
                                ((IRLoad) lastInst).getDest() == ((IRStore) inst).getValue() &&
                                ((IRLoad) lastInst).getAddr() == ((IRStore) inst).getAddr() &&
                                ((IRLoad) lastInst).getOffset() == ((IRStore) inst).getOffset() &&
                                ((IRLoad) lastInst).getSize() == ((IRStore) inst).getSize()) remove = true;
                    }
                    if (remove) inst.remove();
                    else lastInst = inst;
                }
            }
        }
    }
}