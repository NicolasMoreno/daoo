package nicolasmoreno.tp4.parser;

import daoo.repl.Command;
import daoo.repl.Environment;
import daoo.repl.Factory;
import nicolasmoreno.tp4.command.FunctionCommand;
import org.jetbrains.annotations.NotNull;

public class FunctionParser implements Factory<Command> {

    private final String FUNCTION_KEYWORD = "[a-zA-Z]\\(([a-z],)*[a-z]\\)( *)=.*";
    private Environment internalEnvironment;

    public FunctionParser(Environment internalEnvironment) {
        this.internalEnvironment = internalEnvironment;
    }

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        return new FunctionCommand(line, internalEnvironment.copy());
    }

    @Override
    public boolean test(@NotNull String line) {
        return line.matches(FUNCTION_KEYWORD);
    }
}
