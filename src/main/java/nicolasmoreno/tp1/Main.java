package nicolasmoreno.tp1;

import nicolasmoreno.tp1.factory.BuilderFactory;
import nicolasmoreno.tp1.exception.BadSyntaxException;
import nicolasmoreno.tp1.model.SelectStatement;

public class Main {

    public static void main(String[] args) {
        try {
            final SelectStatement selectStatement = BuilderFactory.select()
                        .column(BuilderFactory.col("userName")).column(BuilderFactory.col("id")).column(BuilderFactory.col("surname"))
                        .from(BuilderFactory.table("Users"))
                        .where(BuilderFactory.condition("id").eq().value("123")).where(BuilderFactory.condition("userName").eq().value("Pepe"))
                    .build();
            System.out.println("Final query = "+ selectStatement.toString());
        } catch (BadSyntaxException bse) {
            System.out.println("Bad Syntax Error");
        }

    }
}
