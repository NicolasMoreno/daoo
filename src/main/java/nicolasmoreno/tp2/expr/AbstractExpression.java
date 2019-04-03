package nicolasmoreno.tp2.expr;

import daoo.query.CompoundExpression;
import daoo.query.Expression;
import daoo.query.Operator;

public abstract class AbstractExpression<T> implements CompoundExpression<T> {

    private final Operator operator;
    private final Expression<?>[] operands;

    public AbstractExpression(Operator operator, Expression<?>... operands) {
        this.operator = operator;
        this.operands = operands;
    }

    @Override
    public Operator getOperator() {
        return this.operator;
    }

    @Override
    public Expression<?>[] getOperands() {
        return this.operands;
    }

}
