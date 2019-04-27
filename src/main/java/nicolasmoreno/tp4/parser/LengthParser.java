package nicolasmoreno.tp4.parser;

import daoo.repl.Command;
import daoo.repl.Factory;
import nicolasmoreno.tp4.command.LengthCommand;
import org.jetbrains.annotations.NotNull;

public class LengthParser implements Factory<Command> {

    private final String LENGTH_COMMAND = "length";

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        return new LengthCommand();
    }

    @Override
    public boolean test(@NotNull String line) {
        return line.toLowerCase().equals(LENGTH_COMMAND);
    }
}
