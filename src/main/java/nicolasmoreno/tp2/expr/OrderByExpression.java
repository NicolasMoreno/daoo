package nicolasmoreno.tp2.expr;

import daoo.query.*;
import daoo.query.visitor.Visitable;
import daoo.query.visitor.Visitor;
import nicolasmoreno.tp2.operator.SortingOperator;
import nicolasmoreno.tp2.ops.Sortable;
import org.jetbrains.annotations.NotNull;

public class OrderByExpression<T> extends AbstractExpression<T> implements Visitable {

    public OrderByExpression(Operator operator, Expression<?>... operands) {
        super(operator, operands);
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }

}
