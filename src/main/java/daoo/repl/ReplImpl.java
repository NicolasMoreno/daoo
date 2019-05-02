package daoo.repl;

import org.jetbrains.annotations.NotNull;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.Stack;

import static daoo.printer.TextPrinter.print;
import static nicolasmoreno.tp4.factory.OperandCommandFactory.operandCommand;
import static nicolasmoreno.tp4.operand.OperandImpl.INVALID_OPERAND;

public class ReplImpl extends Repl {

    private final String SPECIAL_CHARACTER = ":";
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;

    protected ReplImpl(@NotNull Environment environment) {
        super(environment);
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    @Override
    void loop(@NotNull InputStream input, @NotNull OutputStream output) {
        final Scanner scanner = new Scanner(input);
        while (scanner.hasNext(".+")) {
            final String line = scanner.nextLine();
            if (line.startsWith(SPECIAL_CHARACTER)) {
                evaluateEnvironmentCommand(line);
            } else {
                evaluateAndExecuteCommand(line);
            }
            final Operand operand = environment.stack().peek();
            print(output, operand.print());
        }
    }

    private void evaluateAndExecuteCommand(String line) {
        final Command command = environment.evaluate(line);
        undoStack.push(command);
        environment.execute(command);
    }


    private void evaluateEnvironmentCommand(@NotNull String line) {
        switch (line) {
            case SPECIAL_CHARACTER + "undo": {
                this.undoCommand();
                break;
            }
            case SPECIAL_CHARACTER + "redo" : {
                this.redoCommand();
                break;
            }
        }
    }

    private void undoCommand() {
        final Command command = !undoStack.empty() ? undoStack.pop() : Command.EMPTY_COMMAND;
        if (command != Command.EMPTY_COMMAND) {
            redoStack.push(command);
        }
        environment.undo(command); // TODO me está apilando el emptycommand y explota al querer hacer el undo
    }

    private void redoCommand() {
        final Command command = !redoStack.empty() ? redoStack.pop() : Command.EMPTY_COMMAND;
        environment.execute(command);
    }


}
