package nicolasmoreno.tp5.change;

import nicolasmoreno.tp5.resource.Resource;

public interface ResourceChange {
    Resource resource();
    ChangeType type();
}
