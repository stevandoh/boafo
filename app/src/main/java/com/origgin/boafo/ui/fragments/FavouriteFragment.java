package com.origgin.boafo.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.origgin.boafo.R;
import com.origgin.boafo.models.Entity;
import com.origgin.boafo.models.FavoriteItem;
import com.origgin.boafo.ui.activities.EntityProfileACT;
import com.origgin.boafo.ui.adapter.EntityAdapter;
import com.origgin.boafo.utils.GenUtils;
import com.origgin.boafo.utils.ItemClickSupport;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavouriteFragment extends Fragment {

    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private Realm realm;
    private EntityAdapter entityAdapter;
    private RealmResults<Entity> results;
    private List<Entity> entities;
    private List<FavoriteItem> favoriteItems;


    public FavouriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        unbinder = ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        Intent intentCategory = getActivity().getIntent();
        favoriteItems = new ArrayList<>();
        setHasOptionsMenu(true);
        entities = new ArrayList<>();
        entities = getEntities();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
    }

    private void setRecyclerView() {


//        recyclerView.addItemDecoration(mDividerItemDecoration);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//        mDividerItemDecoration = new DividerItemDecoration(rv.getContext(),
//                new LinearLayoutManager(this).getOrientation());
        rv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        rv.addItemDecoration(new VerticalDividerItemDecoration.Builder(getContext()).build());

        if(entities == null){
            rv.setVisibility(View.INVISIBLE);

        }else{
            entityAdapter = new EntityAdapter(getContext(), realm, entities);

        }


        rv.setAdapter(entityAdapter);


        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                GenUtils.getToastMessage(getContext(), String.valueOf(position));

                setEntityIntent(position, v);
            }
        });
    }

    private void setEntityIntent(int position, View v) {
        Intent intentEntity = new Intent(v.getContext(), EntityProfileACT.class);
        String entityId = getEntities().get(position).getId();
        intentEntity.putExtra(getString(R.string.entity_id_key), entityId);

        startActivity(intentEntity);
    }

    private List<FavoriteItem> setData() {


        return realm.where(FavoriteItem.class).equalTo("favorite", true).findAll();

    }

    private List<Entity> getEntities() {

        List<Entity> filtered = null;
        for (FavoriteItem favoriteItem : setData()) {
            entities.add(favoriteItem.getEntity());


            filtered = new ArrayList<>(new HashSet<>(entities));


        }

        return filtered;


    }
}


