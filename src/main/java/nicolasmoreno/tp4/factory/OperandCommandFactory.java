package nicolasmoreno.tp4.factory;

import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import nicolasmoreno.tp4.command.OperandCommand;

public class OperandCommandFactory {

    public static Command newOperandCommand(Operand operand) {
        return new OperandCommand(operand);
    }

    public static Command operandCommand(Operand operand, OperandStack stack) {
        return new OperandCommand(operand, stack);
    }
}
