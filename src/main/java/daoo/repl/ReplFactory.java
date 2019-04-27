package daoo.repl;

public class ReplFactory {

    public static Repl repl(Environment environment) {
        return new ReplImpl(environment);
    }
}
