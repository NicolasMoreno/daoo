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

    @Override
    public void visit(@NotNull Query query) {
        final QueryImpl queryImpl = (QueryImpl) query;
        queryImpl.getClause().accept(this);
        queryImpl.getColumnList().forEach( column -> column.accept(this));
        queryImpl.getFromClause().accept(this);
        queryImpl.getTable().accept(this);
        queryImpl.getWhereClause().accept(this);
        queryImpl.getCompoundExpression().accept(this);
        System.out.println(query);
    }

    @Override
    public void visit(@NotNull Column<?> column) {
        this.sqlQuery += column.getName() + ", ";
    }

    @Override
    public void visit(@NotNull Table table) {
        this.sqlQuery += table.getName();
        System.out.println(table);
    }

    @Override
    public void visit(@NotNull Constant<?> constant) {
        System.out.println(constant);
    }

    @Override
    public void visit(@NotNull CompoundExpression<?> expression) {
//        this.iterativeVisit(expression);
        System.out.println(expression);
    }

    /*private String iterativeVisit(@NotNull CompoundExpression<?> compoundExpression) {
        final String[] template = compoundExpression.getOperator().getTemplate();
        switch ((DefaultOperator)compoundExpression.getOperator()) {
            case AND: {
                template[2] = this.iterativeVisit((Criteria)compoundExpression.getOperands()[1]);
                template[0] = this.iterativeVisit((Criteria)compoundExpression.getOperands()[0]);
                break;
            }
            case OR: {
                break;
            }
            case NOT: {
                break;
            }
            case GE: {
                return this.processData(compoundExpression, template);
            }
            case LT: {
                return this.processData(compoundExpression, template);
            }
            case AVG: {
                template[0] += ((IntColumn)compoundExpression.getOperands()[0]).getName();
                return template[0].concat(template[1]);
            }
        }
        return "";
    }

    private String processData(CompoundExpression<?> compoundExpression, String[] template) {
        if (compoundExpression.getOperands()[1] instanceof Constant) {
            template[2] = this.iterativeVisit((Constant)compoundExpression.getOperands()[1]);
        } else {
            template[2] = this.iterativeVisit((CompoundExpression<?>)compoundExpression.getOperands()[1]);
        }
        if (compoundExpression.getOperands()[0] instanceof Column) {
            template[0] = ((Column)compoundExpression.getOperands()[0]).getName();
        } else {
            template[0] = this.iterativeVisit((CompoundExpression<?>) compoundExpression.getOperands()[0]);
        }
        return template[0].concat(template[1]).concat(template[2]);
    }

    private String iterativeVisit(@NotNull Constant constant) {
        return ((Integer)constant.getValue()).toString();
    }*/

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
