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

public class ClarinArticleProvider extends ArticleProvider {

    private final String CLARIN_URL = "https://www.clarin.com";

    public ClarinArticleProvider(Duration interval) {
        super(interval);
    }

    @Override
    public Iterable<Resource> resources() {
        final List<Resource> resourceList = new ArrayList<>();
        try {
            final Document clarinDoc = Jsoup.connect(CLARIN_URL).get();
            final Elements articles = clarinDoc.getElementsByTag("article");
            articles.forEach( article -> {
                final Element element = article.selectFirst("a h1, a h2, a h3");
                final String label = element.text();
                final String href = article.selectFirst("a[href]").attr("href");
                final String link = href.contains(CLARIN_URL) ? href : CLARIN_URL.concat(href);
                final Resource obtainedArticle = new Article(link, label, getArticleContent(link, "div.body-nota p"));
                resourceList.add(obtainedArticle);
            });
            return resourceList;
        } catch (IOException e) {
            e.printStackTrace();
            return resourceList;
        }
    }

    /*private String getArticleContent(String link) {
        try {
            final Document document = Jsoup.connect(link).get();
            final Elements elements = document.select("div.body-nota p");
            StringBuilder contentBuilder = new StringBuilder();
            for (Element paragraph: elements) {
                contentBuilder.append(paragraph.text()).append(" \n");
            }
            return contentBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }*/
}
