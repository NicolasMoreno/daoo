package nicolasmoreno.tp5;


import io.reactivex.Observable;
import io.reactivex.Observer;
import nicolasmoreno.tp5.ResourceStream;
import nicolasmoreno.tp5.change.ResourceChange;
import nicolasmoreno.tp5.provider.ArticleProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class NewsStream extends Observable<ResourceChange> {

    private Set<ResourceStream> subscribedProviders;

    public NewsStream(@NotNull Set<ResourceStream> providers) {
        subscribedProviders = providers;
    }

    @Override
    protected void subscribeActual(Observer<? super ResourceChange> observer) {
        Observable.merge(subscribedProviders).subscribe(observer);
    }
}
