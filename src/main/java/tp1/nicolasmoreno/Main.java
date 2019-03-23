package tp1.nicolasmoreno;

import tp1.nicolasmoreno.exception.BadSyntaxException;
import tp1.nicolasmoreno.model.SelectStatement;

import static tp1.nicolasmoreno.factory.BuilderFactory.*;

public class Main {

    public static void main(String[] args) {
        try {
            final SelectStatement selectStatement = select()
                        .column(col("userName")).column(col("id")).column(col("surname"))
                        .from(table("Users"))
                        .where(condition("id").eq().value("123")).where(condition("userName").eq().value("Pepe"))
                    .build();
            System.out.println("Final query = "+ selectStatement.toString());
        } catch (BadSyntaxException bse) {
            System.out.println("Bad Syntax Error");
        }

    }
}
