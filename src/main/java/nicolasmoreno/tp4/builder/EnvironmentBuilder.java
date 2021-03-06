package nicolasmoreno.tp4.builder;

import daoo.repl.Command;
import daoo.repl.Environment;
import daoo.repl.Factory;
import daoo.repl.Operand;
import nicolasmoreno.tp1.builder.Builder;
import nicolasmoreno.tp4.registry.EnvironmentImpl;

import static nicolasmoreno.tp4.factory.ParserFactory.declarationParser;

public class EnvironmentBuilder implements Builder<Environment> {

    private Environment environment;

    public EnvironmentBuilder() {
        this.environment = new EnvironmentImpl();
    }

    public EnvironmentBuilder addOperand(Factory<Operand> operandParser) {
        environment.addOperandFactory(operandParser);
        return this;
    }

    public EnvironmentBuilder addCommand(Factory<Command> commandParser) {
        environment.addCommandFactory(commandParser);
        return this;
    }

    public EnvironmentBuilder addDeclarationParser() {
        environment.addCommandFactory(declarationParser(environment.copy()));
        return this;
    }

    @Override
    public Environment build() {
        return this.environment;
    }
}
