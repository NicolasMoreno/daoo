package nicolasmoreno.tp2.builder;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;
import nicolasmoreno.tp1.builder.Builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryBuilder implements Builder<Query>, Query {

    private List<Column> columnList;
    private Table table;
    private CompoundExpression compoundExpression;
    private Column orderBy;
    private Column groupBy;

    public QueryBuilder() {
        this.columnList = new ArrayList<>();
    }

    public QueryBuilder select(Column... columns) {
        this.columnList.addAll(Arrays.stream(columns).collect(Collectors.toList()));
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

    public QueryBuilder orderBy(Column column) {
        this.orderBy = column;
        return this;
    }

    public QueryBuilder groupBy(Column column) {
        this.groupBy = column;
        return this;
    }

    @Override
    public Query build() {
        if (this.columnList.size() == 0 ||
                table == null ||
                this.compoundExpression == null) throw new RuntimeException("Syntax Error");
        return this;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
