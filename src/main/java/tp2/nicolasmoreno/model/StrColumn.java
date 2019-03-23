package tp2.nicolasmoreno.model;

import daoo.query.Column;
import daoo.query.Criteria;
import org.jetbrains.annotations.NotNull;

public class StrColumn implements Column<String> {

    private String name;

    public StrColumn(String columnName) {this.name = columnName;}

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    public Criteria startsWith(String comparate) {
        return this.eq(comparate);
    }

    public Integer length() {
        return this.name.length();
    }

}
