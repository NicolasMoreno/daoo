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
//        if (stack.isEmpty())
        previousStack = stack; // Tengo que encontrar la manera de duplicar
        final Operand literalStringOperand = stack.pop().element();
        return stack.push(new OperandImpl(literalStringOperand.print().length()));
    }

    @Override
    public OperandStack undo() {
        return null;
    }
}
