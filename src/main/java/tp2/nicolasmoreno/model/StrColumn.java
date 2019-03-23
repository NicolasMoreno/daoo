package tp2.nicolasmoreno.model;

import daoo.query.Column;
import daoo.query.Criteria;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public class StrColumn implements Column<String> {

    private String name;

    public StrColumn(String columnName) {this.name = columnName;}

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {

    }

    public Criteria startsWith(String comparate) {
        return new Criteria(null, )
    }


}
