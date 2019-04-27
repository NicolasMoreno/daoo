package daoo.printer;

import java.io.IOException;
import java.io.OutputStream;

public class TextPrinter {

    public static void print(OutputStream out, String text) {
        try {
            out.write((text+'\n').getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
