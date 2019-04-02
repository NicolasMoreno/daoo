package nicolasmoreno.tp1.builder;

import nicolasmoreno.tp1.exception.BadSyntaxException;

public interface Builder<T> {

    T build() throws BadSyntaxException;
}
