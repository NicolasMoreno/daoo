package nicolasmoreno.tp2.clause;

import daoo.query.Clause;

public class WhereClause implements Clause<String> {

    private final String template = " WHERE ";

    public String getTemplate() {
        return template;
    }
}
