package nicolasmoreno.tp2.builder;

import daoo.query.*;
import nicolasmoreno.tp2.clause.*;
import nicolasmoreno.tp2.expr.OrderByExpression;
import nicolasmoreno.tp2.impl.QueryImpl;
import nicolasmoreno.tp1.builder.Builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryBuilder implements Builder<Query> {

    private List<Column> columnList;
    private Table table;
    private CompoundExpression compoundExpression;
    private List<OrderByExpression> orderBy;
    private List<Column> groupBy;

    public QueryBuilder() {
        this.columnList = new ArrayList<>();
    }

    public QueryBuilder select(Column... columns) {
        this.columnList.addAll(Arrays.stream(columns).collect(Collectors.toList()));
        return this;
    }

    /**
     * Represents SELECT * FROM table...
     *
     * SELECT *, COUNT(Country) FROM Customers
     * GROUP BY Country
     * ORDER BY Country DESC;
     *
     *
     */
    public QueryBuilder selectAll() {
        return this;
    }

    public QueryBuilder from(Table table) {
        this.table = table;
        return this;
    }

    public QueryBuilder where(CompoundExpression expression) {
        this.compoundExpression = expression;
        return this;
    }

    public QueryBuilder orderBy(OrderByExpression... expressions) {
        this.orderBy = Arrays.stream(expressions).collect(Collectors.toList());
        return this;
    }

    public QueryBuilder groupBy(Column... columns) {
        this.groupBy = Arrays.stream(columns).collect(Collectors.toList());
        return this;
    }

    @Override
    public Query build() {
        if (this.columnList.size() == 0 ||
                table == null ||
                this.compoundExpression == null) throw new RuntimeException("Syntax Error");
        final SelectClause selectClause = new SelectClause(this.columnList);
        final FromClause fromClause = new FromClause(this.table);
        final WhereClause whereClause= new WhereClause(this.compoundExpression);
        final OrderByClause orderByClause = new OrderByClause(this.orderBy);
        final GroupByClause groupByClause = new GroupByClause(this.groupBy);
        return new QueryImpl(selectClause, fromClause, whereClause, orderByClause, groupByClause);
    }
}
