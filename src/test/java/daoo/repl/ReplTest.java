package daoo.repl;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import nicolasmoreno.tp4.factory.ParserFactory;
import org.junit.runner.RunWith;

import java.io.*;

import static daoo.repl.ReplTestBuilder.repl;
import static nicolasmoreno.tp4.factory.ParserFactory.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

@RunWith(JUnitQuickcheck.class)
public class ReplTest {

    @Property
    public void additionTest(
            @InRange(min = "-100", max = "100") Double left,
            @InRange(min = "-100", max = "100") Double right
    ){
        final Repl repl = repl(String.valueOf(left), String.valueOf(right), "+");
        final Operand resultOperand = repl.environment.stack().peek();
        assertEquals("Asserting Addition", (double)left + right, resultOperand.as(Double.class), 0.00001);
    }

    @Property
    public void subtractionTest(
            @InRange(min = "-100", max = "100") Double left,
            @InRange(min = "-100", max = "100") Double right
    ){
        final Repl repl = repl(String.valueOf(left), String.valueOf(right), "-");
        final Operand resultOperand = repl.environment.stack().peek();
        assertEquals("Asserting Subtraction", (double)right - left, resultOperand.as(Double.class), 0.00001);
    }

    @Property
    public void multiplicationTest(
            @InRange(min = "-100", max = "100") Double left,
            @InRange(min = "-100", max = "100") Double right
    ){
        final Repl repl = repl(String.valueOf(left), String.valueOf(right), "*");
        final Operand resultOperand = repl.environment.stack().peek();
        assertEquals("Asserting Multiplication", (double)right * left, resultOperand.as(Double.class), 0.00001);
    }

    @Property
    public void divisionTest(
            @InRange(min = "-100", max = "100") Double left,
            @InRange(min = "-100", max = "100") Double right
    ){
        final Repl repl = repl(String.valueOf(left), String.valueOf(right), "/");
        final Operand resultOperand = repl.environment.stack().peek();
        assertEquals("Asserting Division", (double)right / left, resultOperand.as(Double.class), 0.00001);
    }

    @Property
    public void operandTest(Double number) {
        final Repl repl = repl(String.valueOf(number));
        final Operand resultOperand = repl.environment.stack().peek();
        assertEquals("Asserting operand", number, resultOperand.as(Double.class), 0.000001);
    }

    @Property
    public void literalTest(String text) {
        assumeTrue(!text.equals(""));
        final Repl repl = repl( '"' + text + '"');
        final Operand result = repl.environment.stack().peek();
        assertEquals("Asserting literal", text, result.as(String.class));
    }

    @Property
    public void lengthTest( String text) { //buscar minimo string length
        assumeTrue(!text.equals(""));
        final Repl repl = repl('"' + text + '"', "length");
        final Operand result = repl.environment.stack().peek();
        assertEquals("Asserting length", text.length(), (int)result.as(Integer.class));
    }
}

class ReplTestBuilder {

    static Repl repl(String ...lines) {
        String inputLine = "";
        for (String line: lines) {
            inputLine = inputLine.concat(line + "\n");
        }
        InputStream inputStream = new ByteArrayInputStream(inputLine.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();

        ReplImpl repl = new ReplImpl(environment());
        repl.loop(inputStream, outputStream);
        return repl;
    }

    private static Environment environment() {
        return ParserFactory.environment()
                .addOperand(doubleParser())
                .addOperand(literalParser())
                .addCommand(binaryArithmeticParser())
                .addCommand(lengthParser())
                .addDeclarationParser()
                .build();
    }
}

