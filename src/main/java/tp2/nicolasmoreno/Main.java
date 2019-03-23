package tp2.nicolasmoreno;

import daoo.query.Query;
import daoo.query.Table;
import tp2.nicolasmoreno.model.IntColumn;
import tp2.nicolasmoreno.model.StrColumn;

import static tp2.nicolasmoreno.factory.BuilderFactory.*;

public class Main {
    public static void main(String[] args) {
        // BuilderFactory.table devuelve un TableBuilder que implementa Table
        final Table t = table("student");
        // BuilderFactory.string que devuelve un String, col recibe el String y buildea un StrColumn que implementa un Column<string>
        final StrColumn lastName = t.col(string("lastName"));
        final StrColumn fistName = t.col(string("firstName"));
        final IntColumn age = t.col(integer("age"));

        /**
         * BuilderFactory.query devuelve un QueryBuilder
         * .select recibe una lista de <V ext comparable , T extends column>T ?
         *
          */
        final Query q = query()
                .select(age, fistName)
                .from(t)
                .where(
                        lastName.startsWith("Lopez")
                        .and(age.between(18, 21))
                        .and(age.lt(fistName.length()))
                )
                .orderBy(age)
                .groupBy(lastName)
                .build();
    }
}
