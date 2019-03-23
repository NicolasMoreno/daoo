package tp1.nicolasmoreno.model;

public class Column {
    private String columnName;
    private String type; // TODO type?

    public Column(String columnName) {
        this.columnName = columnName;
//        this.type = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
