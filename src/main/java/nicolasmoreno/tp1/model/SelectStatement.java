package nicolasmoreno.tp1.model;

import java.util.List;

public class SelectStatement {

    private final String initialStatement = "SELECT ";
    private List<Column> columns;
    private Location location;
    private List<Condition> conditions;
    private boolean isDistinct;

    public SelectStatement(List<Column> columns, Location location, List<Condition> conditions, boolean isDistinct) {
        this.columns = columns;
        this.location = location;
        this.conditions = conditions;
        this.isDistinct = isDistinct;
    }

    @Override
    public String toString() {
        String statement = this.initialStatement;
        if (isDistinct) statement += "DISTINCT ";
        for(int i = 0; i < this.columns.size(); i++) {
            if (i == this.columns.size() - 1) {
                statement += this.columns.get(i).getColumnName() + " ";
            } else {
                statement += this.columns.get(i).getColumnName() + ", ";
            }
        }
        statement += this.location.toString();
        for (int j = 0; j < this.conditions.size(); j++) {
            statement += this.conditions.get(j).toString(j);
        }
        return statement;
    }
}
