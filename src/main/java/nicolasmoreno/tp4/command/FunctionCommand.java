package nicolasmoreno.tp4.command;

import daoo.repl.Command;
import daoo.repl.Environment;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import nicolasmoreno.tp4.operandStack.OperandStackImpl;
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
        functionName = splitDeclaration[0];
        final String[] params = splitDeclaration[1].split(",");
        for (int i = 0; i < params.length; i++) {
            functionParameters.put(params[i], null);
        }
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        lastValue = stack;
        final Stack<Command> toExecute = new Stack<>();
        OperandStack remainingGlobalStack = stack;
        fillToExecuteCommands(toExecute, remainingGlobalStack);
        for (Command command : toExecute) {
            internalEnvironment.execute(command);
        }
        return remainingGlobalStack.push(internalEnvironment.stack().peek());
    }

    private void fillToExecuteCommands(Stack<Command> toExecute, OperandStack remainingGlobalStack) {
        for (String stringCommand : functionValue) {
            if (functionParameters.containsKey(stringCommand)) { // Si está dentro del parámetro
                if(functionParameters.get(stringCommand) == null) { // quiere decir que el valor del comando no se setteó todavia
                    final OperandStack.Result pop = remainingGlobalStack.pop();
                    remainingGlobalStack = pop.tail();
                    final Command command = internalEnvironment.evaluate(pop.element().print());
                    functionParameters.replace(stringCommand, command);
                    toExecute.push(command);
                } else {
                    toExecute.push(functionParameters.get(stringCommand));
                }
            } else { // si no está dentro de los parámetros, entonces puede ser un arithmetic
                final Command command = internalEnvironment.evaluate(stringCommand);
                toExecute.push(command);
            }
        }
    }

    @Override
    public OperandStack undo() {
        return lastValue;
    }
}
