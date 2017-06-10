package in.zollet.abhilashdas.hellochat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.zollet.abhilashdas.hellochat.view.viewmodel.contract.LifeCycle;

public abstract class BaseFragment extends Fragment implements LifeCycle.View {

   public static final String TAG="BaseFragment";

    public abstract LifeCycle.ViewModel getViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        getViewModel().onViewResumed();
    }

    @Override
    public void onStart() {
        super.onStart();
        getViewModel().onViewStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().onViewStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getViewModel().onViewDestroyed();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getViewModel().onViewCreated(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getViewModel().onDestroy();
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}
