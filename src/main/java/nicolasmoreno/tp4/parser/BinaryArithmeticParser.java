package nicolasmoreno.tp4.parser;

import daoo.repl.Command;
import daoo.repl.Factory;
import nicolasmoreno.tp4.arithmetic.AdditionCommand;
import nicolasmoreno.tp4.arithmetic.DivideCommand;
import nicolasmoreno.tp4.arithmetic.MultiplyCommand;
import nicolasmoreno.tp4.arithmetic.SubtractionCommand;
import org.jetbrains.annotations.NotNull;

public class BinaryArithmeticParser implements Factory<Command> {


    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        switch (line) {
            case "+" : return new AdditionCommand();
            case "-" : return new SubtractionCommand();
            case "/" : return new DivideCommand();
            case "*" : return new MultiplyCommand();
            default: return Command.EMPTY_COMMAND;
        }
    }

    @Override
    public boolean test(@NotNull String line) {
        if (line.trim().length() > 1) return false;
        switch (line) {
            case "+" :
            case "-" :
            case "/" :
            case "*" :
                return true;
            default: return false;
        }
    }
}
