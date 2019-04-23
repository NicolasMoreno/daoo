package nicolasmoreno.tp4.executor;

import daoo.repl.Command;
import daoo.repl.CommandExecutor;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

public class CommandExecutorImpl implements CommandExecutor {

    private OperandStack globalOperandStack;

    @Override
    public OperandStack execute(@NotNull Command command) {
        return command.execute(globalOperandStack); //TODO globalOperandStack is null;
    }

    @Override
    public OperandStack undo() {
        return null;
    }

    @Override
    public OperandStack redo() {
        return null;
    }
}
