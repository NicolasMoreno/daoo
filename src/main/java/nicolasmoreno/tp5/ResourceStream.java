package nicolasmoreno.tp5;

import io.reactivex.Observable;
import io.reactivex.Observer;
import nicolasmoreno.tp5.resource.Article;
import nicolasmoreno.tp5.resource.ArticleCabinet;
import nicolasmoreno.tp5.resourceChange.ResourceChange;
import nicolasmoreno.tp5.resourceProvider.ArticleProvider;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ResourceStream extends Observable<ResourceChange> {

    private ArticleCabinet cabinet;
    private ArticleProvider provider;

    public ResourceStream(ArticleProvider articleProvider) {
        this.provider = articleProvider;
        cabinet = new ArticleCabinet();
    }

    @Override
    protected void subscribeActual(Observer<? super ResourceChange> observer) {
        final Observable<ResourceChange> objectObservable = Observable
                .create(emitter -> interval(0, provider.interval().getSeconds(), TimeUnit.SECONDS).subscribe(passedTime -> {
            if (emitter.isDisposed()) {
                getArticles().forEach(observer::onNext);
                observer.onComplete();
            }
        }));
        objectObservable.subscribe(observer);
    }

    private List<ResourceChange> getArticles() {
        final List<Article> articles = provider.getArticles();
        return cabinet.compareArticles(articles);
    }
}
