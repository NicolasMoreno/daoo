package nicolasmoreno.tp2.ops;

import daoo.query.DefaultOperator;
import daoo.query.Expression;
import nicolasmoreno.tp2.expr.NumericExpression;

public interface GroupByOps<T extends Number> extends Expression<T> {

    default NumericExpression<T> avg() {
        return new NumericExpression<>(DefaultOperator.AVG, this);
    }

    default NumericExpression<T> max() { return new NumericExpression<>(DefaultOperator.MAX, this); }

    default NumericExpression<T> min() { return new NumericExpression<>(DefaultOperator.MIN, this); }

    default NumericExpression<T> sum() { return new NumericExpression<>(DefaultOperator.SUM, this); }
}
