package nicolasmoreno.tp5.resource;

public class Article implements Resource {

    private String link;
    private String label;

    public Article(String link, String label) {
        this.link = link;
        this.label = label;
    }

    @Override
    public String link() {
        return null;
    }

    @Override
    public String label() {
        return null;
    }
}
