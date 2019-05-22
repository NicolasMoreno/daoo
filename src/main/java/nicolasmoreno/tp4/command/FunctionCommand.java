package nicolasmoreno.tp4.command;

import daoo.repl.Command;
import daoo.repl.Environment;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static nicolasmoreno.tp4.operandStack.ImmutableListFactory.*;

public class FunctionCommand implements Command {

    private Environment internalEnvironment;
    private Stack<String> functionValue;
    private Map<String, Command> functionParameters;
    private String functionName;
    private OperandStack lastValue;

    public FunctionCommand(String line, Environment environment) {
        internalEnvironment = environment;
        functionValue = new Stack<>();
        functionParameters = new HashMap<>();
        fillFunctionProperties(line);
    }

    private void fillFunctionProperties(String line) {
        final String[] splittedLine = line.split("=");
        getParameters(splittedLine[0]);
        functionValue.addAll(Arrays.asList(splittedLine[1].trim().split(" ")));
    }

    private void getParameters(String functionDeclaration) {
        final String cleaned = functionDeclaration.trim().replaceAll("([()])", " ");
        final String[] splitDeclaration = cleaned.split(" ");
        for (int i = 0; i < splitDeclaration.length; i++) {
            if (i != 0) {
                functionParameters.put(splitDeclaration[i], null);
            } else {
                functionName = splitDeclaration[i];
            }
        }
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        // TODO VER QUE PUEDO MEJORAR ACá
        lastValue = stack;
        final Stack<Command> toExecute = new Stack<>();
        OperandStack remainingGlobalstack;
        for (String stringCommand : functionValue) {
            if (functionParameters.containsKey(stringCommand) && functionParameters.get(stringCommand) != null) {
                final OperandStack.Result pop = stack.pop();
                remainingGlobalstack = pop.tail();
                final Command command = internalEnvironment.evaluate(pop.element().print());
                functionParameters.replace(stringCommand, command);
                toExecute.push(command);
            } else {
                final Command command = internalEnvironment.evaluate(stringCommand);
                toExecute.push(command);
            }
        }

        for (Command command : toExecute) {
            command.execute(internalEnvironment.stack());
        }
        return stack;
    }

    @Override
    public OperandStack undo() {
        return lastValue;
    }
}
