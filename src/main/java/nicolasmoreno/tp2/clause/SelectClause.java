package nicolasmoreno.tp2.clause;

import daoo.query.Clause;
import daoo.query.Column;

import java.util.List;

public class SelectClause implements Clause<String> {
    private List<Column> columnList;

    public SelectClause(List<Column> columnList) {
        this.columnList = columnList;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

}
