package nicolasmoreno.tp4.command;

import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import nicolasmoreno.tp4.operandStack.OperandStackImpl;
import org.jetbrains.annotations.NotNull;

public class OperandCommand implements Command {

    private Operand operand;
    private OperandStack lastStack;

    public OperandCommand(Operand operand) {
        this.operand = operand;
        this.lastStack = new OperandStackImpl();
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        if (!stack.isEmpty()) {
            this.lastStack = stack;
        }
        return stack.push(operand);
    }

    @Override
    public OperandStack undo() {
        return lastStack;
    }
}
