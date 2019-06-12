package nicolasmoreno.tp5;

import io.reactivex.Observable;
import io.reactivex.Observer;
import nicolasmoreno.tp5.resource.ArticleCabinet;
import nicolasmoreno.tp5.resource.Resource;
import nicolasmoreno.tp5.change.ResourceChange;
import nicolasmoreno.tp5.provider.ArticleProvider;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResourceStream extends Observable<ResourceChange> {

    private final Observable<ResourceChange> observable;
    private ArticleCabinet cabinet;
    private ArticleProvider provider;

    public ResourceStream(ArticleProvider articleProvider) {
        this.provider = articleProvider;
        cabinet = new ArticleCabinet();
        observable =
                Observable.interval(0, provider.interval().getSeconds(), TimeUnit.SECONDS)
                        .flatMapIterable(passedTime -> getArticles());
    }

    @Override
    protected void subscribeActual(Observer<? super ResourceChange> observer) {
        observable.subscribe(observer);
    }

    private List<ResourceChange> getArticles() {
        final Iterable<Resource> articles = provider.resources();
        return cabinet.detectChanges(articles);
    }
}
