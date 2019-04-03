package nicolasmoreno.tp2.clause;

import daoo.query.Clause;

public class OrderByClause implements Clause<String> {

    private final String template = " ORDER BY ";

    public String getTemplate() {
        return template;
    }
}
