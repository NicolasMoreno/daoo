package nicolasmoreno.tp5;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;
import nicolasmoreno.tp5.resourceChange.ResourceChange;
import nicolasmoreno.tp5.resourceProvider.ArticleProvider;
import nicolasmoreno.tp5.resourceProvider.ClarinArticleProvider;
import nicolasmoreno.tp5.resourceProvider.LaNacionArticleProvider;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ArticleProvider laNacionArticleProvider = new LaNacionArticleProvider(Duration.ofMinutes(1));
        ResourceStream resourceStream = new ResourceStream(laNacionArticleProvider);
        ArticleProvider clarinProvider = new ClarinArticleProvider(Duration.ofMinutes(1));
        ResourceStream resourceStream2 = new ResourceStream(clarinProvider);
        clarinProvider.getArticles();
        Observer observer = new Observer<ResourceChange>() {
            @Override
            public void onSubscribe(Disposable d) {
                d.dispose();
            }

            @Override
            public void onNext(ResourceChange resourceChange) {
                System.out.println(resourceChange.resource().link() + " " + resourceChange.type());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Finished");
            }
        };
        resourceStream.subscribeActual(observer);
        resourceStream2.subscribeActual(observer);
        try {
            Thread.sleep(100000000);
            // TODO something not working well. But basically its working
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
