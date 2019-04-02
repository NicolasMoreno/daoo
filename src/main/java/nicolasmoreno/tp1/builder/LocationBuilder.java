package nicolasmoreno.tp1.builder;

import nicolasmoreno.tp1.model.Location;
import nicolasmoreno.tp1.exception.BadSyntaxException;

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
