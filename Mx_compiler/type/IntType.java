package Mx_compiler.type;

public class IntType extends PrimaryType{
    static private IntType intType = new IntType();

    private IntType(){
        type = TypeList.INT;
        varSize = 8;
    }

    public static IntType getType(){
        return intType;
    }
}
