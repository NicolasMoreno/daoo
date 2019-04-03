package nicolasmoreno.tp2.impl;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import nicolasmoreno.tp2.expr.OrderByExpression;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QueryImpl implements Query {

    private Clause clause;
    private Clause fromClause;
    private Clause whereClause;
    private Clause orderByClause;
    private Clause groupByClause;
    private List<Column> columnList;
    private Table table;
    private CompoundExpression compoundExpression;
    private List<OrderByExpression> orderBy;
    private List<Column> groupBy;

    public QueryImpl(Clause clause, Clause fromClause, Clause whereClause, Clause orderByClause, Clause groupByClause, List<Column> columnList, Table table,
                     CompoundExpression compoundExpression,
                     List<OrderByExpression> orderBy, List<Column> groupBy) {
        this.clause = clause;
        this.fromClause = fromClause;
        this.whereClause = whereClause;
        this.orderByClause = orderByClause;
        this.groupByClause = groupByClause;
        this.columnList = columnList;
        this.table = table;
        this.compoundExpression = compoundExpression;
        this.orderBy = orderBy;
        this.groupBy = groupBy;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public Table getTable() {
        return table;
    }

    public CompoundExpression getCompoundExpression() {
        return compoundExpression;
    }

    public List<OrderByExpression> getOrderBy() {
        return orderBy;
    }

    public List<Column> getGroupBy() {
        return groupBy;
    }

    public Clause getClause() {
        return clause;
    }

    public Clause getFromClause() {
        return fromClause;
    }

    public Clause getWhereClause() {
        return whereClause;
    }

    public Clause getOrderByClause() {
        return orderByClause;
    }

    public Clause getGroupByClause() {
        return groupByClause;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
