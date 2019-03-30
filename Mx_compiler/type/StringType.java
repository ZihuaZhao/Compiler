package Mx_compiler.type;

public class StringType extends PrimaryType {
    static public StringType stringType = new StringType();

    private StringType(){
        type = TypeList.STRING;
    }

    public static StringType getType(){
        return stringType;
    }
}
