package nicolasmoreno.tp2.expr;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import nicolasmoreno.tp2.operator.SortingOperator;
import nicolasmoreno.tp2.ops.NumericOps;
import nicolasmoreno.tp2.ops.Sortable;
import org.jetbrains.annotations.NotNull;

public class NumericExpression<T extends Number> extends AbstractExpression<T> implements CompoundExpression<T>, NumericOps<T>, Sortable {

    public NumericExpression(DefaultOperator operator, Expression<?>... operands) {
        super(operator, operands);
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public OrderByExpression<T> asc() {
        return new OrderByExpression<>(SortingOperator.ASC, this);
    }

    @Override
    public OrderByExpression<T> desc() {
        return new OrderByExpression<>(SortingOperator.DESC, this);
    }
}
