package nicolasmoreno.tp4.builder;

import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.Parser;
import daoo.repl.ParserRegistry;
import nicolasmoreno.tp1.builder.Builder;
import nicolasmoreno.tp4.registry.ParserRegistryImpl;

public class ParserRegistryBuilder implements Builder<ParserRegistry> {

    private ParserRegistry parserRegistry;

    public ParserRegistryBuilder() {
        this.parserRegistry = new ParserRegistryImpl();
    }

    public ParserRegistryBuilder addOperand(Parser<Operand> operandParser) {
        parserRegistry.addOperandParser(operandParser);
        return this;
    }

    public ParserRegistryBuilder addCommand(Parser<Command> commandParser) {
        parserRegistry.addCommandParser(commandParser);
        return this;
    }

    @Override
    public ParserRegistry build() {
        return this.parserRegistry;
    }
}
