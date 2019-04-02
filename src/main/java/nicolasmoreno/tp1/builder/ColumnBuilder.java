package nicolasmoreno.tp1.builder;

import nicolasmoreno.tp1.model.Column;

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
