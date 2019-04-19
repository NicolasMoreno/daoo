package nicolasmoreno.tp4.factory;

import daoo.repl.CommandExecutor;
import daoo.repl.ParserRegistry;
import daoo.repl.Repl;
import daoo.repl.ReplImpl;
import nicolasmoreno.tp4.executor.CommandExecutorImpl;

public class ReplFactory {

    private static CommandExecutor executor() {
        return new CommandExecutorImpl();
    }

    public static Repl repl(ParserRegistry parserRegistry) {
        return new ReplImpl(parserRegistry, executor());
    }
}
