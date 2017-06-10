package in.zollet.abhilashdas.hellochat.view.adapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import in.zollet.abhilashdas.hellochat.R;
import in.zollet.abhilashdas.hellochat.model.Message;
import in.zollet.abhilashdas.hellochat.view.viewmodel.BaseViewModel;

/**
 * Created by abhilashdas on 30/05/17.
 */

public class ChatAdapter extends BaseRecyclerAdapter {



    private List<Message> items;
    private BaseViewModel baseViewModel;
//    private  int layoutId;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    public ChatAdapter(BaseViewModel baseViewModel){
        this.baseViewModel = baseViewModel;
        items = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }



    @Override
    public BaseViewModel getViewModel() {
        return baseViewModel;
    }

    @Override
    public void setViewModel(BaseViewModel viewModel) {
        baseViewModel = viewModel;
    }

    @Override
    protected Message getObjForPosition(int position) {
        return items.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        Message msg = getObjForPosition(position);

        if(msg.getuId().equalsIgnoreCase(firebaseUser.getUid())) {
            return R.layout.adapter_msg_from;
        } else
            return R.layout.adapter_msg_to;

    }

    @Override
    public void updateList(List list) {
        if(items != null && list != null) {
            items.clear();
            items.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public List getData() {
        return items;
    }

    @Override
    public void remvoeItemFromList(int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
