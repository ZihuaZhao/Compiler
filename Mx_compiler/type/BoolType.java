package Mx_compiler.type;

public class BoolType extends PrimaryType {
    static public BoolType boolType = new BoolType();

    private BoolType(){
        type = TypeList.BOOL;
        varSize = 8;
    }

    public static BoolType getType(){
        return boolType;
    }
}
