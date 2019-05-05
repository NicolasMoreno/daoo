package nicolasmoreno.tp4.command;

import daoo.repl.Command;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

public class FunctionCommand implements Command {



    public FunctionCommand(String line) {
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        return null;
    }

    @Override
    public OperandStack undo() {
        return null;
    }
}
