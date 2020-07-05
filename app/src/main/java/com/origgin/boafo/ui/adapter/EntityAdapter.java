package com.origgin.boafo.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.origgin.boafo.R;
import com.origgin.boafo.models.Entity;
import com.origgin.boafo.models.FavoriteItem;
import com.origgin.boafo.utils.GenUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;

/**
 * Created by root on 5/30/17.
 */

public class EntityAdapter extends RecyclerView.Adapter<EntityAdapter.EntityViewHolder> {

    Realm realm;
    Context context;
    List<Entity> entities;

    // declare the color generator and drawable builder
    private ColorGenerator mColorGenerator;
    private TextDrawable.IBuilder mDrawableBuilder;


    public EntityAdapter(Context context, Realm realm, List<Entity> entities) {
        super();
        this.context = context;
        this.entities = entities;
        this.realm = realm;

    }

    public Entity getItem(int position) {
        if (this.entities == null || this.entities.get(position) == null) {
            return null;
        }
        return this.entities.get(position);
    }

    public void setFilter(List<Entity> entitiesFiltered) {
        entities = new ArrayList<>();
        entities.addAll(entitiesFiltered);
        notifyDataSetChanged();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public EntityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        EntityViewHolder pvh = new EntityViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(EntityViewHolder personViewHolder, int position) {
        final Entity entity = getItem(position);
        mColorGenerator = ColorGenerator.MATERIAL;
        mDrawableBuilder = TextDrawable.builder()
                .round();
        TextDrawable drawable = mDrawableBuilder.build(String.valueOf(entity.getName().charAt(0)), mColorGenerator.getColor(entity.getName()));
        personViewHolder.imageView.setImageDrawable(drawable);
//        holder.view.setBackgroundColor(Color.TRANSPARENT);
        personViewHolder.personName.setText(entity.getName());
        personViewHolder.personOccupation.setText(entity.getOccupation());
        personViewHolder.personLocation.setText(entity.getLocation());
//        personViewHolder.favorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Realm realm = null;
//                        realm = Realm.getDefaultInstance();
////                        FavoriteItem favorit = realm.where(FavoriteItem.class).equalTo("entity.id", entity.getId()).findFirst();
////                        favorit .isFavorite();
////                        if (favorite=true){
//                            realm = Realm.getDefaultInstance();
//                            final FavoriteItem favoriteItem = new FavoriteItem();
//                            String id = UUID.randomUUID().toString();
//                            favoriteItem.setId(id);
//                            favoriteItem.setEntity(entity);
//                            favoriteItem.setFavorite(true);
//                            realm.executeTransaction(new Realm.Transaction() {
//                                @Override
//                                public void execute(Realm realm) {
//                                    realm.copyToRealmOrUpdate(favoriteItem);
//                                }
//                            });
//
//            }
//        });
        Realm realm = null;
        realm = Realm.getDefaultInstance();
        FavoriteItem favorit = realm.where(FavoriteItem.class).equalTo("entity.id", entity.getId()).findFirst();
        if(favorit != null){
            personViewHolder.favorite. setFavorite(favorit.isFavorite());
//            personViewHolder.favorite.setFavorite(isFavorite(getItem(position)), favorit.isFavorite());
        }
//        else {
//            personViewHolder.favorite.setFavorite(false);
//        }


        personViewHolder.favorite.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView,  boolean favorite) {
                        Realm realm = null;
                        realm = Realm.getDefaultInstance();
//                        FavoriteItem favorit = realm.where(FavoriteItem.class).equalTo("entity.id", entity.getId()).findFirst();
//                        favorit .isFavorite();
                        if (favorite){
                            final boolean fav = favorite;

                            final String id = UUID.randomUUID().toString();

                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    FavoriteItem favoriteItem = realm.createObject(FavoriteItem.class, id);
                                    favoriteItem.setEntity(entity);
                                    favoriteItem.setFavorite(fav);

                                }
                            });

                            final FavoriteItem favorit = realm.where(FavoriteItem.class).equalTo("entity.id", entity.getId()).findFirst();
                            if (favorit != null){
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        favorit.setFavorite(true);
                                    }
                                });



                            }


                        }else if (!favorite ){
                            final FavoriteItem favorit = realm.where(FavoriteItem.class).equalTo("entity.id", entity.getId()).findFirst();
                            if (favorit != null){
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        favorit.setFavorite(false);
//                                        favorit.deleteFromRealm();
//                                        notifyDataSetChanged();
                                    }
                                });



                            }

                        }




                    }
                });


    }


    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class EntityViewHolder extends RecyclerView.ViewHolder {

        //        CardView cv;
        TextView personName;
        TextView personOccupation;
        TextView personLocation;
        TextView personExperience;
        ImageView imageView;
        MaterialFavoriteButton favorite;


        EntityViewHolder(View itemView) {
            super(itemView);
//            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personOccupation = (TextView) itemView.findViewById(R.id.person_occupation);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            personLocation = (TextView) itemView.findViewById(R.id.person_location);
            favorite = (MaterialFavoriteButton) itemView.findViewById(R.id.btn_favorite);
        }
    }
}