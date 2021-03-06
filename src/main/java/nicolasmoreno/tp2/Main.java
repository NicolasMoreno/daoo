package nicolasmoreno.tp2;

import daoo.query.Query;
import daoo.query.Table;
import nicolasmoreno.tp2.column.DoubleColumn;
import nicolasmoreno.tp2.column.IntColumn;
import nicolasmoreno.tp2.column.StrColumn;
import nicolasmoreno.tp3.visitor.JSONVisitor;
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
                        age.avg().ge(21)
                        .or(age.between(18,25)).and(lastName.eq("Pepe"))
                )
                .orderBy(marksAvg.avg().desc(), firstName.asc())
                .groupBy(lastName, firstName)
                .build();

        final Query q = query()
                .select(firstName)
                .from(t)
                .where(
                        lastName.eq("Pepe")
                        .or(age.ge(30))
                ).build();

        final SQLVisitor sqlVisitor = new SQLVisitor();
        final JSONVisitor jsonVisitor = new JSONVisitor();
        q1.accept(sqlVisitor);
        System.out.println(sqlVisitor.getSqlQuery());
        q.accept(sqlVisitor);
        System.out.println(sqlVisitor.getSqlQuery());
        q1.accept(jsonVisitor);
        System.out.println(jsonVisitor.getJsonObject());

    }
}
