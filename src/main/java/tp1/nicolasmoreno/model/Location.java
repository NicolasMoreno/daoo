package tp1.nicolasmoreno.model;

public class Location {
    private String tableName;

    public Location(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "FROM " + this.tableName + " ";
    }
}
