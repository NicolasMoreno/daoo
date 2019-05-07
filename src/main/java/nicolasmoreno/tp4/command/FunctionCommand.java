package nicolasmoreno.tp4.command;

import daoo.repl.Command;
import daoo.repl.Environment;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FunctionCommand implements Command {

    private Environment functionEnvironment;
    private Queue<String> splittedFunctionValue;
    private Set<String> funcitonParameters;
    private String functionName;
    private OperandStack lastValue;

    public FunctionCommand(String line, Environment environment) {
        functionEnvironment = environment;
        splittedFunctionValue = new ArrayDeque<>();
        funcitonParameters = new HashSet<>();
        fillFunctionProperties(line);
    }

    private void fillFunctionProperties(String line) {
        final String[] splittedLine = line.split("=");
        getParameters(splittedLine[0]);
        splittedFunctionValue.addAll(Arrays.asList(splittedLine[1].trim().split(" ")));
    }

    private void getParameters(String functionDeclaration) {
        final String cleaned = functionDeclaration.trim().replaceAll("([()])", " ");
        final String[] splitDeclaration = cleaned.split(" ");
        for (int i = 0; i < splitDeclaration.length; i++) {
            if (i == 0) {
                functionName = splitDeclaration[i];
            } else {
                funcitonParameters.add(splitDeclaration[i]);
            }
        }
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        lastValue = stack;
        return lastValue;
    }

    @Override
    public OperandStack undo() {
        return lastValue;
    }
}
