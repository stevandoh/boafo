package com.origgin.boafo.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.origgin.boafo.R;
import com.origgin.boafo.ui.adapter.EntityAdapter;
import com.origgin.boafo.models.Entity;
import com.origgin.boafo.utils.GenUtils;
import com.origgin.boafo.utils.ItemClickSupport;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.io.InputStream;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainACT extends AppCompatActivity {


    RealmResults<Entity> results;
    private RecyclerView recyclerView;
    private Realm realm;
    private EntityAdapter entityAdapter;
    private ProgressBar progressBar;
    private List<Entity> entities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


        if (setData().isEmpty()) {
            prepareDB();

        }
        setRecyclerView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            deleteData();
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }


    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv);

//        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        recyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(this).build());
        entityAdapter = new EntityAdapter(this, realm, results);
        recyclerView.setAdapter(entityAdapter);


        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                GenUtils.getToastMessage(MainACT.this, String.valueOf(position));

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

    private List<Entity> setData() {
        results = realm.where(Entity.class).findAll();
        return results;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prepareDB() {
        new StoreData().execute();
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

