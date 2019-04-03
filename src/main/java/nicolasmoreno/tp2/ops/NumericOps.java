package nicolasmoreno.tp2.ops;

import daoo.query.Criteria;
import daoo.query.DefaultOperator;
import daoo.query.Expression;
import nicolasmoreno.tp2.expr.NumericExpression;

import static daoo.query.Constant.constant;

public interface NumericOps<T extends Number> extends Expression<T> {

    default Criteria between(T min, T max) {
        return between(constant(min), constant(max));
    }

    default Criteria between(T min, Expression<T> max) {
        return between(constant(min), max);
    }

    default Criteria between(Expression<T> min, T max) {
        return between(min, constant(max));
    }

    default Criteria between(Expression<T> min, Expression<T> max) {
        return new Criteria(DefaultOperator.BETWEEN, this, min, max);
    }

    default Criteria lt(T cons) {
        return new Criteria(DefaultOperator.LT, this, constant(cons));
    }

    default Criteria lt(NumericExpression<T> expr) {
        return new Criteria(DefaultOperator.LT, this, expr);
    }

    default Criteria le(T lowerEqualsTo) {
        return new Criteria(DefaultOperator.LE, this, constant(lowerEqualsTo));
    }

    default Criteria gt(T greaterTo) {
        return new Criteria(DefaultOperator.GT, this, constant(greaterTo));
    }

    default Criteria ge(T greaterEqualsTo) {
        return new Criteria(DefaultOperator.GE, this, constant(greaterEqualsTo));
    }

}
