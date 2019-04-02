package nicolasmoreno.tp1.builder;

import nicolasmoreno.tp1.model.Condition;
import nicolasmoreno.tp1.exception.BadSyntaxException;

public class ConditionBuilder implements Builder<Condition> {


    private String variableName;
    private String comparative;
    private String value;

    public ConditionBuilder() {
        this.comparative = "";
    }

    @Override
    public Condition build() throws BadSyntaxException {
        if (this.comparative.length() == 0 || this.comparative.length() > 2 || this.variableName == null || this.value == null) throw new BadSyntaxException("Comparation Error");
        return new Condition(variableName, comparative, value);
    }

    public ConditionBuilder columnName(String variableName) {
        this.variableName = variableName;
        return this;
    }

    public ConditionBuilder value(String value) {
        this.value = value;
        return this;
    }

    public ConditionBuilder eq() {
        this.comparative += "=";
        return this;
    }

    public ConditionBuilder greater() {
        this.comparative += ">";
        return this;
    }

    public ConditionBuilder lower() {
        this.comparative += "<";
        return this;
    }

    public ConditionBuilder notEq() {
        this.comparative += "<>";
        return this;
    }
}
