package tp2.nicolasmoreno.model;

import daoo.query.Column;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public class IntColumn implements Column<Integer> {

    private String name;

    public IntColumn(String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {

    }
}
