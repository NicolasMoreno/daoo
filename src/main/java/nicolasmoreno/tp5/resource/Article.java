package nicolasmoreno.tp5.resource;

public class Article implements Resource {

    private String link;
    private String label;
    private String body;

    public Article(String link, String label, String body) {
        this.link = link;
        this.label = label;
        this.body = body;
    }

    @Override
    public String link() {
        return link;
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public String body() {
        return body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Article) {
            final Article articleToCompare = (Article) obj;
            return body.equals(articleToCompare.body);
            /*return (articleId.equals(articleToCompare.articleId)
                    && body.equals(articleToCompare.body)
                    && link.equals(articleToCompare.link));*/
        } else {
            return false;
        }
    }
}
