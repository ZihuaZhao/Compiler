package Mx_compiler.type;

public class VoidType extends PrimaryType{
    static public VoidType voidType = new VoidType();

    private VoidType(){
        type = TypeList.VOID;
    }

    public static VoidType getType(){
        return voidType;
    }
}
