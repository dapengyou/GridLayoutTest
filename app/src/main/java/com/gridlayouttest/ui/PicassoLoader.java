package com.gridlayouttest.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.gridlayouttest.R;
import com.lzy.ninegrid.NineGridView;
import com.squareup.picasso.Picasso;

/**
 * Created by lady_zhou on 2017/4/11.
 */
public class PicassoLoader implements NineGridView.ImageLoader{
    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
            Picasso.with(context).load(url)//
                    .placeholder(R.drawable.ic_default_image)//
                    .error(R.drawable.ic_default_image)//
                    .into(imageView);

    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
