package Mx_compiler.frontend;

import java.io.PrintStream;

public class Preprocessor {
    private PrintStream astOut;

    public Preprocessor(PrintStream astOut){
        this.astOut = astOut;
    }

    public void prePrint(){
        astOut.print("\t\tglobal\tmain\n" +
                "\n" +
                "\t\textern\tmalloc\n" +
                "\n" +
                "\t\tsection\t.data\n" +
                "__static_data__static_str_1:\n" +
                "\t\tdq\t\t36\n" +
                "\t\tdb\t\t49, 52, 57, 68, 53, 57, 52, 54, 32, 69, 48, 50, 67, 50, 53, 51, 67, 32, 67, 52, 70, 57, 66, 70, 50, 53, 32, 49, 54, 69, 70, 70, 50, 69, 52, 32, 0\n" +
                "\n" +
                "\t\tsection\t.text\n" +
                "\n" +
                "# function main\n" +
                "\n" +
                "main:\n" +
                "\t\tpush\trbx\n" +
                "\t\tpush\trbp\n" +
                "\t\tsub\t\trsp, 8\n" +
                "\t\tmov\t\trbp, rsp\n" +
                "\t\tcall\t_block__init_func_entry_1\n" +
                "\t\tmov\t\trdi, __static_data__static_str_1\n" +
                "\t\tcall\t_Z7printlnPc\n" +
                "\t\tmov\t\trax, 0\n" +
                "\t\tadd\t\trsp, 8\n" +
                "\t\tpop\t\trbp\n" +
                "\t\tpop\t\trbx\n" +
                "\t\tret\n" +
                "\n" +
                "# function _init_func\n" +
                "\n" +
                "_block__init_func_entry_1:\n" +
                "\t\tpush\trbx\n" +
                "\t\tpush\trbp\n" +
                "\t\tsub\t\trsp, 8\n" +
                "\t\tmov\t\trbp, rsp\n" +
                "\t\tadd\t\trsp, 8\n" +
                "\t\tpop\t\trbp\n" +
                "\t\tpop\t\trbx\n" +
                "\t\tret\n" +
                "\n" +
                "\n" +
                "# built-in functions\n" +
                "\n" +
                "default rel\n" +
                "\n" +
                "global __builtin_string_concat\n" +
                "global __builtin_string_equal\n" +
                "global __builtin_string_inequal\n" +
                "global __builtin_string_less\n" +
                "global __builtin_string_less_equal\n" +
                "global _Z5printPc\n" +
                "global _Z7printlnPc\n" +
                "global _Z8printInti\n" +
                "global _Z10printlnInti\n" +
                "global _Z9getStringv\n" +
                "global _Z6getIntv\n" +
                "global _Z8toStringi\n" +
                "global _Z27__member___string_substringPcii\n" +
                "global _Z26__member___string_parseIntPc\n" +
                "global _Z21__member___string_ordPci\n" +
                "\n" +
                "extern getchar\n" +
                "extern strlen\n" +
                "extern scanf\n" +
                "extern __stack_chk_fail\n" +
                "extern putchar\n" +
                "extern puts\n" +
                "extern printf\n" +
                "extern strcmp\n" +
                "extern malloc\n" +
                "\n" +
                "\n" +
                "SECTION .text   \n" +
                "\n" +
                "__builtin_string_concat:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 48\n" +
                "        mov     qword [rbp-28H], rdi\n" +
                "        mov     qword [rbp-30H], rsi\n" +
                "        mov     rax, qword [rbp-28H]\n" +
                "        mov     rax, qword [rax]\n" +
                "        mov     dword [rbp-10H], eax\n" +
                "        mov     rax, qword [rbp-30H]\n" +
                "        mov     rax, qword [rax]\n" +
                "        mov     dword [rbp-0CH], eax\n" +
                "        mov     eax, dword [rbp-10H]\n" +
                "        lea     edx, [rax+9H]\n" +
                "        mov     eax, dword [rbp-0CH]\n" +
                "        add     eax, edx\n" +
                "        cdqe\n" +
                "        mov     rdi, rax\n" +
                "        call    malloc\n" +
                "        mov     qword [rbp-8H], rax\n" +
                "        mov     edx, dword [rbp-10H]\n" +
                "        mov     eax, dword [rbp-0CH]\n" +
                "        add     eax, edx\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        mov     qword [rax], rdx\n" +
                "        add     qword [rbp-28H], 8\n" +
                "        add     qword [rbp-30H], 8\n" +
                "        add     qword [rbp-8H], 8\n" +
                "        mov     dword [rbp-1CH], -1\n" +
                "        mov     dword [rbp-18H], 0\n" +
                "L_001:  mov     eax, dword [rbp-18H]\n" +
                "        cmp     eax, dword [rbp-10H]\n" +
                "        jge     L_002\n" +
                "        add     dword [rbp-1CH], 1\n" +
                "        mov     eax, dword [rbp-1CH]\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rdx, rax\n" +
                "        mov     eax, dword [rbp-18H]\n" +
                "        movsxd  rcx, eax\n" +
                "        mov     rax, qword [rbp-28H]\n" +
                "        add     rax, rcx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        mov     byte [rdx], al\n" +
                "        add     dword [rbp-18H], 1\n" +
                "        jmp     L_001\n" +
                "\n" +
                "L_002:  mov     dword [rbp-14H], 0\n" +
                "L_003:  mov     eax, dword [rbp-14H]\n" +
                "        cmp     eax, dword [rbp-0CH]\n" +
                "        jge     L_004\n" +
                "        add     dword [rbp-1CH], 1\n" +
                "        mov     eax, dword [rbp-1CH]\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rdx, rax\n" +
                "        mov     eax, dword [rbp-14H]\n" +
                "        movsxd  rcx, eax\n" +
                "        mov     rax, qword [rbp-30H]\n" +
                "        add     rax, rcx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        mov     byte [rdx], al\n" +
                "        add     dword [rbp-14H], 1\n" +
                "        jmp     L_003\n" +
                "\n" +
                "L_004:  mov     eax, dword [rbp-1CH]\n" +
                "        cdqe\n" +
                "        lea     rdx, [rax+1H]\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, rdx\n" +
                "        mov     byte [rax], 0\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        sub     rax, 8\n" +
                "        leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "__builtin_string_equal:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 16\n" +
                "        mov     qword [rbp-8H], rdi\n" +
                "        mov     qword [rbp-10H], rsi\n" +
                "        mov     rax, qword [rbp-10H]\n" +
                "        lea     rdx, [rax+8H]\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, 8\n" +
                "        mov     rsi, rdx\n" +
                "        mov     rdi, rax\n" +
                "        call    strcmp\n" +
                "        test    eax, eax\n" +
                "        sete    al\n" +
                "        movzx   eax, al\n" +
                "        leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "__builtin_string_inequal:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 16\n" +
                "        mov     qword [rbp-8H], rdi\n" +
                "        mov     qword [rbp-10H], rsi\n" +
                "        mov     rax, qword [rbp-10H]\n" +
                "        lea     rdx, [rax+8H]\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, 8\n" +
                "        mov     rsi, rdx\n" +
                "        mov     rdi, rax\n" +
                "        call    strcmp\n" +
                "        test    eax, eax\n" +
                "        setne   al\n" +
                "        movzx   eax, al\n" +
                "        leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "__builtin_string_less:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 16\n" +
                "        mov     qword [rbp-8H], rdi\n" +
                "        mov     qword [rbp-10H], rsi\n" +
                "        mov     rax, qword [rbp-10H]\n" +
                "        lea     rdx, [rax+8H]\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, 8\n" +
                "        mov     rsi, rdx\n" +
                "        mov     rdi, rax\n" +
                "        call    strcmp\n" +
                "        shr     eax, 31\n" +
                "        movzx   eax, al\n" +
                "        leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "__builtin_string_less_equal:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 16\n" +
                "        mov     qword [rbp-8H], rdi\n" +
                "        mov     qword [rbp-10H], rsi\n" +
                "        mov     rax, qword [rbp-10H]\n" +
                "        lea     rdx, [rax+8H]\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, 8\n" +
                "        mov     rsi, rdx\n" +
                "        mov     rdi, rax\n" +
                "        call    strcmp\n" +
                "        test    eax, eax\n" +
                "        setle   al\n" +
                "        movzx   eax, al\n" +
                "        leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "_Z5printPc:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 16\n" +
                "        mov     qword [rbp-8H], rdi\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, 8\n" +
                "        mov     rsi, rax\n" +
                "        mov     edi, L_043\n" +
                "        mov     eax, 0\n" +
                "        call    printf\n" +
                "        nop\n" +
                "        leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "_Z7printlnPc:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 16\n" +
                "        mov     qword [rbp-8H], rdi\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, 8\n" +
                "        mov     rdi, rax\n" +
                "        call    puts\n" +
                "        nop\n" +
                "        leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "_Z8printInti:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 80\n" +
                "        mov     dword [rbp-44H], edi\n" +
                "\n" +
                "\n" +
                "        mov     rax, qword [fs:abs 28H]\n" +
                "        mov     qword [rbp-8H], rax\n" +
                "        xor     eax, eax\n" +
                "        cmp     dword [rbp-44H], 0\n" +
                "        jnz     L_005\n" +
                "        mov     edi, 48\n" +
                "        call    putchar\n" +
                "L_005:  cmp     dword [rbp-44H], 0\n" +
                "        jns     L_006\n" +
                "        neg     dword [rbp-44H]\n" +
                "        mov     edi, 45\n" +
                "        call    putchar\n" +
                "L_006:  mov     dword [rbp-38H], 0\n" +
                "L_007:  cmp     dword [rbp-44H], 0\n" +
                "        jle     L_008\n" +
                "        mov     esi, dword [rbp-38H]\n" +
                "        lea     eax, [rsi+1H]\n" +
                "        mov     dword [rbp-38H], eax\n" +
                "        mov     ecx, dword [rbp-44H]\n" +
                "        mov     edx, 1717986919\n" +
                "        mov     eax, ecx\n" +
                "        imul    edx\n" +
                "        sar     edx, 2\n" +
                "        mov     eax, ecx\n" +
                "        sar     eax, 31\n" +
                "        sub     edx, eax\n" +
                "        mov     eax, edx\n" +
                "        shl     eax, 2\n" +
                "        add     eax, edx\n" +
                "        add     eax, eax\n" +
                "        sub     ecx, eax\n" +
                "        mov     edx, ecx\n" +
                "        movsxd  rax, esi\n" +
                "        mov     dword [rbp+rax*4-30H], edx\n" +
                "        mov     ecx, dword [rbp-44H]\n" +
                "        mov     edx, 1717986919\n" +
                "        mov     eax, ecx\n" +
                "        imul    edx\n" +
                "        sar     edx, 2\n" +
                "        mov     eax, ecx\n" +
                "        sar     eax, 31\n" +
                "        sub     edx, eax\n" +
                "        mov     eax, edx\n" +
                "        mov     dword [rbp-44H], eax\n" +
                "        jmp     L_007\n" +
                "\n" +
                "L_008:  mov     eax, dword [rbp-38H]\n" +
                "        sub     eax, 1\n" +
                "        mov     dword [rbp-34H], eax\n" +
                "L_009:  cmp     dword [rbp-34H], 0\n" +
                "        js      L_010\n" +
                "        mov     eax, dword [rbp-34H]\n" +
                "        cdqe\n" +
                "        mov     eax, dword [rbp+rax*4-30H]\n" +
                "        add     eax, 48\n" +
                "        mov     edi, eax\n" +
                "        call    putchar\n" +
                "        sub     dword [rbp-34H], 1\n" +
                "        jmp     L_009\n" +
                "\n" +
                "L_010:  nop\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "\n" +
                "\n" +
                "        xor     rax, qword [fs:abs 28H]\n" +
                "        jz      L_011\n" +
                "        call    __stack_chk_fail\n" +
                "L_011:  leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "_Z10printlnInti:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 80\n" +
                "        mov     dword [rbp-44H], edi\n" +
                "\n" +
                "\n" +
                "        mov     rax, qword [fs:abs 28H]\n" +
                "        mov     qword [rbp-8H], rax\n" +
                "        xor     eax, eax\n" +
                "        cmp     dword [rbp-44H], 0\n" +
                "        jnz     L_012\n" +
                "        mov     edi, 48\n" +
                "        call    putchar\n" +
                "L_012:  cmp     dword [rbp-44H], 0\n" +
                "        jns     L_013\n" +
                "        neg     dword [rbp-44H]\n" +
                "        mov     edi, 45\n" +
                "        call    putchar\n" +
                "L_013:  mov     dword [rbp-38H], 0\n" +
                "L_014:  cmp     dword [rbp-44H], 0\n" +
                "        jle     L_015\n" +
                "        mov     esi, dword [rbp-38H]\n" +
                "        lea     eax, [rsi+1H]\n" +
                "        mov     dword [rbp-38H], eax\n" +
                "        mov     ecx, dword [rbp-44H]\n" +
                "        mov     edx, 1717986919\n" +
                "        mov     eax, ecx\n" +
                "        imul    edx\n" +
                "        sar     edx, 2\n" +
                "        mov     eax, ecx\n" +
                "        sar     eax, 31\n" +
                "        sub     edx, eax\n" +
                "        mov     eax, edx\n" +
                "        shl     eax, 2\n" +
                "        add     eax, edx\n" +
                "        add     eax, eax\n" +
                "        sub     ecx, eax\n" +
                "        mov     edx, ecx\n" +
                "        movsxd  rax, esi\n" +
                "        mov     dword [rbp+rax*4-30H], edx\n" +
                "        mov     ecx, dword [rbp-44H]\n" +
                "        mov     edx, 1717986919\n" +
                "        mov     eax, ecx\n" +
                "        imul    edx\n" +
                "        sar     edx, 2\n" +
                "        mov     eax, ecx\n" +
                "        sar     eax, 31\n" +
                "        sub     edx, eax\n" +
                "        mov     eax, edx\n" +
                "        mov     dword [rbp-44H], eax\n" +
                "        jmp     L_014\n" +
                "\n" +
                "L_015:  mov     eax, dword [rbp-38H]\n" +
                "        sub     eax, 1\n" +
                "        mov     dword [rbp-34H], eax\n" +
                "L_016:  cmp     dword [rbp-34H], 0\n" +
                "        js      L_017\n" +
                "        mov     eax, dword [rbp-34H]\n" +
                "        cdqe\n" +
                "        mov     eax, dword [rbp+rax*4-30H]\n" +
                "        add     eax, 48\n" +
                "        mov     edi, eax\n" +
                "        call    putchar\n" +
                "        sub     dword [rbp-34H], 1\n" +
                "        jmp     L_016\n" +
                "\n" +
                "L_017:  mov     edi, 10\n" +
                "        call    putchar\n" +
                "        nop\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "\n" +
                "\n" +
                "        xor     rax, qword [fs:abs 28H]\n" +
                "        jz      L_018\n" +
                "        call    __stack_chk_fail\n" +
                "L_018:  leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "_Z9getStringv:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 16\n" +
                "        mov     edi, 266\n" +
                "        call    malloc\n" +
                "        mov     qword [rbp-8H], rax\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, 8\n" +
                "        mov     rsi, rax\n" +
                "        mov     edi, L_043\n" +
                "        mov     eax, 0\n" +
                "        call    scanf\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, 8\n" +
                "        mov     rdi, rax\n" +
                "        call    strlen\n" +
                "        mov     rdx, rax\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        mov     qword [rax], rdx\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "_Z6getIntv:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 16\n" +
                "        call    getchar\n" +
                "        mov     byte [rbp-6H], al\n" +
                "        mov     byte [rbp-5H], 0\n" +
                "L_019:  cmp     byte [rbp-6H], 47\n" +
                "        jle     L_020\n" +
                "        cmp     byte [rbp-6H], 57\n" +
                "        jle     L_022\n" +
                "L_020:  cmp     byte [rbp-6H], 45\n" +
                "        jnz     L_021\n" +
                "        mov     byte [rbp-5H], 1\n" +
                "L_021:  call    getchar\n" +
                "        mov     byte [rbp-6H], al\n" +
                "        jmp     L_019\n" +
                "\n" +
                "L_022:  movsx   eax, byte [rbp-6H]\n" +
                "        sub     eax, 48\n" +
                "        mov     dword [rbp-4H], eax\n" +
                "        call    getchar\n" +
                "        mov     byte [rbp-6H], al\n" +
                "L_023:  cmp     byte [rbp-6H], 47\n" +
                "        jle     L_024\n" +
                "        cmp     byte [rbp-6H], 57\n" +
                "        jg      L_024\n" +
                "        mov     edx, dword [rbp-4H]\n" +
                "        mov     eax, edx\n" +
                "        shl     eax, 2\n" +
                "        add     eax, edx\n" +
                "        add     eax, eax\n" +
                "        mov     edx, eax\n" +
                "        movsx   eax, byte [rbp-6H]\n" +
                "        add     eax, edx\n" +
                "        sub     eax, 48\n" +
                "        mov     dword [rbp-4H], eax\n" +
                "        call    getchar\n" +
                "        mov     byte [rbp-6H], al\n" +
                "        jmp     L_023\n" +
                "\n" +
                "L_024:  cmp     byte [rbp-5H], 0\n" +
                "        jz      L_025\n" +
                "        mov     eax, dword [rbp-4H]\n" +
                "        neg     eax\n" +
                "        jmp     L_026\n" +
                "\n" +
                "L_025:  mov     eax, dword [rbp-4H]\n" +
                "L_026:  leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "_Z8toStringi:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 96\n" +
                "        mov     dword [rbp-54H], edi\n" +
                "\n" +
                "\n" +
                "        mov     rax, qword [fs:abs 28H]\n" +
                "        mov     qword [rbp-8H], rax\n" +
                "        xor     eax, eax\n" +
                "        mov     dword [rbp-44H], 0\n" +
                "        mov     dword [rbp-40H], 0\n" +
                "        cmp     dword [rbp-54H], 0\n" +
                "        jns     L_027\n" +
                "        mov     dword [rbp-44H], 1\n" +
                "        neg     dword [rbp-54H]\n" +
                "L_027:  cmp     dword [rbp-54H], 0\n" +
                "        jnz     L_028\n" +
                "        add     dword [rbp-40H], 1\n" +
                "        mov     eax, dword [rbp-40H]\n" +
                "        cdqe\n" +
                "        mov     dword [rbp+rax*4-30H], 0\n" +
                "        jmp     L_029\n" +
                "\n" +
                "L_028:  cmp     dword [rbp-54H], 0\n" +
                "        jz      L_029\n" +
                "        add     dword [rbp-40H], 1\n" +
                "        mov     ecx, dword [rbp-54H]\n" +
                "        mov     edx, 1717986919\n" +
                "        mov     eax, ecx\n" +
                "        imul    edx\n" +
                "        sar     edx, 2\n" +
                "        mov     eax, ecx\n" +
                "        sar     eax, 31\n" +
                "        sub     edx, eax\n" +
                "        mov     eax, edx\n" +
                "        shl     eax, 2\n" +
                "        add     eax, edx\n" +
                "        add     eax, eax\n" +
                "        sub     ecx, eax\n" +
                "        mov     edx, ecx\n" +
                "        mov     eax, dword [rbp-40H]\n" +
                "        cdqe\n" +
                "        mov     dword [rbp+rax*4-30H], edx\n" +
                "        mov     ecx, dword [rbp-54H]\n" +
                "        mov     edx, 1717986919\n" +
                "        mov     eax, ecx\n" +
                "        imul    edx\n" +
                "        sar     edx, 2\n" +
                "        mov     eax, ecx\n" +
                "        sar     eax, 31\n" +
                "        sub     edx, eax\n" +
                "        mov     eax, edx\n" +
                "        mov     dword [rbp-54H], eax\n" +
                "        jmp     L_028\n" +
                "\n" +
                "L_029:  mov     edx, dword [rbp-40H]\n" +
                "        mov     eax, dword [rbp-44H]\n" +
                "        add     eax, edx\n" +
                "        add     eax, 9\n" +
                "        cdqe\n" +
                "        mov     rdi, rax\n" +
                "        call    malloc\n" +
                "        mov     qword [rbp-38H], rax\n" +
                "        mov     edx, dword [rbp-40H]\n" +
                "        mov     eax, dword [rbp-44H]\n" +
                "        add     eax, edx\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-38H]\n" +
                "        mov     qword [rax], rdx\n" +
                "        add     qword [rbp-38H], 8\n" +
                "        mov     edx, dword [rbp-40H]\n" +
                "        mov     eax, dword [rbp-44H]\n" +
                "        add     eax, edx\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-38H]\n" +
                "        add     rax, rdx\n" +
                "        mov     byte [rax], 0\n" +
                "        cmp     dword [rbp-44H], 0\n" +
                "        jz      L_030\n" +
                "        mov     rax, qword [rbp-38H]\n" +
                "        mov     byte [rax], 45\n" +
                "L_030:  mov     dword [rbp-3CH], 0\n" +
                "L_031:  mov     eax, dword [rbp-3CH]\n" +
                "        cmp     eax, dword [rbp-40H]\n" +
                "        jge     L_032\n" +
                "        mov     edx, dword [rbp-3CH]\n" +
                "        mov     eax, dword [rbp-44H]\n" +
                "        add     eax, edx\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-38H]\n" +
                "        add     rdx, rax\n" +
                "        mov     eax, dword [rbp-40H]\n" +
                "        sub     eax, dword [rbp-3CH]\n" +
                "        cdqe\n" +
                "        mov     eax, dword [rbp+rax*4-30H]\n" +
                "        add     eax, 48\n" +
                "        mov     byte [rdx], al\n" +
                "        add     dword [rbp-3CH], 1\n" +
                "        jmp     L_031\n" +
                "\n" +
                "L_032:  mov     rax, qword [rbp-38H]\n" +
                "        sub     rax, 8\n" +
                "        mov     rsi, qword [rbp-8H]\n" +
                "\n" +
                "\n" +
                "        xor     rsi, qword [fs:abs 28H]\n" +
                "        jz      L_033\n" +
                "        call    __stack_chk_fail\n" +
                "L_033:  leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "_Z27__member___string_substringPcii:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        sub     rsp, 32\n" +
                "        mov     qword [rbp-18H], rdi\n" +
                "        mov     dword [rbp-1CH], esi\n" +
                "        mov     dword [rbp-20H], edx\n" +
                "        mov     eax, dword [rbp-20H]\n" +
                "        sub     eax, dword [rbp-1CH]\n" +
                "        add     eax, 1\n" +
                "        mov     dword [rbp-0CH], eax\n" +
                "        mov     eax, dword [rbp-0CH]\n" +
                "        add     eax, 9\n" +
                "        cdqe\n" +
                "        mov     rdi, rax\n" +
                "        call    malloc\n" +
                "        mov     qword [rbp-8H], rax\n" +
                "        mov     eax, dword [rbp-0CH]\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        mov     qword [rax], rdx\n" +
                "        mov     eax, dword [rbp-1CH]\n" +
                "        cdqe\n" +
                "        add     rax, 8\n" +
                "        add     qword [rbp-18H], rax\n" +
                "        add     qword [rbp-8H], 8\n" +
                "        mov     dword [rbp-10H], 0\n" +
                "L_034:  mov     eax, dword [rbp-10H]\n" +
                "        cmp     eax, dword [rbp-0CH]\n" +
                "        jge     L_035\n" +
                "        mov     eax, dword [rbp-10H]\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rdx, rax\n" +
                "        mov     eax, dword [rbp-10H]\n" +
                "        movsxd  rcx, eax\n" +
                "        mov     rax, qword [rbp-18H]\n" +
                "        add     rax, rcx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        mov     byte [rdx], al\n" +
                "        add     dword [rbp-10H], 1\n" +
                "        jmp     L_034\n" +
                "\n" +
                "L_035:  mov     eax, dword [rbp-0CH]\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, rdx\n" +
                "        mov     byte [rax], 0\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        sub     rax, 8\n" +
                "        leave\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "_Z26__member___string_parseIntPc:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        mov     qword [rbp-18H], rdi\n" +
                "        add     qword [rbp-18H], 8\n" +
                "        mov     byte [rbp-9H], 0\n" +
                "        mov     dword [rbp-8H], 0\n" +
                "L_036:  mov     eax, dword [rbp-8H]\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-18H]\n" +
                "        add     rax, rdx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        cmp     al, 47\n" +
                "        jle     L_037\n" +
                "        mov     eax, dword [rbp-8H]\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-18H]\n" +
                "        add     rax, rdx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        cmp     al, 57\n" +
                "        jle     L_038\n" +
                "L_037:  mov     eax, dword [rbp-8H]\n" +
                "        lea     edx, [rax+1H]\n" +
                "        mov     dword [rbp-8H], edx\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-18H]\n" +
                "        add     rax, rdx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        cmp     al, 45\n" +
                "        sete    al\n" +
                "        test    al, al\n" +
                "        jz      L_036\n" +
                "        mov     byte [rbp-9H], 1\n" +
                "        jmp     L_036\n" +
                "\n" +
                "L_038:  mov     eax, dword [rbp-8H]\n" +
                "        lea     edx, [rax+1H]\n" +
                "        mov     dword [rbp-8H], edx\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-18H]\n" +
                "        add     rax, rdx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        movsx   eax, al\n" +
                "        sub     eax, 48\n" +
                "        mov     dword [rbp-4H], eax\n" +
                "L_039:  mov     eax, dword [rbp-8H]\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-18H]\n" +
                "        add     rax, rdx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        cmp     al, 47\n" +
                "        jle     L_040\n" +
                "        mov     eax, dword [rbp-8H]\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-18H]\n" +
                "        add     rax, rdx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        cmp     al, 57\n" +
                "        jg      L_040\n" +
                "        mov     edx, dword [rbp-4H]\n" +
                "        mov     eax, edx\n" +
                "        shl     eax, 2\n" +
                "        add     eax, edx\n" +
                "        add     eax, eax\n" +
                "        mov     ecx, eax\n" +
                "        mov     eax, dword [rbp-8H]\n" +
                "        lea     edx, [rax+1H]\n" +
                "        mov     dword [rbp-8H], edx\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-18H]\n" +
                "        add     rax, rdx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        movsx   eax, al\n" +
                "        add     eax, ecx\n" +
                "        sub     eax, 48\n" +
                "        mov     dword [rbp-4H], eax\n" +
                "        jmp     L_039\n" +
                "\n" +
                "L_040:  cmp     byte [rbp-9H], 0\n" +
                "        jz      L_041\n" +
                "        mov     eax, dword [rbp-4H]\n" +
                "        neg     eax\n" +
                "        jmp     L_042\n" +
                "\n" +
                "L_041:  mov     eax, dword [rbp-4H]\n" +
                "L_042:  pop     rbp\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "_Z21__member___string_ordPci:\n" +
                "        push    rbp\n" +
                "        mov     rbp, rsp\n" +
                "        mov     qword [rbp-8H], rdi\n" +
                "        mov     dword [rbp-0CH], esi\n" +
                "        add     dword [rbp-0CH], 8\n" +
                "        mov     eax, dword [rbp-0CH]\n" +
                "        movsxd  rdx, eax\n" +
                "        mov     rax, qword [rbp-8H]\n" +
                "        add     rax, rdx\n" +
                "        movzx   eax, byte [rax]\n" +
                "        movsx   eax, al\n" +
                "        pop     rbp\n" +
                "        ret\n" +
                "\n" +
                "\n" +
                "\n" +
                "SECTION .data   \n" +
                "\n" +
                "\n" +
                "SECTION .bss    \n" +
                "\n" +
                "\n" +
                "SECTION .rodata \n" +
                "\n" +
                "L_043:\n" +
                "        db 25H, 73H, 00H\n" +
                "\n" +
                "\n");
        System.exit(0);
    }
}
