package nicolasmoreno.tp4.parser;

import daoo.repl.Command;
import daoo.repl.Factory;
import nicolasmoreno.tp4.command.FunctionCommand;
import org.jetbrains.annotations.NotNull;

public class FunctionParser implements Factory<Command> {

    private final String FUNCTION_KEYWORD = "[a-z]\\(([a-z],)*[a-z]\\)( *)=.*";

    public FunctionParser() {
    }

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        return new FunctionCommand(line);
    }

    @Override
    public boolean test(@NotNull String line) {
        return line.matches(FUNCTION_KEYWORD);
    }
}
