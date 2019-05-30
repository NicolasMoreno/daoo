package nicolasmoreno.tp5;

import io.reactivex.Observable;
import io.reactivex.Observer;
import nicolasmoreno.tp5.resourceProvider.ResourceProvider;

public class ResourceStream extends Observable<ResourceChange> {

    public ResourceStream(ResourceProvider resourceProvider) {
    }

    @Override
    protected void subscribeActual(Observer<? super ResourceChange> observer) {

    }
}
