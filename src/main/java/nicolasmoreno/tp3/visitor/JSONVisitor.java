package nicolasmoreno.tp3.visitor;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class JSONVisitor implements Visitor {

    private JSONObject jsonObject;

    public JSONVisitor() {
        this.jsonObject = new JSONObject();
    }

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
