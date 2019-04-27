package nicolasmoreno.tp4.parser;

import daoo.repl.Factory;
import daoo.repl.Operand;
import nicolasmoreno.tp4.operand.OperandImpl;
import org.jetbrains.annotations.NotNull;

public class LiteralParser implements Factory<Operand> {

    private final char LITERAL_CHARACTER = '"';

    @NotNull
    @Override
    public Operand apply(@NotNull String line) {
        return new OperandImpl(line.replaceAll("\"", ""));
    }

    @Override
    public boolean test(@NotNull String line) {
        if (line.length() < 2) return false;
        return line.charAt(0) == LITERAL_CHARACTER && line.charAt(line.length() - 1) == LITERAL_CHARACTER;
    }
}
