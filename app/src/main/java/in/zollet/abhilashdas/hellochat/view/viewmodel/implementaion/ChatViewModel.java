package in.zollet.abhilashdas.hellochat.view.viewmodel.implementaion;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import in.zollet.abhilashdas.hellochat.R;
import in.zollet.abhilashdas.hellochat.model.Message;
import in.zollet.abhilashdas.hellochat.model.User;
import in.zollet.abhilashdas.hellochat.utility.configuration.RecyclerConfiguration;
import in.zollet.abhilashdas.hellochat.view.adapter.ChatAdapter;
import in.zollet.abhilashdas.hellochat.view.adapter.SingleLayoutAdapter;
import in.zollet.abhilashdas.hellochat.view.viewmodel.BaseViewModel;
import in.zollet.abhilashdas.hellochat.view.viewmodel.contract.LifeCycle;
import in.zollet.abhilashdas.hellochat.view.viewmodel.contract.ViewModelContract;

/**
 * Created by abhilashdas on 30/05/17.
 */

public class ChatViewModel extends BaseViewModel implements ViewModelContract.ViewModel{


    private ViewModelContract.View viewCallback;
    public RecyclerConfiguration recyclerConfiguration = new RecyclerConfiguration();
    public ObservableBoolean enableSendButton = new ObservableBoolean(false);
    public ObservableField<String> msgText = new ObservableField<>("");
    public ObservableField<List<Message>> msgList = new ObservableField<>();
    public List<Message> messageList = new ArrayList<>();

    private DatabaseReference databaseReferenceFromUser;
    private DatabaseReference databaseReferenceToUser;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;
    private ChildEventListener childEventListener;

    @Override
    public void onViewResumed() {
        attachDatabaseReadListner();
    }

    @Override
    public void onViewCreated(@NonNull LifeCycle.View viewCallback) {

    }

    @Override
    public void onViewStart(@NonNull LifeCycle.View viewCallback) {
        this.viewCallback = (ViewModelContract.View) viewCallback;
        initRecycler();
        msgList.set(new ArrayList<Message>());
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        User user = (User) this.viewCallback.getData();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceFromUser = firebaseDatabase.getReference().child("messages").child(user.getuId()+"-"+firebaseUser.getUid());
        databaseReferenceToUser = firebaseDatabase.getReference().child("messages").child(firebaseUser.getUid()+"-"+user.getuId());

    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(viewCallback.getActivityContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        recyclerConfiguration.setLayoutManager(layoutManager);
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(new ChatAdapter(this));
    }

    public TextWatcher chatWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if (charSequence.toString().trim().length() > 0) {
                enableSendButton.set(true);
            } else {
                enableSendButton.set(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void onSendButtonClick(){
        Message msg = new Message(msgText.get().trim(), firebaseUser.getDisplayName(), null,firebaseUser.getUid());

        //Send messages on click
        databaseReferenceFromUser.push().setValue(msg);
        databaseReferenceToUser.push().setValue(msg);

        // Clear input box
        msgText.set("");
    }

    @Override
    public void onViewStop() {
        detachDatabaseReadListner();
    }

    @Override
    public void onViewDestroyed() {

    }

    @Override
    public void onDestroy() {

    }

    private void attachDatabaseReadListner() {
        if (childEventListener == null)
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Message message = dataSnapshot.getValue(Message.class);

                    List<Message> messageList = msgList.get();
                    messageList.add(message);
                    msgList.set(new ArrayList<Message>());
                    msgList.set(messageList);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
        databaseReferenceFromUser.addChildEventListener(childEventListener);
    }
    private void detachDatabaseReadListner() {
        if (childEventListener != null)
            databaseReferenceFromUser.removeEventListener(childEventListener);
        childEventListener = null;
        msgList.set(new ArrayList<Message>());
    }
}
