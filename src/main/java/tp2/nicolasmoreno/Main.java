package tp2.nicolasmoreno;

import daoo.query.Query;
import daoo.query.Table;
import tp2.nicolasmoreno.model.IntColumn;
import tp2.nicolasmoreno.model.StrColumn;

import static tp2.nicolasmoreno.factory.BuilderFactory.*;

public class Main {
    public static void main(String[] args) {
        final Table t = table("student");
        final StrColumn lastName = t.col(string("lastName"));
        final StrColumn fistName = t.col(string("firstName"));
        final IntColumn age = t.col(integer("age"));

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
