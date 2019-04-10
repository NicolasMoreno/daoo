package nicolasmoreno.tp2.clause;

import daoo.query.Clause;
import daoo.query.Column;

import java.util.List;

public class GroupByClause implements Clause<String> {

    private final String template = " GROUP BY ";
    private List<Column> groupBy;

    public GroupByClause(List<Column> groupBy) {
        this.groupBy = groupBy;
    }

    public List<Column> getGroupBy() {
        return groupBy;
    }

    public String getTemplate() {
        return template;
    }
}
