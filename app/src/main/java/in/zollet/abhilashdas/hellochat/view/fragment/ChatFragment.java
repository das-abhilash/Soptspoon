package in.zollet.abhilashdas.hellochat.view.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import in.zollet.abhilashdas.hellochat.R;
import in.zollet.abhilashdas.hellochat.databinding.FragmentChatBinding;
import in.zollet.abhilashdas.hellochat.model.User;
import in.zollet.abhilashdas.hellochat.utility.Constants;
import in.zollet.abhilashdas.hellochat.view.viewmodel.contract.LifeCycle;
import in.zollet.abhilashdas.hellochat.view.viewmodel.contract.ViewModelContract;
import in.zollet.abhilashdas.hellochat.view.viewmodel.implementaion.ChatViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends BaseFragment implements ViewModelContract.View  {


    public static final String TO = "ChatFragment";

    ChatViewModel chatViewModel = new ChatViewModel();
    User user;
    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentChatBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false);
        View view = binding.getRoot();

        if(getArguments()!= null && getArguments().getParcelable(Constants.UserOBJ) != null)
        user = Parcels.unwrap(getArguments().getParcelable(Constants.UserOBJ));
        else
        user = new User();

        binding.setModel(chatViewModel);
        return view;
    }


    @Override
    public LifeCycle.ViewModel getViewModel() {
        return chatViewModel;
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public Object getData() {
        return user;
    }
}
