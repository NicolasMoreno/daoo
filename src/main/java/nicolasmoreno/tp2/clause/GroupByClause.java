package nicolasmoreno.tp2.clause;

import daoo.query.Clause;

public class GroupByClause implements Clause<String> {

    private final String template = " GROUP BY ";

    public String getTemplate() {
        return template;
    }
}
