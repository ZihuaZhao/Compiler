package Mx_compiler.type;

public class NullType extends Type{
    static private NullType nullType = new NullType();

    private NullType(){
        type = TypeList.NULL;
    }

    public static NullType getType(){
        return nullType;
    }
}
