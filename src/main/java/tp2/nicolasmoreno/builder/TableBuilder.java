package tp2.nicolasmoreno.builder;

import daoo.query.Column;
import daoo.query.Table;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TableBuilder implements Table {

    private String name;
    private List<Column> columnList;

    public TableBuilder(String name) {
        this.name = name;
        this.columnList = new ArrayList<>();
    }

    @Override
    public <V extends Comparable<V>, T extends Column<V>> T col(T column) {
        this.columnList.add(column);
        return column;
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }

    public List<Column> getColumnList() {
        return columnList;
    }
}
