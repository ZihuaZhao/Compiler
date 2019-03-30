package Mx_compiler.type;

public class FunctionType extends Type{
    private String name;

    public FunctionType(String name){
        type = TypeList.FUNCTION;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof FunctionType)) return false;
        return name.equals(((FunctionType) obj).name);
    }

    public String getName(){
         return name;
    }
}
