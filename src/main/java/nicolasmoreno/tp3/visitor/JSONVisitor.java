package nicolasmoreno.tp3.visitor;

import daoo.query.*;
import daoo.query.visitor.Visitable;
import daoo.query.visitor.Visitor;
import nicolasmoreno.tp2.clause.*;
import nicolasmoreno.tp2.impl.QueryImpl;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;

public class JSONVisitor implements Visitor {

    private String currentState;
    private StringBuilder stringBuilder;
    private JSONObject jsonObject;

    public JSONVisitor() {
        this.jsonObject = new JSONObject();
        this.stringBuilder = new StringBuilder();
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    @Override
    public void visit(@NotNull Query query) {
        final QueryImpl queryImpl = (QueryImpl) query;
        queryImpl.getClauses().forEach( clause -> clause.accept(this));
    }

    @Override
    public void visit(@NotNull Column<?> column) {
        if (currentState.equals("WHERE")) {
            stringBuilder.append(column.getName());
        } else {
            jsonObject.append(currentState, column.getName());
        }
    }

    @Override
    public void visit(@NotNull Table table) {
        jsonObject.append(currentState, table.getName());
    }

    @Override
    public void visit(@NotNull Constant<?> constant) {
        if (currentState.equals("WHERE")) {
            stringBuilder.append(constant.getValue());
        } else {
            jsonObject.append(currentState, constant.getValue());
        }
    }

    @Override
    public void visit(@NotNull CompoundExpression<?> expression) {
        for (int i = 0; i < expression.getOperator().getArity(); i++) {
            stringBuilder.append(expression.getOperator().getTemplate()[i]);
            (expression.getOperands()[i]).accept(this);
            if (i+1 == expression.getOperands().length) {
                stringBuilder.append(expression.getOperator().getTemplate()[i+1]);
            }
        }
    }

    @Override
    public void visit(@NotNull Clause<?> clause) {
        if (clause instanceof SelectClause) {
            final SelectClause select = (SelectClause) clause;
            currentState = "SELECT";
            iterativeVisit(select.getColumnList());

        } else if (clause instanceof FromClause) {
            final FromClause fromClause = (FromClause) clause;
            currentState = "FROM";
            fromClause.getTable().accept(this);

        } else if (clause instanceof WhereClause) {
            final WhereClause whereClause = (WhereClause) clause;
            currentState = "WHERE";
            whereClause.getCompoundExpression().accept(this);
            jsonObject.append(currentState, stringBuilder);
            stringBuilder.delete(0, stringBuilder.length());
        } else if (clause instanceof GroupByClause) {
            final GroupByClause groupByClause = (GroupByClause) clause;
            currentState = "GROUP BY";
            iterativeVisit(groupByClause.getGroupBy());

        } else if (clause instanceof OrderByClause) {
            final OrderByClause orderByClause = (OrderByClause) clause;
            currentState = "ORDER BY";
            iterativeVisit(orderByClause.getOrderBy());
            jsonObject.append(currentState, stringBuilder);
            stringBuilder.delete(0, stringBuilder.length());
        }
    }

    private void iterativeVisit(List<? extends Visitable> columnList) {
        int i = 0;
        for (Visitable col: columnList) {
            col.accept(this);
//            if (i < columnList.size() - 1) {
//                jsonObject.append("SELECT", "");
//            }
            i++;
        }
    }
}
