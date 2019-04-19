package nicolasmoreno.tp4.parser;

import daoo.repl.Command;
import daoo.repl.Parser;
import org.jetbrains.annotations.NotNull;

public class BinaryArithmeticParser implements Parser<Command> {


    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        return null;
    }

    @Override
    public boolean test(@NotNull String line) {
        return false;
    }
}
