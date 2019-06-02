package nicolasmoreno.tp5.resourceProvider;

import nicolasmoreno.tp5.resource.Article;
import nicolasmoreno.tp5.resource.Resource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class ArticleProvider implements ResourceProvider {

    private List<Resource> resourceList;
    private Duration interval;

    public ArticleProvider(Duration interval) {
        this.interval = interval;
        this.resourceList = new ArrayList<>();
    }

    public abstract List<Article> getArticles();

    @Override
    public Iterable<Resource> resources() {
        return resourceList;
    }

    @Override
    public Duration interval() {
        return interval;
    }

    public void addArticle(Resource resource) {
        this.resourceList.add(resource);
    }
}
