package nicolasmoreno.tp4.registry;

import daoo.repl.*;
import nicolasmoreno.tp4.operandStack.OperandStackImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nicolasmoreno.tp4.factory.OperandCommandFactory.newOperandCommand;
import static nicolasmoreno.tp4.factory.ParserFactory.variableParser;
import static nicolasmoreno.tp4.operand.OperandImpl.INVALID_OPERAND;
import static nicolasmoreno.tp4.operandStack.OperandStackImpl.SUCCESS_VARIABLE;

public class EnvironmentImpl implements Environment {

    private List<Factory<Operand>> operandFactoryList;
    private List<Factory<Command>> commandFactoryList;
    private Factory<Command> variableParser;
    private Map<String, Command> variableMap;
    private OperandStack globalOperandStack;

    public EnvironmentImpl() {
        globalOperandStack = new OperandStackImpl();
        operandFactoryList = new ArrayList<>();
        commandFactoryList = new ArrayList<>();
        variableParser = variableParser(this.copy());
        variableMap = new HashMap<>();
    }

    private EnvironmentImpl(List<Factory<Operand>> operandFactoryList, List<Factory<Command>> commandFactoryList) {
        this.operandFactoryList = operandFactoryList;
        this.commandFactoryList = commandFactoryList;
        globalOperandStack = new OperandStackImpl();
    }

    @Override
    public void addOperandFactory(@NotNull Factory<Operand> factory) {
        this.operandFactoryList.add(factory);
    }

    @Override
    public void addCommandFactory(@NotNull Factory<Command> factory) {
        this.commandFactoryList.add(factory);
    }

    @NotNull
    @Override
    public Command evaluate(@NotNull String input) {
        for (Factory<Operand> operandParser: operandFactoryList) {
            if (operandParser.test(input)) {
                final Operand foundOperand = operandParser.apply(input);
                return newOperandCommand(foundOperand);
            }
        }
        for (Factory<Command> commandParser: commandFactoryList) {
            if (commandParser.test(input)) return commandParser.apply(input);
        }
        if (variableParser.test(input)) {
            this.variableMap.put(input.split("=")[0].trim(), variableParser.apply(input));
        } else if (variableMap.containsKey(input)) {
            return variableMap.get(input);
        }
        return Command.EMPTY_COMMAND;

    }

    @Override
    public void execute(@NotNull Command command) {
        final OperandStack executed = command.execute(globalOperandStack);
        globalOperandStack = executed;

    }

    @Override
    public void undo(@NotNull Command command) {
        try {
            globalOperandStack = command.undo();
        } catch (UnsupportedOperationException e) {
            globalOperandStack = globalOperandStack.push(INVALID_OPERAND);
        }
    }

    @NotNull
    @Override
    public Environment copy() {
        return new EnvironmentImpl(this.operandFactoryList, this.commandFactoryList);
    }

    @Override
    public OperandStack stack() {
        return globalOperandStack;
    }
}
