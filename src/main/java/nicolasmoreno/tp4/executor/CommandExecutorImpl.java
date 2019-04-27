package nicolasmoreno.tp4.executor;

import daoo.repl.Command;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

public class CommandExecutorImpl  {

    private OperandStack globalOperandStack;

    public OperandStack execute(@NotNull Command command) {
        return command.execute(globalOperandStack);
    }

    public OperandStack undo() {
        return null;
    }

    public OperandStack redo() {
        return null;
    }
}
