package nicolasmoreno.tp2.impl;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class QueryImpl implements Query {

    private List<Clause> clauses;

    public QueryImpl(Clause... clauses) {
        this.clauses = Arrays.stream(clauses).collect(Collectors.toList());
    }

    public Iterable<Clause> getClauses() {
        return this.clauses;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
