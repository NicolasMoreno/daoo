package nicolasmoreno.tp5.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import nicolasmoreno.tp5.change.ResourceChange;

public class ResourceObserver implements Observer<ResourceChange> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResourceChange resourceChange) {
        System.out.println(resourceChange.resource().link() + " " + resourceChange.type());
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Finished");
    }
}
