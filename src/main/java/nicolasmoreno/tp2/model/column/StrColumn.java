package nicolasmoreno.tp2.model.column;

import daoo.query.Column;
import daoo.query.Criteria;
import daoo.query.DefaultOperator;
import org.jetbrains.annotations.NotNull;
import nicolasmoreno.tp2.model.expr.NumericExpression;

import static daoo.query.Constant.constant;

public class StrColumn implements Column<String> {

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

}
