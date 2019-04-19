package nicolasmoreno.tp4.parser;


import daoo.repl.Operand;
import daoo.repl.Parser;
import org.jetbrains.annotations.NotNull;

public class DoubleParser implements Parser<Operand> {

    @NotNull
    @Override
    public Operand apply(@NotNull String line) {
        return null;
    }

    @Override
    public boolean test(@NotNull String line) {
        return false;
    }
}
