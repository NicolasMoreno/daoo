package nicolasmoreno.tp2.ops;

import nicolasmoreno.tp2.expr.OrderByExpression;

public interface Sortable<T extends Comparable<T>> {

    OrderByExpression<T> asc();

    OrderByExpression<T> desc();
}
