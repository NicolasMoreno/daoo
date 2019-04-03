package nicolasmoreno.tp2.column;

import daoo.query.Column;
import daoo.query.visitor.Visitor;
import nicolasmoreno.tp2.ops.GroupByOps;
import org.jetbrains.annotations.NotNull;
import nicolasmoreno.tp2.ops.NumericOps;

public abstract class NumericColumn<T extends Number & Comparable<T>> implements Column<T>, NumericOps<T>, GroupByOps<T> {

    private String name;

    NumericColumn(String name) {
        this.name = name;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

}
