package nicolasmoreno.tp5.resource;

import nicolasmoreno.tp5.change.ResourceChange;

import java.util.*;
import java.util.stream.Collectors;

import static nicolasmoreno.tp5.change.ResourceChangeFactory.*;

public class ArticleCabinet {

    private List<Resource> articles;

    public ArticleCabinet() {
        this.articles = new ArrayList<>();
    }

    //Only for testing
    ArticleCabinet(List<Resource> articles) {
        this.articles = articles;
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

    /**
     * Versión 2 de detección de cambios con los nuevos artículos
     * @param newArticles nuevos artículos
     * @return Lista de cambios
     */
    public List<ResourceChange> detectChanges(Iterable<Resource> newArticles) {
        List<ResourceChange> changes = new ArrayList<>();
        List<Resource> possibleRemovedResource = new ArrayList<>(); // Test if its empty
        if (articles.isEmpty()) {
            newArticles.forEach( newArticle -> {
                articles.add(newArticle);
                changes.add(addedArticle(newArticle));
            });
        }
        else {
            int iterationIndex = 0;
            for (Resource newArticle: newArticles) {
                final int articleIndex = articles.indexOf(newArticle);
                if ( articleIndex > -1) {
                    if (iterationIndex < articleIndex) { // Quiere decir que algo cambió
                        final List<Resource> subList = articles.subList(iterationIndex, articleIndex);
                        possibleRemovedResource.addAll(subList);
                        articles.removeAll(subList);
                    }
                } else {
                    final int removed = possibleRemovedResource.indexOf(newArticle);
                    if (removed == -1) {
                        changes.add(addedArticle(newArticle));
                        articles.add(iterationIndex, newArticle);
                    } else {
                        changes.add(modifiedArticle(newArticle));
                        articles.add(iterationIndex, possibleRemovedResource.remove(removed));
                    }
                }
                iterationIndex++;
            }
            if (iterationIndex < articles.size()) {
                final List<Resource> remainingResources = articles.subList(iterationIndex, articles.size());
                remainingResources.forEach( elem -> changes.add(removedArticle(elem)));
                articles.removeAll(remainingResources);
            }
            possibleRemovedResource.forEach( elem -> changes.add(removedArticle(elem)));
        }
        return changes;
    }


    // Only for testing
    List<Resource> getArticles() {
        return articles;
    }
}
