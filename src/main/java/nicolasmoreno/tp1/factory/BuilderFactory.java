package nicolasmoreno.tp1.factory;

import nicolasmoreno.tp1.builder.ColumnBuilder;
import nicolasmoreno.tp1.builder.ConditionBuilder;
import nicolasmoreno.tp1.builder.LocationBuilder;
import nicolasmoreno.tp1.builder.SelectStatementBuilder;

public class BuilderFactory {

    public static SelectStatementBuilder select(){
        return new SelectStatementBuilder();
    }

    public static ColumnBuilder col(String columnName) {
        return new ColumnBuilder().name(columnName);
    }

    public static LocationBuilder table(String tableName) {
        return new LocationBuilder().table(tableName);
    }

    public static ConditionBuilder condition(String columnName) {
        return new ConditionBuilder().columnName(columnName);
    }

    /*public static CreateTableBuilder createTable() {

    }

    public static DropTableBuilder dropTable() {

    }*/
}
