package daoo.repl;

import static nicolasmoreno.tp4.factory.ParserFactory.*;
import static daoo.repl.ReplFactory.repl;

public class Main {

    public static void main(String[] args) {
        final Environment registry = environment()
                .addOperand(doubleParser())
                .addOperand(literalParser())
                .addCommand(binaryArithmeticParser())
                .addCommand(lengthParser())
                .addCommand(functionParser())
                .build();

        final Repl repl = repl(registry);
        repl.loop(System.in, System.out);
    }
}
