package tp2.nicolasmoreno.builder;

import daoo.query.Column;
import daoo.query.Criteria;
import daoo.query.Expression;
import daoo.query.Table;
import tp2.nicolasmoreno.model.IntColumn;
import tp2.nicolasmoreno.model.StrColumn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryBuilder {

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

}
