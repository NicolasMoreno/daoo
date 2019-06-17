package daoo.repl;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.When;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import nicolasmoreno.tp4.factory.ParserFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.util.NoSuchElementException;

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
        assumeTrue(!text.contains("\""));
        final Repl repl = repl( '"' + text + '"');
        final Operand result = repl.environment.stack().peek();
        assertEquals("Asserting literal",'"' +  text + '"', result.as(String.class));
    }

    @Property
    public void lengthTest(@When(seed = -8871128074932915312L) String text) {
        assumeTrue(!text.equals(""));
        assumeTrue(!text.contains("\""));
        final Repl repl = repl('"' + text + '"', "length");
        final Operand result = repl.environment.stack().peek();
        assertEquals("Asserting length", text.length(), (int)result.as(Integer.class));
    }

    @Property
    public void functionOneParamTest(@InRange(min = "-100", max = "100") Double left,
                             @InRange(min = "-100", max = "100") Double right) {
        final String functionOperand = "f(x) = x x +";
        final String functionCall = "f";
        final Repl repl = repl(String.valueOf(left), String.valueOf(right), functionOperand, functionCall);
        final Operand result = repl.environment.stack().peek();
        assertEquals("Asserting 1 param sum", Double.valueOf(right + right), result.as(Double.class));
    }

    @Property
    public void functionTwoParamsTest(@InRange(min = "-100", max = "100") Double left,
                                      @InRange(min = "-100", max = "100") Double right) {
        final String functionOperand = "f(x,y) = x y +";
        final String functionCall = "f";
        final Repl repl = repl(String.valueOf(left), String.valueOf(right), functionOperand, functionCall);
        final Operand result2 = repl.environment.stack().peek();
        assertEquals("Asserting 2 param sum", Double.valueOf(left + right), result2.as(Double.class));
    }

    @Property
    public void functionWithLengthTest(String text) { //3626116356312657247
        assumeTrue(!text.equals(""));
        assumeTrue(!text.contains("\""));
        final String functionOperand = "f(x) = x length";
        final String functionCall = "f";
        final Repl repl = repl('"' + text + '"', functionOperand, functionCall);
        final Operand result = repl.environment.stack().peek();
        assertEquals("Asserting function with length", text.length(), (int)result.as(Integer.class));
    }

    @Property
    public void variableNumberTest(@InRange(min = "-1000", max = "1000") Double number) {
        final String variableDeclaration = "x = " + number;
        final String variableCall = "x";
        final String first = "\"test\"";
        final Repl repl = repl(first, variableDeclaration, variableCall);
        final Operand result = repl.environment.stack().peek();
        assertEquals("Asserting number variable", number, result.as(Double.class));
    }

    @Test(expected = NoSuchElementException.class)
    public void notEnoughValuesFunctionTest() {
        final Double number = 20d;
        final String functionOperand = "f(x,y) = x y +";
        final String functionCall = "f";
        repl(String.valueOf(number), functionOperand, functionCall);
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

