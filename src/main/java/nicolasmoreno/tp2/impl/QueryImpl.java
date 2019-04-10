package nicolasmoreno.tp2.impl;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class QueryImpl implements Query {

    private List<Clause> clauses;

    public QueryImpl(List<Clause> clauses) {
        this.clauses = clauses;
    }

    public Iterable<Clause> getClauses() {
        return this.clauses;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
