package nicolasmoreno.tp5.provider;

import nicolasmoreno.tp5.resource.Resource;

import java.time.Duration;

public interface ResourceProvider {
    Iterable<Resource> resources();
    Duration interval();
}
