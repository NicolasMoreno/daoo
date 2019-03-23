package tp1.nicolasmoreno.model;

public class Condition {
    private String property;
    private String comparative;
    private String value;

    public Condition(String property, String comparative, String value) {
        this.property = property;
        this.comparative = comparative;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getComparative() {
        return comparative;
    }

    public void setComparative(String comparative) {
        this.comparative = comparative;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.property + " " + this.comparative + " " + this.value;
    }

    public String toString(int index) {
        if (index == 0) {
            return "WHERE " + this.toString();
        } else {
            return " AND " + this.toString();
        }
    }
}
