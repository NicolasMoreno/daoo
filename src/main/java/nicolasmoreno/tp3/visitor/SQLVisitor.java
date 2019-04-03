package nicolasmoreno.tp3.visitor;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import nicolasmoreno.tp2.clause.FromClause;
import nicolasmoreno.tp2.clause.SelectClause;
import nicolasmoreno.tp2.clause.WhereClause;
import nicolasmoreno.tp2.column.IntColumn;
import nicolasmoreno.tp2.impl.QueryImpl;
import org.jetbrains.annotations.NotNull;

public class SQLVisitor implements Visitor {

    private String sqlQuery;

    public SQLVisitor() {
        this.sqlQuery = "";
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    @Override
    public void visit(@NotNull Query query) {
        final QueryImpl queryImpl = (QueryImpl) query;
        queryImpl.getClause().accept(this);
        int iterationIndex = 0;
        for (Column col: queryImpl.getColumnList()) {
            col.accept(this);
            if (iterationIndex < queryImpl.getColumnList().size() - 1) {
                this.sqlQuery += ", ";
            }
            iterationIndex++;
        }
        queryImpl.getFromClause().accept(this);
        queryImpl.getTable().accept(this);
        queryImpl.getWhereClause().accept(this);
        queryImpl.getCompoundExpression().accept(this);
        System.out.println(query);
    }

    @Override
    public void visit(@NotNull Column<?> column) {
        this.sqlQuery += column.getName();
    }

    @Override
    public void visit(@NotNull Table table) {
        this.sqlQuery += table.getName();
        System.out.println(table);
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
//        this.iterativeVisit(expression);
        for (int i = 0; i < expression.getOperator().getArity(); i++) {
                this.sqlQuery += expression.getOperator().getTemplate()[i];
                (expression.getOperands()[i]).accept(this);
            }
        /*switch ((DefaultOperator)expression.getOperator()) {
            case AND: {
                (expression.getOperands()[0]).accept(this);
                this.sqlQuery += expression.getOperator().getTemplate()[1];
                (expression.getOperands()[1]).accept(this);
                break;
            }
            case EQ: {
                (expression.getOperands()[0]).accept(this);
                this.sqlQuery += expression.getOperator().getTemplate()[1];
                (expression.getOperands()[1]).accept(this);
                break;
            }
            case BETWEEN: {
                (expression.getOperands()[0]).accept(this);
                this.sqlQuery += expression.getOperator().getTemplate()[1];
                (expression.getOperands()[1]).accept(this);
                this.sqlQuery += expression.getOperator().getTemplate()[2];
                (expression.getOperands()[2]).accept(this);
                break;
            }
        }*/
    }

    @Override
    public void visit(@NotNull Clause<?> clause) {
        if (clause instanceof SelectClause) {
            this.sqlQuery += ((SelectClause) clause).getTemplate();
        } else if (clause instanceof FromClause) {
            this.sqlQuery += ((FromClause) clause).getTemplate();
        } else if (clause instanceof WhereClause) {
            this.sqlQuery += ((WhereClause) clause).getTemplate();
        }
    }
}
