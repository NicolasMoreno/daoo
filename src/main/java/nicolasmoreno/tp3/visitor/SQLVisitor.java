package nicolasmoreno.tp3.visitor;

import daoo.query.*;
import daoo.query.visitor.Visitable;
import daoo.query.visitor.Visitor;
import nicolasmoreno.tp2.clause.*;
import nicolasmoreno.tp2.expr.OrderByExpression;
import nicolasmoreno.tp2.impl.QueryImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SQLVisitor implements Visitor {

    private StringBuilder sqlQuery;

    public String getSqlQuery() {
        return sqlQuery.toString();
    }

    @Override
    public void visit(@NotNull Query query) {
        sqlQuery = new StringBuilder();
        final QueryImpl queryImpl = (QueryImpl) query;
        queryImpl.getClauses().forEach( clause -> clause.accept(this));
    }

    private void iterativeVisit(List<? extends Visitable> columnList) {
        int i = 0;
        for (Visitable col: columnList) {
            col.accept(this);
            if (i < columnList.size() - 1) {
                this.sqlQuery.append(", ");
            }
            i++;
        }
    }

    @Override
    public void visit(@NotNull Column<?> column) {
        sqlQuery.append(column.getName());
    }

    @Override
    public void visit(@NotNull Table table) {
        sqlQuery.append(table.getName());
    }

    @Override
    public void visit(@NotNull Constant<?> constant) {
        final Object value = constant.getValue();
        if (value instanceof String) {
            this.sqlQuery.append("'").append(constant.getValue().toString()).append("'");
        } else if (value instanceof Number) {
            this.sqlQuery.append(constant.getValue().toString());
        }
    }

    @Override
    public void visit(@NotNull CompoundExpression<?> expression) {
        for (int i = 0; i < expression.getOperator().getArity(); i++) {
                this.sqlQuery.append(expression.getOperator().getTemplate()[i]);
                (expression.getOperands()[i]).accept(this);
                if (i+1 == expression.getOperands().length) {
                    this.sqlQuery.append(expression.getOperator().getTemplate()[i+1]);
                }
        }
    }

    @Override
    public void visit(@NotNull Clause<?> clause) {
        if (clause instanceof SelectClause) {
            final SelectClause select = (SelectClause) clause;
            sqlQuery.append("SELECT ");
            iterativeVisit(select.getColumnList());
        } else if (clause instanceof FromClause) {
            final FromClause fromClause = (FromClause) clause;
            sqlQuery.append(" FROM ");
            fromClause.getTable().accept(this);
        } else if (clause instanceof WhereClause) {
            final WhereClause whereClause = (WhereClause) clause;
            sqlQuery.append(" WHERE ");
            whereClause.getCompoundExpression().accept(this);
        } else if (clause instanceof GroupByClause) {
            final GroupByClause orderBy = (GroupByClause) clause;
            sqlQuery.append(" GROUP BY ");
            iterativeVisit(orderBy.getGroupBy());
        } else if (clause instanceof OrderByClause) {
            final OrderByClause orderByClause = (OrderByClause) clause;
            sqlQuery.append(" ORDER BY ");
            iterativeVisit(orderByClause.getOrderBy());
        }
    }
}
