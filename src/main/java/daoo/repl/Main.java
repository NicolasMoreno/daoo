package daoo.repl;

import static nicolasmoreno.tp4.factory.ParserFactory.*;
import static nicolasmoreno.tp4.factory.ReplFactory.repl;

public class Main {

    public static void main(String[] args) {
        final ParserRegistry registry = parserRegistry()
                .addOperand(doubleParser())
                .addOperand(literalParser())
                .addCommand(binaryArithmeticParser())
                .addCommand(lengthParser())
                .build();

        final Repl repl = repl(registry);
        repl.loop(System.in, System.out);
    }
}
