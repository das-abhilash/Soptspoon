package in.zollet.abhilashdas.hellochat.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import in.zollet.abhilashdas.hellochat.view.viewholder.HelloChatViewHolder;
import in.zollet.abhilashdas.hellochat.view.viewmodel.BaseViewModel;

/**
 * Created by abhilashdas on 24/05/17.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<HelloChatViewHolder> {

    public HelloChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new HelloChatViewHolder(binding);
    }

    public void onBindViewHolder(HelloChatViewHolder holder, int position) {
        Object obj = getObjForPosition(position);
        holder.bind(obj,position,this, getViewModel());
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public abstract BaseViewModel getViewModel();

    public abstract void setViewModel(BaseViewModel viewModel);

    protected abstract Object getObjForPosition(int position);

    protected abstract int getLayoutIdForPosition(int position);

    public abstract void updateList(List list);

    public abstract List<T> getData();

    public abstract void remvoeItemFromList(int position);
}


