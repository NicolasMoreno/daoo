package nicolasmoreno.tp3.visitor;

import daoo.query.Query;
import daoo.query.Table;
import nicolasmoreno.tp2.column.DoubleColumn;
import nicolasmoreno.tp2.column.IntColumn;
import nicolasmoreno.tp2.column.StrColumn;
import org.junit.Test;

import static nicolasmoreno.tp2.factory.BuilderFactory.*;
import static nicolasmoreno.tp2.factory.BuilderFactory.doubleCol;
import static org.junit.Assert.*;

public class VisitorTest {

    private final SQLVisitor sqlVisitor = new SQLVisitor();
    private final JSONVisitor jsonVisitor = new JSONVisitor();

    @Test
    public void complexSqlTest() {

        final String expectedSql = "SELECT age, firstName " +
                "FROM student " +
                "WHERE (avg(age) >= 21 or age between 18 and 25) and lastName = 'Pepe' " +
                "ORDER BY avg(marksAvg) DESC, firstName ASC " +
                "GROUP BY lastName, firstName";

        final String expectedJSON = "{" +
                "\"FROM\":[\"student\"]," +
                "\"GROUP BY\":[\"lastName\",\"firstName\"]," +
                "\"WHERE\":[\"(avg(age) >= 21 or age between 18 and 25) and lastName = Pepe\"]," +
                "\"SELECT\":[\"age\",\"firstName\"]," +
                "\"ORDER BY\":[\"marksAvg\",\"firstName\",\"avg() DESC ASC\"]" +
                "}";

        final Table t = table("student");
        final StrColumn lastName = t.col(string("lastName"));
        final StrColumn firstName = t.col(string("firstName"));
        final IntColumn age = t.col(integer("age"));
        final DoubleColumn marksAvg = t.col(doubleCol("marksAvg"));

        final Query queryTest = query()
                .select(age, firstName)
                .from(t)
                .where(
                        age.avg().ge(21)
                                .or(age.between(18,25)).and(lastName.eq("Pepe"))
                )
                .orderBy(marksAvg.avg().desc(), firstName.asc())
                .groupBy(lastName, firstName)
                .build();

        queryTest.accept(sqlVisitor);
        queryTest.accept(jsonVisitor);

        assertEquals("Asserting expected sql", expectedSql, sqlVisitor.getSqlQuery());
        assertEquals("Asserting expected json", expectedJSON, jsonVisitor.getJsonObject().toString());
    }

    @Test
    public void simpleSqlTest() {
        final String expectedSQL = "SELECT firstName " +
                "FROM student " +
                "WHERE (lastName = 'Pepe' or age >= 30)";

        final String expectedJSON = "{" +
                "\"FROM\":[\"student\"]," +
                "\"WHERE\":[\"(lastName = Pepe or age >= 30)\"]," +
                "\"SELECT\":[\"firstName\"]}";

        final Table t = table("student");
        final StrColumn firstName = t.col(string("firstName"));
        final StrColumn lastName = t.col(string("lastName"));
        final IntColumn age = t.col(integer("age"));

        final Query simpleQuery = query()
                .select(firstName)
                .from(t)
                .where(
                        lastName.eq("Pepe")
                                .or(age.ge(30))
                ).build();
        simpleQuery.accept(sqlVisitor);
        simpleQuery.accept(jsonVisitor);

        assertEquals("Asserting simple sql", expectedSQL, sqlVisitor.getSqlQuery());
        assertEquals("Asserting simple json", expectedJSON, jsonVisitor.getJsonObject().toString());



    }

}