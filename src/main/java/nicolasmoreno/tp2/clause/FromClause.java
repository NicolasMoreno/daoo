package nicolasmoreno.tp2.clause;

import daoo.query.Clause;

public class FromClause implements Clause<String> {
    private final String template = "FROM ";

    public String getTemplate() {
        return template;
    }
}
