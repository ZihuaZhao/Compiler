package Mx_compiler.type;

public class StringType extends PrimaryType {
    static public StringType stringType = new StringType();

    private StringType(){
        type = TypeList.STRING;
        varSize = 8;
    }

    public static StringType getType(){
        return stringType;
    }
}
