package tp1.nicolasmoreno.builder;

import tp1.nicolasmoreno.exception.BadSyntaxException;

public interface Builder<T> {

    T build() throws BadSyntaxException;
}
