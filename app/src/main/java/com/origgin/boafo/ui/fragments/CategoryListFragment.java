package com.origgin.boafo.ui.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.origgin.boafo.R;
import com.origgin.boafo.models.BoafoMenuItem;
import com.origgin.boafo.models.Entity;
import com.origgin.boafo.ui.activities.EntityListACT;
import com.origgin.boafo.ui.adapter.CategoryAdapter;
import com.origgin.boafo.utils.GenUtils;
import com.origgin.boafo.utils.ItemClickSupport;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A placeholder fragment containing a simple view.
 */
public class CategoryListFragment extends Fragment {

    RealmResults<Entity> results;
    private RecyclerView recyclerView;
    private Realm realm;
    private CategoryAdapter categoryAdapter;
    private ProgressBar progressBar;
    private List<Entity> entities;
    private List<String> occupationList;
    private List<BoafoMenuItem> menuItems;


    public CategoryListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        realm = Realm.getDefaultInstance();
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

//        if (setData().isEmpty()) {
//            prepareDB();
//
//        }
        prepareDB();
        setRecyclerView();


        return view;
    }

    private void setRecyclerView() {


//        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).build());
        recyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(getContext()).build());
//       entityAdapter = new EntityAdapter(getContext(), realm, results);
        getOccupations();
        initializeData();
        categoryAdapter = new CategoryAdapter(menuItems);

        Log.d("MItem", menuItems.size() +"");
        recyclerView.setAdapter(categoryAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                setEntityIntent(position, v);
            }
        });
    }

    private void setEntityIntent(int position, View v) {
        Intent intentEntity = new Intent(v.getContext(), EntityListACT.class);
        String entityOccupation = occupationList.get(position);
        intentEntity.putExtra(getString(R.string.entity_id_occupation), entityOccupation);

        startActivity(intentEntity);
    }

    private List<Entity> setData() {
        results = realm.where(Entity.class).distinct("occupation").sort("occupation", Sort.ASCENDING);
        return results;
    }

    private List<String> getOccupations() {
        occupationList = new ArrayList<>();
        for (Entity entity : setData()) {
            occupationList.add(entity.getOccupation());
        }
        Log.d("Occupation list", "" + occupationList.size());
        return occupationList;
    }

    private void prepareDB() {
        new CategoryListFragment.StoreData().execute();

//        new StoreData().execute();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_category_list, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                entityAdapter.setFilter(setQuery(query));
//
//                return true;
//
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                entityAdapter.setFilter(setQuery(newText));
//
//                return true;
//            }
//        });
    }

    private RealmResults setQuery(String query) {

//        private String occupation;
//        private String specialization;
//        private String location;
//        private String experience;
//        private String phone;

        results = realm.where(Entity.class)
                .contains("occupation", query, Case.INSENSITIVE)
                .findAll();
        return results;
    }

    private void initializeData() {
        menuItems = new ArrayList<>();
        for (String item : getOccupations()) {
            menuItems.add(new BoafoMenuItem(item,R.drawable.services));

        }
        Log.d("BoafoMenuItem", menuItems.toString());

//        menuItems.add(new com.origgin.boafo.models.BoafoMenuItem("Location Services", R.drawable.locations));
    }

    private class StoreData extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {

            Realm realm = null;
            try {
                realm = Realm.getDefaultInstance();
                // ... Use the Realm instance ...
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        InputStream inputStream = getResources().openRawResource(R.raw.data);
                        try {
//                            realm.createAllFromJson(Entity.class, inputStream);
                            realm.createOrUpdateAllFromJson(Entity.class, inputStream);
                        } catch (Exception e) {
                            realm.cancelTransaction();
                        }

                    }
                });
            } finally {
                if (realm != null) {
                    realm.close();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            // Runs on the UI thread before doInBackground
            // Good for toggling visibility of a progress indicator
            progressBar.setVisibility(ProgressBar.VISIBLE);

        }


        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            setRecyclerView();
        }
    }

}
