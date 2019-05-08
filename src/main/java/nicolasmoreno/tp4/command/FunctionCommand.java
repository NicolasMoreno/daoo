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
    private Map<String, Operand> functionParameters;
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
        Stack<Command> commandsToExecute = new Stack<>();
        final OperandStack.Result pop1 = stack.pop();
        OperandStack stacksResults = pop1.tail();
        Result result = get(functionValue);
        commandsToExecute.push(new OperandCommand(pop1.element()));
        while (result.element() != null && !stacksResults.isEmpty()) {
            if (functionParameters.containsKey(result.element())) {
                final OperandStack.Result pop = stacksResults.pop();
                stacksResults = pop.tail();
                commandsToExecute.push(new OperandCommand(pop.element()));
            } else {
                commandsToExecute.push(internalEnvironment.evaluate(result.element()));
            }
            result = get(result.tail());
        }
        Command command = commandsToExecute.pop();
        if (commandsToExecute.size() > 0) {
            internalEnvironment.execute(command);
            while (commandsToExecute.size() > 1) {
                internalEnvironment.execute(command);
                command = commandsToExecute.pop();
            }
            final OperandStack executed = commandsToExecute.pop().execute(internalEnvironment.stack());
            return stacksResults.push(executed.pop().element());
        } else {
            final OperandStack executed = command.execute(internalEnvironment.stack());
            return stacksResults.push(executed.pop().element());
        }
    }

    @Override
    public OperandStack undo() {
        return lastValue;
    }
}
