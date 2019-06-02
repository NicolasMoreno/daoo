package nicolasmoreno.tp5.resourceChange;

import nicolasmoreno.tp5.resource.Resource;

public class ResourceChangeFactory {

    public static ResourceChange addedArticle(Resource resource) {
        return new ArticleChange(resource, ChangeType.ADD);
    }

    public static ResourceChange modifiedArticle(Resource resource) {
        return new ArticleChange(resource, ChangeType.MODIFY);
    }

    public static ResourceChange removedArticle(Resource resource) {
        return new ArticleChange(resource, ChangeType.REMOVE);
    }

    public static ResourceChange unchangedArticle(Resource resource) {
        return new ArticleChange(resource, null);
    }
}
