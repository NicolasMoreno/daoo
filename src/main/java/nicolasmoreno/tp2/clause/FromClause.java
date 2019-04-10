package nicolasmoreno.tp2.clause;

import daoo.query.Clause;
import daoo.query.Table;

public class FromClause implements Clause<String> {
    private Table table;

    public FromClause(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

}
