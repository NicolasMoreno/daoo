package daoo.printer;

import daoo.repl.Operand;
import daoo.repl.OperandStack;

import java.io.IOException;
import java.io.OutputStream;
import java.util.NoSuchElementException;

public class TextPrinter {

    public static void print(OutputStream out, OperandStack stack) {
        try {
            final Operand peek = stack.peek();
            out.write((peek.print() +'\n').getBytes());
        } catch (NoSuchElementException noElem) {
            try {
                out.write("\n".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
