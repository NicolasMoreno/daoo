package nicolasmoreno.tp2.clause;

import daoo.query.Clause;
import nicolasmoreno.tp2.expr.OrderByExpression;

import java.util.List;

public class OrderByClause implements Clause<String> {

    private List<OrderByExpression> orderBy;

    public OrderByClause(List<OrderByExpression> orderBy) {
        this.orderBy = orderBy;
    }

    public List<OrderByExpression> getOrderBy() {
        return orderBy;
    }

}
