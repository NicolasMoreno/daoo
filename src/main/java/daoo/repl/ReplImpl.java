package daoo.repl;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class ReplImpl extends Repl {

    public ReplImpl(ParserRegistry parsers, @NotNull CommandExecutor executor) {
        super(parsers, executor);
    }

    @Override
    void loop(@NotNull InputStream input, @NotNull OutputStream output) {
        final Scanner scanner = new Scanner(input);
        while (scanner.hasNext(".+")) {
            final Command command = parsers.match(scanner.nextLine());
            executor.execute(command);
        }
    }
}
