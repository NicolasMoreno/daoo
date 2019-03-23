package tp1.nicolasmoreno.factory;

import tp1.nicolasmoreno.builder.ColumnBuilder;
import tp1.nicolasmoreno.builder.ConditionBuilder;
import tp1.nicolasmoreno.builder.LocationBuilder;
import tp1.nicolasmoreno.builder.SelectStatementBuilder;

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
