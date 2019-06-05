package nicolasmoreno.tp5;

import nicolasmoreno.tp5.observer.ResourceObserver;
import nicolasmoreno.tp5.provider.ClarinArticleProvider;
import nicolasmoreno.tp5.provider.LaNacionArticleProvider;

import java.time.Duration;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
//        ArticleProvider laNacionArticleProvider = new LaNacionArticleProvider(Duration.ofMinutes(1));
//        ArticleProvider clarinProvider = new ClarinArticleProvider(Duration.ofMinutes(1));
        ResourceStream resourceStream = new ResourceStream(new LaNacionArticleProvider(Duration.ofMinutes(1)));
        ResourceStream resourceStream2 = new ResourceStream(new ClarinArticleProvider(Duration.ofMinutes(1)));
        HashSet<ResourceStream> resourceSet = new HashSet<>();
        resourceSet.add(resourceStream);
        resourceSet.add(resourceStream2);
        ResourceObserver observer = new ResourceObserver();
        NewsStream newsStream = new NewsStream(resourceSet);
        newsStream.subscribeActual(observer);
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
