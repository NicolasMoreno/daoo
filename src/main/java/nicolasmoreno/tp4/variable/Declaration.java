package nicolasmoreno.tp4.variable;

import daoo.repl.Command;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

public interface Declaration extends Command {

    void declare();

    boolean test(String line);

    OperandStack call(@NotNull OperandStack stack);

}
