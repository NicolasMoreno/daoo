package daoo.repl;

import org.jetbrains.annotations.NotNull;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import static daoo.printer.TextPrinter.print;

public class ReplImpl extends Repl {

    protected ReplImpl(@NotNull Environment environment) {
        super(environment);
    }

    @Override
    void loop(@NotNull InputStream input, @NotNull OutputStream output) {
        final Scanner scanner = new Scanner(input);
        while (scanner.hasNext(".+")) {
            final Command command = environment.evaluate(scanner.nextLine());
            environment.execute(command);
            final Operand operand = environment.stack().peek();
            print(output, operand.print());
        }
    }
}
