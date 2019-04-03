package nicolasmoreno.tp2.builder;

import daoo.query.*;
import nicolasmoreno.tp2.clause.FromClause;
import nicolasmoreno.tp2.clause.SelectClause;
import nicolasmoreno.tp2.clause.WhereClause;
import nicolasmoreno.tp2.expr.OrderByExpression;
import nicolasmoreno.tp2.impl.QueryImpl;
import nicolasmoreno.tp1.builder.Builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryBuilder implements Builder<Query> {

    private Clause statementClause;
    private Clause fromClause;
    private Clause whereClause;
    private Clause orderByClause;
    private Clause groupByClause;
    private List<Column> columnList;
    private Table table;
    private CompoundExpression compoundExpression;
    private List<OrderByExpression> orderBy;
    private List<Column> groupBy;

    public QueryBuilder() {
        this.columnList = new ArrayList<>();
    }

    public QueryBuilder select(Column... columns) {
        this.statementClause = new SelectClause();
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
        this.fromClause = new FromClause();
        this.table = table;
        return this;
    }

    public QueryBuilder where(CompoundExpression expression) {
        this.whereClause = new WhereClause();
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
        return new QueryImpl(this.statementClause, this.fromClause, this.whereClause, this.columnList, this.table, this.compoundExpression, this.orderBy, this.groupBy);
    }
}
