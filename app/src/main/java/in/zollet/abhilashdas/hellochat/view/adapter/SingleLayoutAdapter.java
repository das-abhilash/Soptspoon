package in.zollet.abhilashdas.hellochat.view.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.zollet.abhilashdas.hellochat.model.User;
import in.zollet.abhilashdas.hellochat.view.viewmodel.BaseViewModel;

/**
 * Created by abhilashdas on 25/05/17.
 */

public class SingleLayoutAdapter<T> extends BaseRecyclerAdapter {


    private List<T> items;
    private int layoutId;
    private BaseViewModel viewModel;

    public SingleLayoutAdapter(Integer layoutId) {
        this.items = new ArrayList<>();
        this.layoutId = layoutId;
    }

    public SingleLayoutAdapter(int layoutId, BaseViewModel viewModel) {
        this.items = new ArrayList<>();
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    @Override
    public void updateList(List list) {
        this.items = list;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void setViewModel(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected Object getObjForPosition(int position) {
        return items.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public List<T> getData() {
        return items;
    }

    @Override
    public void remvoeItemFromList(int position) {

    }


}
