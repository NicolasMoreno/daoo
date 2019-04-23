package nicolasmoreno.tp4.parser;

import daoo.repl.Operand;
import daoo.repl.Parser;
import nicolasmoreno.tp4.operand.OperandImpl;
import org.jetbrains.annotations.NotNull;

public class DoubleParser implements Parser<Operand> {

    @NotNull
    @Override
    public Operand apply(@NotNull String line) {
        return new OperandImpl(Double.parseDouble(line));
    }

    @Override
    public boolean test(@NotNull String line) {
        try {
            Double.parseDouble(line);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
