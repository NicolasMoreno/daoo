package nicolasmoreno.tp4.registry;

import daoo.repl.*;
import nicolasmoreno.tp4.operandStack.OperandStackImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static nicolasmoreno.tp4.factory.OperandCommandFactory.newOperandCommand;
import static nicolasmoreno.tp4.operand.OperandImpl.INVALID_OPERAND;

public class EnvironmentImpl implements Environment {

    private List<Factory<Operand>> operandFactoryList;
    private List<Factory<Command>> commandFactoryList;
    private OperandStack globalOperandStack;


    public EnvironmentImpl() {
        globalOperandStack = new OperandStackImpl();
        operandFactoryList = new ArrayList<>();
        commandFactoryList = new ArrayList<>();
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
        return Command.EMPTY_COMMAND;
    }

    @Override
    public void execute(@NotNull Command command) {
        this.globalOperandStack = command.execute(globalOperandStack);
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
        return null;
    }

    @Override
    public OperandStack stack() {
        return globalOperandStack;
    }
}
