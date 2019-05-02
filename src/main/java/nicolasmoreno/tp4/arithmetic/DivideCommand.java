package nicolasmoreno.tp4.arithmetic;

import daoo.repl.OperandStack;
import nicolasmoreno.tp4.operand.OperandImpl;
import org.jetbrains.annotations.NotNull;

public class DivideCommand extends ArithmeticCommand{

    public DivideCommand() {
        super();
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        try {
            previousValue = stack;
            final OperandStack.Result result1 = stack.pop();
            final OperandStack.Result result2 = result1.tail().pop();
            return result2.tail().push(new OperandImpl(result1.element().as(Double.class) / result2.element().as(Double.class)));
        } catch (Exception e) {
            return previousValue;
        }
    }
}
