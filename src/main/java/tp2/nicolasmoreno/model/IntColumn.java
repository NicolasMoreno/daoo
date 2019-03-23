package tp2.nicolasmoreno.model;

import daoo.query.Column;
import daoo.query.Criteria;
import daoo.query.DefaultOperator;
import org.jetbrains.annotations.NotNull;

import static daoo.query.Constant.constant;

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

    public Criteria between(Integer min, Integer max) {
        return new Criteria(DefaultOperator.BETWEEN, constant(this.name),constant(min), constant(max));
    }

    public Criteria lt(Integer lowerTo) {
        return new Criteria(DefaultOperator.LT, constant(this.name), constant(lowerTo));
    }
}


