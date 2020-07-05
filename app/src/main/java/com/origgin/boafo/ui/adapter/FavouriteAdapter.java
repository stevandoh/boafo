package com.origgin.boafo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.origgin.boafo.models.Entity;

import java.util.List;

import io.realm.Realm;

/**
 * Created by root on 7/13/17.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>{
    Realm realm;
    Context context;
    List<Entity> entities;

    // declare the color generator and drawable builder
    private ColorGenerator mColorGenerator;
    private TextDrawable.IBuilder mDrawableBuilder;


    public FavouriteAdapter(Context context, Realm realm, List<Entity> entities) {
        super();
        this.context = context;
        this.entities = entities;
        this.realm = realm;

    }

    @Override
    public FavouriteAdapter.FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FavouriteAdapter.FavouriteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        public FavouriteViewHolder(View itemView) {
            super(itemView);
        }
    }
}
