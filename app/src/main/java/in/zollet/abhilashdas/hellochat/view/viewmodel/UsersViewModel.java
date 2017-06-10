package in.zollet.abhilashdas.hellochat.view.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import in.zollet.abhilashdas.hellochat.MainActivity;
import in.zollet.abhilashdas.hellochat.R;
import in.zollet.abhilashdas.hellochat.dataprovider.eventbus.FragmentTransactionObservable;
import in.zollet.abhilashdas.hellochat.model.User;
import in.zollet.abhilashdas.hellochat.utility.configuration.RecyclerConfiguration;
import in.zollet.abhilashdas.hellochat.view.adapter.SingleLayoutAdapter;
import in.zollet.abhilashdas.hellochat.view.fragment.ChatFragment;
import in.zollet.abhilashdas.hellochat.view.viewmodel.contract.LifeCycle;
import in.zollet.abhilashdas.hellochat.view.viewmodel.contract.ViewModelContract;

/**
 * Created by abhilashdas on 25/05/17.
 */

public class UsersViewModel extends BaseViewModel implements ViewModelContract.ViewModel {

    public UsersViewModel() {
        fragmentTransactionObservable = FragmentTransactionObservable.getInstance();
    }

    private FragmentTransactionObservable fragmentTransactionObservable;
    private DatabaseReference userDatabaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    public ObservableField<List<User>> userList = new ObservableField<>();
    public RecyclerConfiguration recyclerConfiguration = new RecyclerConfiguration();
    private ViewModelContract.View viewCallback;
    public ObservableInt progressBarVisibility = new ObservableInt(View.VISIBLE);

    FirebaseUser firebaseUser;

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewCreated(@NonNull LifeCycle.View viewCallback) {

    }

    public void initRecycler() {
        // no need to add layoutmanager as autofitRecylerView uses gridlayoutmanger by default
//        recyclerConfiguration.setLayoutManager(new LinearLayoutManager(viewCallback.getActivityContext(), LinearLayoutManager.VERTICAL, false));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(new SingleLayoutAdapter(R.layout.adapter_user,this));
    }

    @Override
    public void onViewStart(@NonNull LifeCycle.View viewCallback) {
        this.viewCallback = (ViewModelContract.View) viewCallback;

        initRecycler();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        userDatabaseReference = firebaseDatabase.getReference().child("user");

        userDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<User> list = new ArrayList<>();
                Iterable<DataSnapshot> users = dataSnapshot.getChildren();
                while (users.iterator().hasNext()) {
                    list.add(users.iterator().next().getChildren().iterator().next().getValue(User.class));
                }
                userList.set(list);
                progressBarVisibility.set(View.GONE);
                saveUserToDB(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onUserItemClicked(User user) {
        fragmentTransactionObservable.setObject(user);
        fragmentTransactionObservable.setToFragment(ChatFragment.TO);
    }

    private void saveUserToDB(DataSnapshot dataSnapshot) {
        if(!dataSnapshot.hasChild(firebaseUser.getUid())) {
            User user = new User(firebaseUser.getUid(),firebaseUser.getDisplayName(),firebaseUser.getEmail(),firebaseUser.getPhotoUrl());
            userDatabaseReference.child(firebaseUser.getUid()).push().setValue(user);
        }
        else {
            Iterable<DataSnapshot> users = dataSnapshot.child(firebaseUser.getUid()).getChildren();
            User user = users.iterator().next().getValue(User.class);
        }
    }



    @Override
    public void onViewStop() {

    }

    @Override
    public void onViewDestroyed() {

    }

    @Override
    public void onDestroy() {

    }
}
