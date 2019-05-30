package nicolasmoreno.tp5.resourceProvider;

import nicolasmoreno.tp5.resource.Article;
import nicolasmoreno.tp5.resource.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Duration;

public class ClarinArticleProvider extends ArticleProvider {

    private final String CLARIN_URL = "https://www.clarin.com";

    public ClarinArticleProvider(Duration interval) {
        super(interval);
    }

    @Override
    public void readArticle() {
        try {
            final Document clarinDoc = Jsoup.connect(CLARIN_URL).get();
            final Elements articles = clarinDoc.getElementsByTag("article");
            articles.forEach( article -> {
                final Element element = article.selectFirst("a h1, a h2, a h3");
                final String label = element.text();
                final String link = CLARIN_URL.concat(article.selectFirst("a[href]").attr("href"));
                final Resource resource = new Article(link, label);
                super.addArticle(resource);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
