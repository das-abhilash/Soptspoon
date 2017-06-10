package in.zollet.abhilashdas.hellochat.utility;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import in.zollet.abhilashdas.hellochat.customViews.CircleCropImageTransformation;
import in.zollet.abhilashdas.hellochat.utility.configuration.RecyclerConfiguration;
import in.zollet.abhilashdas.hellochat.view.adapter.BaseRecyclerAdapter;

/**
 * Created by abhilashdas on 25/05/17.
 */

public class BindingUtil {

    @BindingAdapter({"imageUrl", "errorDrawble"})
    public static void setImage(ImageView imageView, @NonNull String url, Drawable errorDrawable) {
        Glide.with(imageView.getContext()).load(url).placeholder(errorDrawable).centerCrop().into(imageView);
    }

    @BindingAdapter({"circleImageUrl", "errorDrawble"})
    public static void setCircleImage(ImageView imageView, @NonNull String url, Drawable errorDrawable) {

        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .error(errorDrawable)
                .transform(new CircleCropImageTransformation(context))
                .into(imageView);
    }

    @BindingAdapter({"configurationWithAdapter", "recyclerItems"})
    public static <T> void configureRecyclerView(RecyclerView recyclerView, @NonNull RecyclerConfiguration configuration, List<T> items)

    {
        BaseRecyclerAdapter baseRecyclerAdapter = (BaseRecyclerAdapter) recyclerView.getAdapter();
        if (baseRecyclerAdapter != null) {
            setItems(recyclerView, items);
        } else {
            configureRecyclerView(recyclerView, configuration);
            setItems(recyclerView, items);

        }
    }

    @BindingAdapter("recyclerItem")
    public static <T> void setItems(RecyclerView recyclerView, List<T> items) {
        BaseRecyclerAdapter baseRecyclerAdapter = (BaseRecyclerAdapter) recyclerView.getAdapter();
        if (baseRecyclerAdapter != null) {

            if (items == null) {
                items = new ArrayList<>();
            }
            baseRecyclerAdapter.updateList(items);
            baseRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @BindingAdapter("configuration")
    public static void configureRecyclerView(RecyclerView recyclerView, @NonNull RecyclerConfiguration configuration) {


        if (configuration == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = configuration.getLayoutManager();

        int orientation = configuration.getOrientation();

        if (recyclerView.getLayoutManager() == null) {
            recyclerView.setLayoutManager(configuration.getLayoutManager());
        }

        recyclerView.setItemAnimator(configuration.getItemAnimator());
        recyclerView.setHasFixedSize(configuration.isHasFixedSize());
        if (configuration.getItemDecoration() != null) {
            recyclerView.addItemDecoration(configuration.getItemDecoration());
        }

        if (configuration.getAdapter() != null)
            recyclerView.setAdapter(configuration.getAdapter());

    }

}
