package nicolasmoreno.tp5.resource;

public interface Resource {
    default String id() {return this.link();}

    String link();

    String label();

    String body();
}
