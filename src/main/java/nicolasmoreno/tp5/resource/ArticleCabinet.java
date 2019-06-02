package nicolasmoreno.tp5.resource;

import nicolasmoreno.tp5.resourceChange.ResourceChange;
import nicolasmoreno.tp5.resourceChange.ResourceChangeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static nicolasmoreno.tp5.resourceChange.ResourceChangeFactory.*;

public class ArticleCabinet {

    private List<Article> articles;

    public ArticleCabinet() {
        this.articles = new ArrayList<>();
    }

    public List<ResourceChange> compareArticles(List<Article> newArticles) {
        List<ResourceChange> changes;
        if (articles.size() == 0) {
            changes = newArticles.stream()
                    .map(ResourceChangeFactory::addedArticle).collect(Collectors.toList());
        } else {
            // Checkeo los nuevos con los viejos, para ver si fueron modificados o se agregaron nuevos
            changes = newArticles.stream().map( article -> {
                final Optional<Article> optionalArticle = articles.stream().filter(archivedArticle -> archivedArticle.id().equals(article.id())).findFirst();
                if ( optionalArticle.isPresent() ) {
                    final Article archivedArticle = optionalArticle.get();
                    return archivedArticle.equals(article) ?
                            unchangedArticle(article) :
                            modifiedArticle(article);
                } else {
                    return addedArticle(article);
                }
            }).collect(Collectors.toList());
        }
        // Ahora debería checkear los viejos con los nuevos, para ver si los viejos fueron eliminados
        articles.forEach( oldArticle -> {
            final Optional<Article> optionalArticle = newArticles.stream().filter(newArticle -> newArticle.id().equals(oldArticle.id())).findFirst();
            if (!optionalArticle.isPresent()) {
                changes.add(removedArticle(oldArticle));
            }
        });
        return changes;
    }


}
