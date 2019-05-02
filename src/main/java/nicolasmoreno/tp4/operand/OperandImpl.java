package nicolasmoreno.tp4.operand;

import daoo.repl.Operand;
import org.jetbrains.annotations.NotNull;

public class OperandImpl implements Operand {

    private Object value;

    public OperandImpl(Object value) {
        this.value = value;
    }

    @Override
    public <T> T as(@NotNull Class<T> type) {
        return type.cast(value);
    }

    @Override
    public String print() {
        return value.toString();
    }

    public static final Operand INVALID_OPERAND = new Operand() {
        @Override
        public <T> T as(@NotNull Class<T> type) {
            return null;
        }

        @Override
        public String print() {
            return "Invalid Operand";
        }
    };
}
