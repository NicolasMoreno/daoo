package nicolasmoreno.tp2.model.impl;

import daoo.query.Query;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public class QueryImpl implements Query {

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
