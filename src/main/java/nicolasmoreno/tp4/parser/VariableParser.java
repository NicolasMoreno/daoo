package nicolasmoreno.tp4.parser;

import daoo.repl.Command;
import daoo.repl.Factory;
import nicolasmoreno.tp4.command.VariableCommand;
import nicolasmoreno.tp4.operand.OperandImpl;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class VariableParser implements Factory<Command> {

    private final String VARIABLE_KEYWORD = "[a-z]+( *)=( *)([a-z]|[0-9])+";
    private Map<String, Command> variableDeclarationMap;

    public VariableParser() {
        variableDeclarationMap = new HashMap<>();
    }

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        final String[] splittedCommand = line.split("=");
        final String varName = splittedCommand[0].trim();
        if (variableDeclarationMap.containsKey(varName)) {
            return variableDeclarationMap.get(varName);
        } else {
            final VariableCommand variableCommand = new VariableCommand(new OperandImpl(splittedCommand[1].trim()));
            variableDeclarationMap.put(splittedCommand[0].trim(), variableCommand);
            return variableCommand;
        }
    }

    @Override
    public boolean test(@NotNull String line) {
        if (line.matches(VARIABLE_KEYWORD)) {
            final String varName = line.split("=")[0].trim();
            return !variableDeclarationMap.containsKey(varName);
        } else {
            return variableDeclarationMap.containsKey(line);
        }
    }
}
