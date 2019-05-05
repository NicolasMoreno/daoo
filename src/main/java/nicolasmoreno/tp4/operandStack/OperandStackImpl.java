package nicolasmoreno.tp4.operandStack;

import daoo.repl.Operand;
import daoo.repl.OperandStack;
import nicolasmoreno.tp4.operand.OperandImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static nicolasmoreno.tp4.operand.OperandImpl.INVALID_OPERAND;

public class OperandStackImpl implements OperandStack {

    private List<Operand> operandList;

    public OperandStackImpl() {
        operandList = new ArrayList<>();
    }

    private OperandStackImpl(List<Operand> operandList) {
        this.operandList = operandList;
    }


    @Override
    public Result pop() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Empty Stack");
        final Operand poppedOperand = this.operandList.get(this.operandList.size() - 1);
        final List<Operand> poppedList = ImmutableListFactory.pop(this.operandList);
        return new ResultImpl(new OperandStackImpl(poppedList), poppedOperand);
    }

    @Override
    public Operand peek() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Empty Stack");
        return operandList.get(operandList.size() - 1);
    }

    @Override
    public OperandStack push(@NotNull Operand operand) {
        final List<Operand> addedList = ImmutableListFactory.add(operandList, operand);
        return new OperandStackImpl(addedList);
    }

    @Override
    public boolean isEmpty() {
        return operandList.isEmpty();
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

    public static final OperandStack INVALID_STACK = new OperandStack() {
        @Override
        public Result pop() throws NoSuchElementException {
            return null;
        }

        @Override
        public Operand peek() throws NoSuchElementException {
            return INVALID_OPERAND;
        }

        @Override
        public OperandStack push(@NotNull Operand operand) {
            return null;
        }
    } ;

    public static final OperandStack SUCCESS_VARIABLE = new OperandStack() {
        @Override
        public Result pop() throws NoSuchElementException {
            return null;
        }

        @Override
        public Operand peek() throws NoSuchElementException {
            return new OperandImpl("");
        }

        @Override
        public OperandStack push(@NotNull Operand operand) {
            return null;
        }
    };
}
