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

}
