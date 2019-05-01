package nicolasmoreno.tp4.arithmetic;

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
            final Double element1 = stack.pop().element().as(Double.class);
            final Double element2 = stack.pop().element().as(Double.class);
            return stack.push(new OperandImpl(element1 * element2));
        } catch (Exception e) {
            return previousValue;
        }
    }
}
