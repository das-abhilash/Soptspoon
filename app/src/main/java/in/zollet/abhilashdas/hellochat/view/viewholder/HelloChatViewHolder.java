package in.zollet.abhilashdas.hellochat.view.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import in.zollet.abhilashdas.hellochat.BR;

import in.zollet.abhilashdas.hellochat.view.adapter.BaseRecyclerAdapter;
import in.zollet.abhilashdas.hellochat.view.viewmodel.BaseViewModel;


/**
 * Created by abhilashdas on 12/02/17.
 */

public class HelloChatViewHolder<T> extends RecyclerView.ViewHolder {

    private final ViewDataBinding mBinding;

    public HelloChatViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bind(Object item, int position, BaseRecyclerAdapter<T> adapter, BaseViewModel viewModel) {
        mBinding.setVariable(BR.item, item);
        mBinding.setVariable(BR.position, position);
        mBinding.setVariable(BR.model, viewModel);
        mBinding.setVariable(BR.adapter, adapter);
        mBinding.executePendingBindings();
    }
}