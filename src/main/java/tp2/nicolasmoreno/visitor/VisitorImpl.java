package tp2.nicolasmoreno.visitor;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public class VisitorImpl implements Visitor {




    @Override
    public void visit(@NotNull Query query) {

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

    }
}
