package nicolasmoreno.tp2.operator;

import daoo.query.Operator;
import org.jetbrains.annotations.NotNull;

public enum SortingOperator implements Operator {

    ASC("", "ASC"),
    DESC("", "DESC");

    @NotNull private final String[] template;

    SortingOperator(@NotNull String... template) {
        this.template = template;
    }

    @Override
    public int getArity() {
        return template.length - 1;
    }

    @NotNull
    @Override
    public String[] getTemplate() {
        return this.template;
    }
}
