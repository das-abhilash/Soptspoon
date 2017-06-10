package in.zollet.abhilashdas.hellochat.utility.configuration;

/**
 * Created by abhilashdas on 26/05/17.
 */

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import in.zollet.abhilashdas.hellochat.BR;


/**
 * Created by Abhilash on 2/10/2017.
 */

public class RecyclerConfiguration extends BaseObservable {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private RecyclerView.ItemDecoration itemDecoration;
    private int orientation;
    boolean hasFixedSize;
    private RecyclerView.Adapter adapter;

    public RecyclerConfiguration() {
        itemAnimator=new DefaultItemAnimator();
        orientation= LinearLayoutManager.VERTICAL;
        hasFixedSize = false;
    }

    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public int getOrientation() {
        return orientation;
    }

    @Bindable
    public void setOrientation(int orientation) {
        this.orientation = orientation;
        notifyPropertyChanged(BR.orientation);
    }

    @Bindable
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;

        notifyPropertyChanged(BR.layoutManager);
    }

    public RecyclerView.ItemAnimator getItemAnimator() {
        return itemAnimator;
    }

    @Bindable
    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        this.itemAnimator = itemAnimator;
        notifyPropertyChanged(BR.itemAnimator);
    }

    public boolean isHasFixedSize() {
        return hasFixedSize;
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        this.hasFixedSize = hasFixedSize;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return itemDecoration;
    }

    @Bindable
    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
        notifyPropertyChanged(BR.itemDecoration);
    }
}
