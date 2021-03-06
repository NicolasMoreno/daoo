package nicolasmoreno.tp4.command;

import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import nicolasmoreno.tp4.operand.OperandImpl;
import org.jetbrains.annotations.NotNull;

public class LengthCommand implements Command {

    private OperandStack previousStack;

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        previousStack = stack;
        final Operand literalStringOperand = stack.pop().element();
        final String value = literalStringOperand.print();
        return stack.push(new OperandImpl(value.replaceAll("\"", "").length()));
    }

    @Override
    public OperandStack undo() {
        return previousStack;
    }
}
