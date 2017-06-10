package in.zollet.abhilashdas.hellochat.dataprovider.eventbus;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subjects.PublishSubject;

/**
 * Created by Abhilash on 1/20/2017.
 */

public class FragmentTransactionObservable {

    private static FragmentTransactionObservable instance;
    private String toFragment = "";
    private Object object;

    private PublishSubject<String> event;
    private Subscription subscription;

    public FragmentTransactionObservable() {
        event = PublishSubject.create();
        subscription=event.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
    }

    public static FragmentTransactionObservable getInstance() {
        if (instance == null) {
            instance = new FragmentTransactionObservable();
        }
        return instance;
    }

    /**
     * Pass a fragemnt down to event listeners.
     */
    public void setToFragment (String  toFragment) {
        event.onNext(toFragment);
    }

    public String getToFragment() {
        return toFragment;
    }


    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }


    /**
     * Subscribe to this Observable. On event, do something e.g. replace a fragment
     */
    public Observable<String> getEvents() {
        return event;
    }
}
