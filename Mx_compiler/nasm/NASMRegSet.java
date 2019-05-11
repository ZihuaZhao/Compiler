package Mx_compiler.nasm;

import Mx_compiler.IR.PhysicalReg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NASMRegSet {
    public static final Collection<PhysicalReg> allRegs, generalRegs, callerSaveRegs, calleeSaveRegs;
    public static final NASMReg rax, rcx, rdx, rbx, rsi, rdi, rsp, rbp, r8, r9, r10, r11, r12, r13, r14, r15;
    public static final List<PhysicalReg> arg6;
    static {
        List<NASMReg> all = new ArrayList<>();
        List<NASMReg> general = new ArrayList<>();
        List<NASMReg> callerSave = new ArrayList<>();
        List<NASMReg> calleeSave = new ArrayList<>();
        rax = new NASMReg("rax" , false , true , false , -1);
        rcx = new NASMReg("rcx" , false , true , false , 3);
        rdx = new NASMReg("rdx" , false , true , false , 2);
        rbx = new NASMReg("rbx" , false , false , true , -1);
        rsi = new NASMReg("rsi" , false , true , false , 1);
        rdi = new NASMReg("rdi" , false , true , false , 0);
        rsp = new NASMReg("rsp" , false , true , false , -1);
        rbp = new NASMReg("rbp" , false , false , true , -1);
        r8 = new NASMReg("r8" , true , true , false , 4);
        r9 = new NASMReg("r9" , true , true , false , 5);
        r10 = new NASMReg("r10" , true , true , false , -1);
        r11 = new NASMReg("r11" , true , true , false , -1);
        r12 = new NASMReg("r12" , true , false , true , -1);
        r13 = new NASMReg("r13" , true , false , true , -1);
        r14 = new NASMReg("r14" , true , false , true , -1);
        r15 = new NASMReg("r15" , true , false , true , -1);
        arg6 = new ArrayList<>();
        arg6.add(rdi);
        arg6.add(rsi);
        arg6.add(rdx);
        arg6.add(rcx);
        arg6.add(r8);
        arg6.add(r9);
        all.add(rax);
        all.add(rcx);
        all.add(rdx);
        all.add(rbx);
        all.add(rsi);
        all.add(rdi);
        all.add(rsp);
        all.add(rbp);
        all.add(r8);
        all.add(r9);
        all.add(r10);
        all.add(r11);
        all.add(r12);
        all.add(r13);
        all.add(r14);
        all.add(r15);
        all.stream().filter(NASMReg::isGeneral).forEach(general::add);
        all.stream().filter(NASMReg::isCallerSave).forEach(callerSave::add);
        all.stream().filter(NASMReg::isCalleeSave).forEach(calleeSave::add);
        allRegs = Collections.unmodifiableCollection(all);
        generalRegs = Collections.unmodifiableCollection(general);
        callerSaveRegs = Collections.unmodifiableList(callerSave);
        calleeSaveRegs = Collections.unmodifiableList(calleeSave);
    }
}
