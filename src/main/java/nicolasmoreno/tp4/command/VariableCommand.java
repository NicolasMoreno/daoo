package nicolasmoreno.tp4.command;

import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

import static nicolasmoreno.tp4.operandStack.OperandStackImpl.INVALID_STACK;
import static nicolasmoreno.tp4.operandStack.OperandStackImpl.SUCCESS_VARIABLE;

public class VariableCommand implements Command {

    private Operand variableValue;
    private OperandStack lastValue;
    private boolean isDeclared;

    public VariableCommand(Operand variableValue) {
        this.variableValue = variableValue;
        this.isDeclared = false;
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        if (!isDeclared) {
            isDeclared = true;
            return SUCCESS_VARIABLE;
        } else {
            this.lastValue = stack;
            return stack.push(variableValue);
        }
    }

    @Override
    public OperandStack undo() {
        if (lastValue != null) return lastValue;
        else return INVALID_STACK;
    }

}
