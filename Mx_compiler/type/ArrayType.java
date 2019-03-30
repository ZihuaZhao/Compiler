package Mx_compiler.type;

public class ArrayType extends Type{
    private Type baseType;

    public ArrayType(Type baseType){
        type = TypeList.ARRAY;
        this.baseType = baseType;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof ArrayType)) return false;
        return baseType.equals(((ArrayType) obj).baseType);
    }

    public Type getBaseType(){
        return baseType;
    }
}
