package nicolasmoreno.tp2.clause;

import daoo.query.Clause;

public class SelectClause implements Clause<String> {
    private final String template = "SELECT ";

    public String getTemplate() {
        return template;
    }
}
