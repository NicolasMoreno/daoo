package nicolasmoreno.tp5.resource;

public class Article implements Resource {

    private String link;
    private String label;
    private String content;

    public Article(String link, String label) {
        this.link = link;
        this.label = label;
    }

    @Override
    public String link() {
        return link;
    }

    @Override
    public String label() {
        return label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Article) {
            final Article articleToCompare = (Article) obj;
            return content.equals(articleToCompare.content);
            /*return (articleId.equals(articleToCompare.articleId)
                    && content.equals(articleToCompare.content)
                    && link.equals(articleToCompare.link));*/
        } else {
            return false;
        }
    }
}
