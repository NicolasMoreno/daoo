package nicolasmoreno.tp4.command;

import daoo.repl.Operand;
import daoo.repl.OperandStack;
import nicolasmoreno.tp4.variable.Declaration;
import org.jetbrains.annotations.NotNull;

import static nicolasmoreno.tp4.operandStack.OperandStackImpl.INVALID_STACK;
import static nicolasmoreno.tp4.operandStack.OperandStackImpl.SUCCESS_VARIABLE;

public class VariableCommand implements Declaration {

    private String variableName;
    private Operand variableValue;
    private OperandStack lastValue;

    public VariableCommand(String variableName, Operand variableValue) {
        this.variableName = variableName;
        this.variableValue = variableValue;
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        return SUCCESS_VARIABLE;
    }

    @Override
    public OperandStack undo() {
        if (lastValue != null) return lastValue;
        else return INVALID_STACK;
    }

    @Override
    public void declare() {

    }

    @Override
    public boolean test(String line) {
        return line.trim().equals(variableName);
    }

    @Override
    public OperandStack call(@NotNull OperandStack stack) {
        this.lastValue = stack;
        return stack.push(variableValue);
    }
}
