package com.example.spacex_test_task;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/*
DataBinding image converter, Picasso to download image.
 */


public class DataBindingImage {
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view);
    }
}
