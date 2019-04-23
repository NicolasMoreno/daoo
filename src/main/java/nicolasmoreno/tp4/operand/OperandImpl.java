package nicolasmoreno.tp4.operand;

import daoo.repl.Command;
import daoo.repl.Operand;
import nicolasmoreno.tp4.command.OperandCommand;
import org.jetbrains.annotations.NotNull;

public class OperandImpl<V> implements Operand {

    private Object value;

    public OperandImpl(Object value) {
        this.value = value;
    }

    @Override
    public <T> T as(@NotNull Class<T> type) {
        if (type.isInstance(Command.class) ) {
            return type.cast(value);
        } else {
            return null;
        }
    }

    @Override
    public String print() {
        return value.toString();
    }
}
