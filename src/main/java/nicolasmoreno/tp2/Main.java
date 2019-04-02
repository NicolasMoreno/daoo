package nicolasmoreno.tp2;

import daoo.query.Query;
import daoo.query.Table;
import nicolasmoreno.tp2.model.column.DoubleColumn;
import nicolasmoreno.tp2.model.column.IntColumn;
import nicolasmoreno.tp2.model.column.StrColumn;

import static nicolasmoreno.tp2.factory.BuilderFactory.*;

public class Main {
    public static void main(String[] args) {
        final Table t = table("student");
        final StrColumn lastName = t.col(string("lastName"));
        final StrColumn firstName = t.col(string("firstName"));
        final IntColumn age = t.col(integer("age"));
        final DoubleColumn calificationAvg = t.col(doubleCol("average"));

        final Query q1 = query()
                .select(age, firstName)
                .from(t)
                .where(
                        lastName.startsWith("Lopez")
                        .and(age.between(18, 21))
                        .and(age.lt(firstName.length())) // Mal
                )
                .orderBy(age)
                .groupBy(lastName)
                .build();

        final Query q = query()
                .select(firstName)
                .from(t)
                .where(
                        lastName.eq("Pepe")
                        .or(age.ge(30))
                ).build();

        System.out.println(
                "Hello"
        );


    }
}
