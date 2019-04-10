package nicolasmoreno.tp3.visitor;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import nicolasmoreno.tp2.clause.*;
import nicolasmoreno.tp2.impl.QueryImpl;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class JSONVisitor implements Visitor {

    private JSONObject jsonObject;

    public JSONVisitor() {
        this.jsonObject = new JSONObject();
    }

    @Override
    public void visit(@NotNull Query query) {
        final QueryImpl queryImpl = (QueryImpl) query;
        queryImpl.getClauses().forEach( clause -> clause.accept(this));
    }

    @Override
    public void visit(@NotNull Column<?> column) {

    }

    @Override
    public void visit(@NotNull Table table) {

    }

    @Override
    public void visit(@NotNull Constant<?> constant) {

    }

    @Override
    public void visit(@NotNull CompoundExpression<?> expression) {

    }

    @Override
    public void visit(@NotNull Clause<?> clause) {
        if (clause instanceof SelectClause) {
            final SelectClause select = (SelectClause) clause;

        } else if (clause instanceof FromClause) {
            final FromClause fromClause = (FromClause) clause;

        } else if (clause instanceof WhereClause) {
            final WhereClause whereClause = (WhereClause) clause;

        } else if (clause instanceof GroupByClause) {
            final GroupByClause orderBy = (GroupByClause) clause;

        } else if (clause instanceof OrderByClause) {
            final OrderByClause orderByClause = (OrderByClause) clause;

        }
    }
}
