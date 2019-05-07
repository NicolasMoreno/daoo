package nicolasmoreno.tp4.factory;


import nicolasmoreno.tp4.builder.EnvironmentBuilder;
import nicolasmoreno.tp4.parser.*;


public class ParserFactory {

    public static DoubleParser doubleParser() {
        return new DoubleParser();
    }

    public static LiteralParser literalParser() {
        return new LiteralParser();
    }

    public static BinaryArithmeticParser binaryArithmeticParser() {
        return new BinaryArithmeticParser();
    }

    public static LengthParser lengthParser() {
        return new LengthParser();
    }

    public static FunctionParser functionParser() { return new FunctionParser(); }

    public static VariableParser variableParser() { return new VariableParser(); }

    public static EnvironmentBuilder environment() { return new EnvironmentBuilder(); }

}
