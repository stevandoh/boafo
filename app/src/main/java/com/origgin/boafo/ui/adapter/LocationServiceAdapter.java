package com.origgin.boafo.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.origgin.boafo.R;
import com.origgin.boafo.models.BoafoMenuItem;

import java.util.List;

/**
 * Created by root on 7/12/17.
 */

public class LocationServiceAdapter extends RecyclerView.Adapter<LocationServiceAdapter.LocationServiceViewHolder>  {
    public List<BoafoMenuItem> boafoMenuItemList;


    @Override
    public LocationServiceAdapter.LocationServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_category, parent, false);
        LocationServiceAdapter.LocationServiceViewHolder mvh = new LocationServiceAdapter.LocationServiceViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(LocationServiceViewHolder holder, int position) {
        holder.photo.setImageResource(boafoMenuItemList.get(position).photoId);
        holder.title.setText(boafoMenuItemList.get(position).title);


    }


    @Override
    public int getItemCount() {
        return boafoMenuItemList.size();
    }
    public LocationServiceAdapter(List<BoafoMenuItem> boafoMenuItemList){
        this.boafoMenuItemList = boafoMenuItemList;

    }

    public class LocationServiceViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        ImageView photo;
        public LocationServiceViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            photo = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
