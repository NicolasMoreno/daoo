package nicolasmoreno.tp5;

import nicolasmoreno.tp5.resourceProvider.ArticleProvider;
import nicolasmoreno.tp5.resourceProvider.ClarinArticleProvider;
import nicolasmoreno.tp5.resourceProvider.LaNacionArticleProvider;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        ArticleProvider laNacionArticleProvider = new ClarinArticleProvider(Duration.ofMinutes(10));
        laNacionArticleProvider.readArticle();
        System.out.println("Finished");
    }
}
