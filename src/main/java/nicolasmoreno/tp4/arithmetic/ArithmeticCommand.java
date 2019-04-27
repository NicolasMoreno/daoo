package nicolasmoreno.tp4.arithmetic;

import daoo.repl.Command;
import daoo.repl.OperandStack;
import nicolasmoreno.tp4.operandStack.OperandStackImpl;
import org.jetbrains.annotations.NotNull;

public abstract class ArithmeticCommand implements Command {

    OperandStack previousValue;
    private String arithmeticValue;

    public ArithmeticCommand() {
        previousValue = new OperandStackImpl();
    }

    @Override
    public abstract OperandStack execute(@NotNull OperandStack stack);

    @Override
    public OperandStack undo() {
        return previousValue;
    }

}
