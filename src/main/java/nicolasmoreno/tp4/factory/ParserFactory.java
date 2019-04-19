package nicolasmoreno.tp4.factory;


import nicolasmoreno.tp4.builder.ParserRegistryBuilder;
import nicolasmoreno.tp4.parser.BinaryArithmeticParser;
import nicolasmoreno.tp4.parser.DoubleParser;
import nicolasmoreno.tp4.parser.LengthParser;
import nicolasmoreno.tp4.parser.LiteralParser;


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

    public static ParserRegistryBuilder parserRegistry() { return new ParserRegistryBuilder(); }

}
