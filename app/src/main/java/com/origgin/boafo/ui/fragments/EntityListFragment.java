package com.origgin.boafo.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.origgin.boafo.R;
import com.origgin.boafo.models.Entity;
import com.origgin.boafo.ui.activities.EntityProfileACT;
import com.origgin.boafo.ui.adapter.EntityAdapter;
import com.origgin.boafo.utils.GenUtils;
import com.origgin.boafo.utils.ItemClickSupport;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class EntityListFragment extends Fragment {


    Unbinder unbinder;
    Bundle b;
    @BindView(R.id.rv)
    RecyclerView rv;
    private Realm realm;
    private EntityAdapter entityAdapter;
    private RealmResults<Entity> results;
    private List<Entity> entities;

    public EntityListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entity, container, false);
        unbinder = ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        Intent intentCategory = getActivity().getIntent();
        setHasOptionsMenu(true);
        b = intentCategory.getExtras();
        getActivity().setTitle(b.getString(
                getString(R.string.entity_id_occupation)));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setRecyclerView() {


//        recyclerView.addItemDecoration(mDividerItemDecoration);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//        mDividerItemDecoration = new DividerItemDecoration(rv.getContext(),
//                new LinearLayoutManager(this).getOrientation());
        rv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        rv.addItemDecoration(new VerticalDividerItemDecoration.Builder(getContext()).build());
        results = setData();
        entityAdapter = new EntityAdapter(getContext(), realm, results);


        rv.setAdapter(entityAdapter);

        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                setEntityIntent(position, v);
            }
        });
    }

    private void setEntityIntent(int position, View v) {
        Intent intentEntity = new Intent(v.getContext(), EntityProfileACT.class);
        String entityId = results.get(position).getId();
        intentEntity.putExtra(getString(R.string.entity_id_key), entityId);

        startActivity(intentEntity);
    }

    private RealmResults<Entity> setData() {
       return realm.where(Entity.class).equalTo("occupation", b.getString(
                getString(R.string.entity_id_occupation))).findAll();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_entity, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                entityAdapter.setFilter(setQuery(query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                entityAdapter.setFilter(setQuery(newText));
                return true;
            }
        });
    }

    private RealmResults<Entity> setQuery(String query) {

        results = realm.where(Entity.class)
                .beginGroup()
                .contains("occupation", b.getString(
                        getString(R.string.entity_id_occupation)))
                .contains("location", query, Case.INSENSITIVE)
                .or()
                .contains("specialization", query, Case.INSENSITIVE)
                .or()
                .contains("name",query,Case.INSENSITIVE)
                .endGroup()


//                .findAll();
               .findAll();

        return results;
    }

}
