package nicolasmoreno.tp2.model.expr;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import nicolasmoreno.tp2.model.ops.NumericOps;
import org.jetbrains.annotations.NotNull;

public class NumericExpression<T extends Number> implements CompoundExpression<T>, NumericOps<T> {

    private final Operator operator;
    private final Expression<?>[] operands;

    /** Numeric<?> expression constructor. */
    public NumericExpression(Operator operator, Expression<?>... operands) {
        this.operator = operator;
        this.operands = operands;
    }

    @Override public Operator getOperator() { return operator; }

    @Override public Expression<?>[] getOperands() { return operands; }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
