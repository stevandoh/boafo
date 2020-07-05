package com.origgin.boafo.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.origgin.boafo.R;
import com.origgin.boafo.models.BoafoMenuItem;

import java.util.ArrayList;

/**
 * Created by root on 7/12/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }


    // references to our images
    private Integer[] mThumbIds = {

            R.drawable.atm, R.drawable.hospital,
            R.drawable.policeman, R.drawable.fireman
    };

//    private void initializeData() {
//        boafoMenuItems = new ArrayList<>();
//        boafoMenuItems.add(new BoafoMenuItem("ATM", R.drawable.atm));
//        boafoMenuItems.add(new BoafoMenuItem("HOSPITAL", R.drawable.hospital));
//        boafoMenuItems.add(new BoafoMenuItem("POLICE", R.drawable.policeman));
//        boafoMenuItems.add(new BoafoMenuItem("FIRE SERVICE", R.drawable.fireman));
//    }
}

