package tp2.nicolasmoreno.builder;

import daoo.query.*;
import tp1.nicolasmoreno.builder.Builder;
import tp1.nicolasmoreno.exception.BadSyntaxException;
import tp2.nicolasmoreno.model.IntColumn;
import tp2.nicolasmoreno.model.StrColumn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryBuilder implements Builder<Query> {

    private Table table;
    private List<Column> columnList;


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

    public QueryBuilder where(Criteria criteria) {
        return this;
    }

    public QueryBuilder orderBy(Column column) {
        return this;
    }

    public QueryBuilder groupBy(Column column) {
        return this;
    }


    @Override
    public Query build() throws BadSyntaxException {
        return null;
    }
}
