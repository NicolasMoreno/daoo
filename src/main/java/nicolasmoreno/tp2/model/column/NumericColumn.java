package nicolasmoreno.tp2.model.column;

import daoo.query.Column;
import daoo.query.DefaultOperator;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;
import nicolasmoreno.tp2.model.ops.NumericOps;
import nicolasmoreno.tp2.model.expr.NumericExpression;

public abstract class NumericColumn<T extends Number & Comparable<T>> implements Column<T>, NumericOps<T> {

    private String name;

    public NumericColumn(String name) {
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

    public NumericExpression<T> avg() {
        return new NumericExpression<>(DefaultOperator.AVG, this);
    }

    public NumericExpression<T> max() { return new NumericExpression<>(DefaultOperator.MAX, this); }

    public NumericExpression<T> min() { return new NumericExpression<>(DefaultOperator.MIN, this); }

    public NumericExpression<T> sum() { return new NumericExpression<>(DefaultOperator.SUM, this); }
}
