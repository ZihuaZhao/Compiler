package Mx_compiler.type;

public class ClassType extends Type {
    private String name;

    public ClassType(String name){
        type = TypeList.CLASS;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof ClassType)) return false;
        return name.equals(((ClassType) obj).name);
    }

    public String getName(){
        return name;
    }
}
