package nicolasmoreno.tp2.factory;

import nicolasmoreno.tp2.builder.QueryBuilder;
import nicolasmoreno.tp2.model.impl.TableImpl;
import nicolasmoreno.tp2.model.column.DoubleColumn;
import nicolasmoreno.tp2.model.column.IntColumn;
import nicolasmoreno.tp2.model.column.StrColumn;

public class BuilderFactory {

    public static TableImpl table(String name) {
        return new TableImpl(name);
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

    public static DoubleColumn doubleCol(String name) {
        return new DoubleColumn(name);
    }
}
