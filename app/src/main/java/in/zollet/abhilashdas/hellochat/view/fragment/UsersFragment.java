package in.zollet.abhilashdas.hellochat.view.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.zollet.abhilashdas.hellochat.R;
import in.zollet.abhilashdas.hellochat.databinding.FragmentUsersBinding;
import in.zollet.abhilashdas.hellochat.view.fragment.BaseFragment;
import in.zollet.abhilashdas.hellochat.view.viewmodel.UsersViewModel;
import in.zollet.abhilashdas.hellochat.view.viewmodel.contract.LifeCycle;
import in.zollet.abhilashdas.hellochat.view.viewmodel.contract.ViewModelContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends BaseFragment implements ViewModelContract.View {

   /* @Inject
    UsersViewModel usersViewModel;*/

    UsersViewModel usersViewModel = new UsersViewModel();

    public static final String TO = "UserFragment";

    public UsersFragment() {
        // Required empty public constructor
        /* ((ZeloCustomerApplication) getActivity().getApplication()).component().inject(this);*/
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentUsersBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false);
        View view = binding.getRoot();
        binding.setModel(usersViewModel);
        return view;
    }

    @Override
    public LifeCycle.ViewModel getViewModel() {
        return usersViewModel;
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }


    @Override
    public Object getData() {
        return null;
    }
}
