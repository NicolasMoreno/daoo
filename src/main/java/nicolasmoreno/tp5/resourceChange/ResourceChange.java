package nicolasmoreno.tp5.resourceChange;

import nicolasmoreno.tp5.ChangeType;
import nicolasmoreno.tp5.resource.Resource;

public interface ResourceChange {
    Resource resource();
    ChangeType type();
}