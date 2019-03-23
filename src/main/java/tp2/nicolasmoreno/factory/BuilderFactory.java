package tp2.nicolasmoreno.factory;

import tp1.nicolasmoreno.builder.ColumnBuilder;
import tp1.nicolasmoreno.builder.ConditionBuilder;
import tp1.nicolasmoreno.builder.LocationBuilder;
import tp1.nicolasmoreno.builder.SelectStatementBuilder;
import tp2.nicolasmoreno.builder.QueryBuilder;
import tp2.nicolasmoreno.builder.TableBuilder;
import tp2.nicolasmoreno.model.IntColumn;
import tp2.nicolasmoreno.model.StrColumn;

public class BuilderFactory {

    public static TableBuilder table(String name) {
        return new TableBuilder(name);
    }

    public static StrColumn string(String name) {
        return new StrColumn(name);
    }

    public static IntColumn integer(String name) {
        return new IntColumn(name);
    }

    public static QueryBuilder query() {
        return new QueryBuilder();
    }
}
