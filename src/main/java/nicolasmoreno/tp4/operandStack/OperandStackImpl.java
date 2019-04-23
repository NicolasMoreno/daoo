package nicolasmoreno.tp4.operandStack;

import daoo.repl.Operand;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Stack;

public class OperandStackImpl implements OperandStack {

    private Stack<Operand> stack;

    public OperandStackImpl() {
        this.stack = new Stack<>();
    }

    @Override
    public Result pop() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Stack Empty");
        final Operand poppedOperand = stack.pop();
        return new ResultImpl(this, poppedOperand);
    }

    @Override
    public Operand peek() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Stack Empty");
        return stack.peek();
    }

    @Override
    public OperandStack push(@NotNull Operand operand) {
        stack.push(operand);
        return this;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    private class ResultImpl implements Result {

        private OperandStack tail;
        private Operand element;

        private ResultImpl(OperandStack tail, Operand element) {
            this.tail = tail;
            this.element = element;
        }

        @Override
        public OperandStack tail() {
            return tail;
        }

        @Override
        public Operand element() {
            return element;
        }
    }
}
