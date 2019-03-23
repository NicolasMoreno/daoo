package tp1.nicolasmoreno.builder;

import tp1.nicolasmoreno.model.Column;

public class ColumnBuilder implements Builder<Column>{

    private String columnName;
    //TODO próximamente column type con parámetros

    public ColumnBuilder() {
        this.columnName = "";
    }

    @Override
    public Column build() {
        return new Column(columnName);
    }

    public ColumnBuilder name(String columnName) {
        this.columnName = columnName;
        return this;
    }

    @Override
    public String toString() {
        return this.columnName;
    }
}
