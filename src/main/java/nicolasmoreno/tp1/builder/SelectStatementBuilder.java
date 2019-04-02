package nicolasmoreno.tp1.builder;

import nicolasmoreno.tp1.model.Condition;
import nicolasmoreno.tp1.model.Location;
import nicolasmoreno.tp1.exception.BadSyntaxException;
import nicolasmoreno.tp1.model.Column;
import nicolasmoreno.tp1.model.SelectStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectStatementBuilder implements Builder<SelectStatement>{

    private boolean isDistinct;
    private boolean isAllColumnsStatement;
    private List<ColumnBuilder> columnsBuilder;
    private LocationBuilder locationBuilder;
    private List<ConditionBuilder> conditionsBuilder;

    public SelectStatementBuilder() {
        this.isDistinct = false;
        this.columnsBuilder = new ArrayList<>();
        this.conditionsBuilder = new ArrayList<>();
    }

    @Override
    public SelectStatement build() throws BadSyntaxException {
        if (this.columnsBuilder.size() == 0) throw new BadSyntaxException("Illegal syntax statement");
        final List<Condition> conditionList = this.conditionsBuilder.stream().map(conditionBuilder -> {
            try {
                return conditionBuilder.build();
            } catch (BadSyntaxException e ) {
                System.out.println("Hello");
            }
            return null;
        }).collect(Collectors.toList());
        final List<Column> columnList = this.columnsBuilder.stream().map(ColumnBuilder::build).collect(Collectors.toList());
        final Location location = this.locationBuilder.build();
        return new SelectStatement(
                columnList,
                location,
                conditionList,
                this.isDistinct
                );
    }

    public SelectStatementBuilder column(ColumnBuilder columnsBuilder) {
        this.columnsBuilder.add(columnsBuilder);
        return this;
    }

    public SelectStatementBuilder from(LocationBuilder locationBuilder) {
        this.locationBuilder = locationBuilder;
        return this;
    }

    public SelectStatementBuilder where(ConditionBuilder conditionBuilder) {
        this.conditionsBuilder.add(conditionBuilder);
        return this;
    }

    public SelectStatementBuilder distinct() {
        this.isDistinct = true;
        return this;
    }
}
