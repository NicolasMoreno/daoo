package nicolasmoreno.tp4.parser;

import daoo.repl.Command;
import daoo.repl.Environment;
import daoo.repl.Factory;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

import static nicolasmoreno.tp4.operandStack.OperandStackImpl.SUCCESS_VARIABLE;


public class VariableParser implements Factory<Command> {

    private final String VARIABLE_KEYWORD = "[a-zA-Z]+( *)=( *)(\"[a-zA-Z]+\"|[a-zA-Z]+|[0-9]+)";
    private Environment internalEnvironment;

    public VariableParser(Environment environment) {
        internalEnvironment = environment;
    }

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        final String[] splittedCommand = line.split("=");
        final String varValue = splittedCommand[1].trim();
        return internalEnvironment.evaluate(varValue);
    }

    @Override
    public boolean test(@NotNull String line) {
        return line.matches(VARIABLE_KEYWORD);
    }

    public static final Command CREATED_VARIABLE = new Command() {
        private OperandStack lastValue;
        @Override
        public OperandStack execute(@NotNull OperandStack stack) {
            lastValue = stack;
            return SUCCESS_VARIABLE;
        }

        @Override
        public OperandStack undo() {
            return lastValue;
        }
    };
}
