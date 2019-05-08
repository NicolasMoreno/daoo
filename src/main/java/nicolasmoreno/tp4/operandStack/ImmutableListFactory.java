package nicolasmoreno.tp4.operandStack;

import daoo.repl.Operand;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ImmutableListFactory {

    public static <T extends Operand> List<T> add(List<T> oldList, T newElement) {
        final ArrayList<T> newList = new ArrayList<>(oldList);
        newList.add(newElement);
        return newList;
    }

    @NotNull
    @Contract("_ -> new")
    public static <T extends Operand> List<T> pop(List<T> oldList) {
        final ArrayList<T> newList = new ArrayList<>(oldList);
        newList.remove(oldList.size() - 1);
        return newList;
    }

    public static Result get(List<String> oldList) {
        if (oldList.size() > 0) {
            final ArrayList<String> newList = new ArrayList<>(oldList);
            final String removed = newList.remove(oldList.size() - 1);
            return new Result() {
                @Override
                public List tail() {
                    return newList;
                }

                @Override
                public String element() {
                    return removed;
                }
            };
        } else {
            return new Result() {
                @Override
                public List tail() {
                    return null;
                }

                @Override
                public String element() {
                    return null;
                }
            };
        }
    }

    public interface Result {

        /** Stack containing other operands left after pop (if any). */
        List<String> tail();

        /** Pop Operand result. */
        String element();
    }

}
