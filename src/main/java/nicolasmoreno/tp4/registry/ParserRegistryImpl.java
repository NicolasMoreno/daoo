package nicolasmoreno.tp4.registry;

import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.Parser;
import daoo.repl.ParserRegistry;
import nicolasmoreno.tp4.command.OperandCommand;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ParserRegistryImpl implements ParserRegistry {

    private List<Parser<Operand>> operandParserList;
    private List<Parser<Command>> commandParserList;

    public ParserRegistryImpl() {
        this.operandParserList = new ArrayList<>();
        this.commandParserList = new ArrayList<>();
    }

    @Override
    public void addOperandParser(@NotNull Parser<Operand> parser) {
        this.operandParserList.add(parser);
    }

    @Override
    public void addCommandParser(@NotNull Parser<Command> parser) {
        this.commandParserList.add(parser);
    }

    @NotNull
    @Override
    public Command match(@NotNull String line) {
        for (Parser<Operand> operandParser: operandParserList) {
            if (operandParser.test(line)) {
                final Operand foundOperand = operandParser.apply(line);
                return new OperandCommand(foundOperand);
            }
        }
        for (Parser<Command> commandParser: commandParserList) {
            if (commandParser.test(line)) return commandParser.apply(line);
        }
        return Command.EMPTY_COMMAND;
    }
}
