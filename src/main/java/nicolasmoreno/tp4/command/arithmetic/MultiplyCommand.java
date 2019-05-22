package nicolasmoreno.tp4.command.arithmetic;

import daoo.repl.OperandStack;
import nicolasmoreno.tp4.operand.OperandImpl;
import org.jetbrains.annotations.NotNull;

public class MultiplyCommand extends ArithmeticCommand {


    public MultiplyCommand() {
        super();
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        try {
            previousValue = stack; // Sólo asigna referencia.
            final OperandStack.Result result1 = stack.pop();
            final OperandStack.Result result2 = result1.tail().pop();
            return result2.tail().push(new OperandImpl(result1.element().as(Double.class) * result2.element().as(Double.class)));
        } catch (Exception e) {
            return previousValue;
        }
    }
}
