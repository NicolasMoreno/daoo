package nicolasmoreno.tp5.provider;

import nicolasmoreno.tp5.resource.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class ArticleProvider implements ResourceProvider {

    private Duration interval;

    public ArticleProvider(Duration interval) {
        this.interval = interval;
    }

    @Override
    public abstract Iterable<Resource> resources();

    @Override
    public Duration interval() {
        return interval;
    }

    String getArticleContent(String link, String cssSelector) {
        try {
            final Document document = Jsoup.connect(link).get();
            final Elements elements = document.select(cssSelector);
            StringBuilder contentBuilder = new StringBuilder();
            for (Element paragraph: elements) {
                contentBuilder.append(paragraph.text()).append(" \n");
            }
            return contentBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
