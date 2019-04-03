package nicolasmoreno.tp2.column;

import daoo.query.Column;
import daoo.query.Criteria;
import daoo.query.DefaultOperator;
import nicolasmoreno.tp2.expr.OrderByExpression;
import nicolasmoreno.tp2.operator.SortingOperator;
import nicolasmoreno.tp2.ops.Sortable;
import org.jetbrains.annotations.NotNull;
import nicolasmoreno.tp2.expr.NumericExpression;

import static daoo.query.Constant.constant;

public class StrColumn implements Column<String>, Sortable<String> {

    private String name;

    public StrColumn(String columnName) {this.name = columnName;}

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    public Criteria startsWith(String pattern) {
        return new Criteria(DefaultOperator.LIKE, constant(this.name), constant(pattern));
    }

    public NumericExpression<Integer> length() {
        return new NumericExpression<>(DefaultOperator.STRING_LENGTH, this);
    }

    @Override
    public OrderByExpression<String> asc() {
        return new OrderByExpression<>(SortingOperator.ASC, this);
    }

    @Override
    public OrderByExpression<String> desc() {
        return new OrderByExpression<>(SortingOperator.DESC, this);
    }
}
