package nicolasmoreno.tp5.resourceProvider;

import nicolasmoreno.tp5.resource.Article;
import nicolasmoreno.tp5.resource.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LaNacionArticleProvider extends ArticleProvider {

    private final String LA_NACION_URL = "https://www.lanacion.com.ar";

    public LaNacionArticleProvider(Duration interval) {
        super(interval);
    }

    @Override
    public Iterable<Resource> resources() {
        final List<Resource> resourceList = new ArrayList<>();
        try {
            Document lanacionDoc = Jsoup.connect(LA_NACION_URL).get();
            final Elements articles = lanacionDoc.getElementsByTag("article");
            articles.forEach(element -> {
                final Element firstFilter = element.selectFirst("h1, h2, h3");
                final String link = LA_NACION_URL.concat(firstFilter.getElementsByAttribute("href").attr("href"));
                final String label = firstFilter.selectFirst("a").text();
                final Resource article = new Article(link, label, getArticleContent(link, "p"));
                resourceList.add(article);
            });
            return resourceList;
        } catch (IOException e) {
            e.printStackTrace();
            return resourceList;
        }
    }

   /* private String getArticleContent(String link) {
        try {
            final Document document = Jsoup.connect(link).get();
            final Elements elements = document.getElementsByTag("p");
            StringBuilder content = new StringBuilder();
            for(Element paragraph: elements) {
                content.append(paragraph.text()).append(" \n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }*/
}
