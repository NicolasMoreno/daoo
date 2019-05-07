package nicolasmoreno.tp4.declaration;

import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

public interface Declaration{

    boolean test(String line);

    OperandStack call(@NotNull OperandStack stack);

}
