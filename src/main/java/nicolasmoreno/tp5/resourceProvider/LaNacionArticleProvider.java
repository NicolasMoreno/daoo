package nicolasmoreno.tp5.resourceProvider;

import nicolasmoreno.tp5.resource.Article;
import nicolasmoreno.tp5.resource.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Duration;

public class LaNacionArticleProvider extends ArticleProvider {

    private final String LA_NACION_URL = "https://www.lanacion.com.ar";

    public LaNacionArticleProvider(Duration interval) {
        super(interval);
    }

    @Override
    public void readArticle() {
        try {
            Document lanacionDoc = Jsoup.connect(LA_NACION_URL).get();
            final Elements articles = lanacionDoc.getElementsByTag("article");
            articles.forEach(element -> {
                final Element firstFilter = element.selectFirst("h1, h2");
                final String link = LA_NACION_URL.concat(firstFilter.getElementsByAttribute("href").attr("href"));
                final String label = firstFilter.selectFirst("a").text();
                final Resource resource = new Article(link, label);
                super.addArticle(resource);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
