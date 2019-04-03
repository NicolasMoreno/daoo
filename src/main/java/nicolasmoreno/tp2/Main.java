package nicolasmoreno.tp2;

import daoo.query.Query;
import daoo.query.Table;
import nicolasmoreno.tp2.column.DoubleColumn;
import nicolasmoreno.tp2.column.IntColumn;
import nicolasmoreno.tp2.column.StrColumn;
import nicolasmoreno.tp2.impl.QueryImpl;
import nicolasmoreno.tp3.visitor.SQLVisitor;

import static nicolasmoreno.tp2.factory.BuilderFactory.*;

public class Main {
    public static void main(String[] args) {
        final Table t = table("student");
        final StrColumn lastName = t.col(string("lastName"));
        final StrColumn firstName = t.col(string("firstName"));
        final IntColumn age = t.col(integer("age"));
        final DoubleColumn marksAvg = t.col(doubleCol("marksAvg"));

        final Query q1 = query()
                .select(age, firstName)
                .from(t)
                .where(
                        lastName.eq("Pepe")
                        .and(age.between(18, 21))
                )
                .orderBy(marksAvg.avg().desc(), firstName.asc())
                .groupBy(lastName)
                .build();

        final Query q = query()
                .select(firstName)
                .from(t)
                .where(
                        lastName.eq("Pepe")
                        .or(age.ge(30))
                ).build();

        final SQLVisitor sqlVisitor = new SQLVisitor();
        final QueryImpl query = (QueryImpl)q1;
        query.accept(sqlVisitor);

    }
}
