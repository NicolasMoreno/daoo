package nicolasmoreno.tp5.resource;

import nicolasmoreno.tp5.change.ResourceChange;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static nicolasmoreno.tp5.change.ResourceChangeFactory.*;

public class ArticleCabinet {

    private List<Resource> articles;

    public ArticleCabinet() {
        this.articles = new ArrayList<>();
    }

    public List<ResourceChange> compareArticles(Iterable<Resource> newArticles) {
        List<ResourceChange> changes = new ArrayList<>();
        newArticles.forEach( newArticle -> {
            if (articles.size() == 0) {
                changes.add(addedArticle(newArticle));
                articles.add(newArticle);
            } else {
                final Optional<Resource> optionalArticle = articles.stream().filter(oldArticle -> oldArticle.id().equals(newArticle.link())).findFirst();
                if (optionalArticle.isPresent()) {
                    final Resource resource = optionalArticle.get();
                    changes.add(resource.body().equalsIgnoreCase(newArticle.body()) ? unchangedArticle(newArticle) : modifiedArticle(newArticle));
                } else {
                    changes.add(addedArticle(newArticle));
                    articles.add(newArticle);
                }
            }
        });
        final List<String> collect = changes.stream().map(article -> article.resource().link()).collect(Collectors.toList());
        if (collect.size() > 0) {
            final List<Resource> removedArticles = articles.stream().filter(oldArticle -> collect.indexOf(oldArticle.link()) == -1).collect(Collectors.toList());
            System.out.println("Removed articles = " + removedArticles.size());
            removedArticles.forEach( removedArticle -> {
                changes.add(removedArticle(removedArticle));
                articles.remove(removedArticle);
            });
        }
        return changes;
    }


}
