package nicolasmoreno.tp5.resourceChange;

import nicolasmoreno.tp5.resource.Resource;
import org.jetbrains.annotations.NotNull;

public class ArticleChange implements ResourceChange {

    private Resource resource;
    private ChangeType changeType;

    public ArticleChange(@NotNull Resource resource, ChangeType changeType) {
        this.resource = resource;
        this.changeType = changeType;
    }

    @Override
    public Resource resource() {
        return resource;
    }

    @Override
    public ChangeType type() {
        return changeType;
    }
}
