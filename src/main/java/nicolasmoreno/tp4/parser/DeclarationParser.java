package nicolasmoreno.tp4.parser;

import daoo.repl.Command;
import daoo.repl.Environment;
import daoo.repl.Factory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static nicolasmoreno.tp4.factory.ParserFactory.*;


public class DeclarationParser implements Factory<Command> {

    private Factory<Command> variableParser;
    private Factory<Command> functionParser;
    private Map<String, Command> variableMap;


    public DeclarationParser(Environment environment) {
        variableMap = new HashMap<>();
        variableParser = variableParser(environment);
        functionParser = functionParser();
    }

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        final String[] splitString = line.split("=");
        final String varName = splitString[0].indexOf('(') == -1 ?
                splitString[0].trim() :
                splitString[0].substring(0, splitString[0].indexOf('('));
        if (variableMap.containsKey(varName)) {
            return variableMap.get(varName);
        } else {
            this.variableMap.put(varName, evaluate(line));
            return Command.EMPTY_COMMAND;
        }
    }

    private Command evaluate(String line) {
        if (variableParser.test(line)) return variableParser.apply(line);
        else return functionParser.apply(line);
    }

    @Override
    public boolean test(@NotNull String line) {
        return variableMap.containsKey(line) || variableParser.test(line) || functionParser.test(line);
    }
}
