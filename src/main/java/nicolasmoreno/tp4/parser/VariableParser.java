package nicolasmoreno.tp4.parser;

import daoo.repl.Command;
import daoo.repl.Factory;
import nicolasmoreno.tp4.command.VariableCommand;
import nicolasmoreno.tp4.operand.OperandImpl;
import org.jetbrains.annotations.NotNull;

public class VariableParser implements Factory<Command> {

    private final String VARIABLE_KEYWORD = "[a-z]+( *)=( *)([a-z]|[0-9])+";

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        final String[] splittedCommand = line.split("=");
        return new VariableCommand(splittedCommand[0], new OperandImpl(splittedCommand[1]));
    }

    @Override
    public boolean test(@NotNull String line) {
        return line.matches(VARIABLE_KEYWORD);
        /*if (line.contains("=")) {
            final String[] splitted = line.split("=");
            final String functionDeclaration = splitted[0];
            return splitted.length == 2 && functionDeclaration.matches(VARIABLE_KEYWORD) && !splitted[1].matches(" *");
        } else return false;*/
    }
}
