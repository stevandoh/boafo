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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    public List<BoafoMenuItem> boafoMenuItemList;


    public CategoryAdapter(List<BoafoMenuItem> boafoMenuItemList) {
        this.boafoMenuItemList = boafoMenuItemList;

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_category, parent, false);
        CategoryViewHolder mvh = new CategoryViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

        holder.photo.setImageResource(boafoMenuItemList.get(position).photoId);
        holder.title.setText(boafoMenuItemList.get(position).title);


    }

    @Override
    public int getItemCount() {
        return boafoMenuItemList.size();
    }

    public void setFilter(List<BoafoMenuItem> entitiesFiltered) {
        boafoMenuItemList.addAll(entitiesFiltered);
        notifyDataSetChanged();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title;
        ImageView photo;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textViewMenu);
            photo = (ImageView) itemView.findViewById(R.id.imageViewMenu);
        }
    }
}
