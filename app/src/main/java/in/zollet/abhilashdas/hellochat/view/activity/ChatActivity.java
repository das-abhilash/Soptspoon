package in.zollet.abhilashdas.hellochat.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import in.zollet.abhilashdas.hellochat.R;
import in.zollet.abhilashdas.hellochat.dataprovider.eventbus.FragmentTransactionObservable;
import in.zollet.abhilashdas.hellochat.utility.Constants;
import in.zollet.abhilashdas.hellochat.view.fragment.ChatFragment;
import in.zollet.abhilashdas.hellochat.view.fragment.UsersFragment;
import rx.Subscriber;
import rx.Subscription;

public class ChatActivity extends AppCompatActivity {

   /* @Inject
    public FragmentTransactionObservable fragmentTransactionObservable;

    Subscription subscription;*/

    public FragmentTransactionObservable fragmentTransactionObservable;

    public static final String TAG = "SearchActivity";
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        fragmentTransactionObservable = FragmentTransactionObservable.getInstance();

        checkFromEvent(UsersFragment.TO);
    }

    public void checkFromEvent(String toFragment) {
        Fragment fragment;
        Bundle bundle = new Bundle();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (toFragment){
            case UsersFragment.TO:
                fragment = new UsersFragment();
                fragmentTransaction.addToBackStack(null);
                break;

            case ChatFragment.TO:
                fragment = new ChatFragment();
                bundle.putParcelable(Constants.UserOBJ, Parcels.wrap(fragmentTransactionObservable.getObject()));
                fragment.setArguments(bundle);
                fragmentTransaction.addToBackStack(null);
                break;

            default:
                fragment = new UsersFragment();
        }

        fragmentTransaction.replace(R.id.container, fragment).commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        subscription = fragmentTransactionObservable.getEvents()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String to) {
                        checkFromEvent(to);
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
