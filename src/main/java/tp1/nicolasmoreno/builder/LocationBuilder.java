package tp1.nicolasmoreno.builder;

import tp1.nicolasmoreno.exception.BadSyntaxException;
import tp1.nicolasmoreno.model.Location;

public class LocationBuilder implements Builder<Location> {

    private String tableName;

    public LocationBuilder() {
        this.tableName = "";
    }

    @Override
    public Location build() throws BadSyntaxException{
        if (this.tableName.equals("")) throw new BadSyntaxException("Hello");
        return new Location(tableName);
    }

    public LocationBuilder table(String tableName) {
        this.tableName = tableName;
        return this;
    }

}
