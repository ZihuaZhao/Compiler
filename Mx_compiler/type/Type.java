package Mx_compiler.type;

public class Type {
    public enum TypeList{
        VOID , INT , BOOL , STRING , CLASS , ARRAY , FUNCTION , NULL
    }

    TypeList type;

    public TypeList getTypeList(){
        return type;
    }
}
