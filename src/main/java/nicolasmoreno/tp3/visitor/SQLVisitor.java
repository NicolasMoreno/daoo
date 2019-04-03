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

    private String sqlQuery;
    private boolean printingOrderBy;

    public SQLVisitor() {
        this.sqlQuery = "";
        this.printingOrderBy = false;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    @Override
    public void visit(@NotNull Query query) {
        final QueryImpl queryImpl = (QueryImpl) query;
        queryImpl.getClause().accept(this);
        this.iterativeVisit(queryImpl.getColumnList(), 0);
        queryImpl.getFromClause().accept(this);
        queryImpl.getTable().accept(this);
        queryImpl.getWhereClause().accept(this);
        queryImpl.getCompoundExpression().accept(this);
        queryImpl.getOrderByClause().accept(this);
        this.printingOrderBy = true;
        int index = 0;
        for (OrderByExpression exp: queryImpl.getOrderBy()) {
            exp.accept(this);
            if (index < queryImpl.getOrderBy().size() - 1) {
                this.sqlQuery += ", ";
            }
            index++;
        }
        queryImpl.getGroupByClause().accept(this);
        this.iterativeVisit(queryImpl.getGroupBy(), 0);
    }

    private void iterativeVisit(List<Column> columnList, int i) {
        for (Visitable col: columnList) {
            col.accept(this);
            if (i < columnList.size() - 1) {
                this.sqlQuery += ", ";
            }
            i++;
        }
    }

    @Override
    public void visit(@NotNull Column<?> column) {
        this.sqlQuery += column.getName();
    }

    @Override
    public void visit(@NotNull Table table) {
        this.sqlQuery += table.getName();
    }

    @Override
    public void visit(@NotNull Constant<?> constant) {
        final Object value = constant.getValue();
        if (value instanceof String) {
            this.sqlQuery += "'" + constant.getValue().toString() + "'";
        } else if (value instanceof Number) {
            this.sqlQuery += constant.getValue().toString();
        }
    }

    @Override
    public void visit(@NotNull CompoundExpression<?> expression) {
        for (int i = 0; i < expression.getOperator().getArity(); i++) {
                this.sqlQuery += expression.getOperator().getTemplate()[i];
                (expression.getOperands()[i]).accept(this);
                if (i+1 == expression.getOperands().length) {
                    this.sqlQuery += expression.getOperator().getTemplate()[i+1];
                }
                /*if (printingOrderBy) {
                    this.sqlQuery += " " + expression.getOperator().getTemplate()[i+1];
                }*/
            }
    }

    @Override
    public void visit(@NotNull Clause<?> clause) {
        if (clause instanceof SelectClause) {
            this.sqlQuery += ((SelectClause) clause).getTemplate();
        } else if (clause instanceof FromClause) {
            this.sqlQuery += ((FromClause) clause).getTemplate();
        } else if (clause instanceof WhereClause) {
            this.sqlQuery += ((WhereClause) clause).getTemplate();
        } else if (clause instanceof GroupByClause) {
            this.sqlQuery += ((GroupByClause) clause).getTemplate();
        } else if (clause instanceof OrderByClause) {
            this.sqlQuery += ((OrderByClause) clause).getTemplate();
        }
    }
}
