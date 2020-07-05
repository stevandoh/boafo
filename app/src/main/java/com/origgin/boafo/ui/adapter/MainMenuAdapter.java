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
 * Created by root on 7/5/17.
 */

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder>  {
   public List<BoafoMenuItem> boafoMenuItemList;


    @Override
    public MainMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_menu, parent, false);
        MainMenuViewHolder mvh = new MainMenuViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MainMenuViewHolder holder, int position) {

        holder.photo.setImageResource(boafoMenuItemList.get(position).photoId);
        holder.title.setText(boafoMenuItemList.get(position).title);


    }

    @Override
    public int getItemCount() {
        return boafoMenuItemList.size();
    }
    public MainMenuAdapter(List<BoafoMenuItem> boafoMenuItemList){
        this.boafoMenuItemList = boafoMenuItemList;

    }

    public class MainMenuViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        ImageView photo;
        public MainMenuViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textViewMenu);
            photo = (ImageView) itemView.findViewById(R.id.imageViewMenu);
        }
    }
}
