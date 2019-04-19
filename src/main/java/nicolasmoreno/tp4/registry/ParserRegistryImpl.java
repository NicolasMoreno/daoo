package nicolasmoreno.tp4.registry;

import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.Parser;
import daoo.repl.ParserRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ParserRegistryImpl implements ParserRegistry {

    private List<Parser> parserList;

    public ParserRegistryImpl() {
        this.parserList = new ArrayList<>();
    }

    @Override
    public void addOperandParser(@NotNull Parser<Operand> parser) {
        this.parserList.add(parser);
    }

    @Override
    public void addCommandParser(@NotNull Parser<Command> parser) {
        this.parserList.add(parser);
    }

    @NotNull
    @Override
    public Command match(@NotNull String line) {
        return null;
    }
}
