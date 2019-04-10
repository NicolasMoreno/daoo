package nicolasmoreno.tp2.clause;

import daoo.query.Clause;
import daoo.query.CompoundExpression;
import daoo.query.Table;

public class WhereClause implements Clause<String> {

    private CompoundExpression compoundExpression;

    public WhereClause(CompoundExpression compoundExpression) {
        this.compoundExpression = compoundExpression;
    }

    public CompoundExpression getCompoundExpression() {
        return compoundExpression;
    }

}
