package com.origgin.boafo.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.origgin.boafo.R;
import com.origgin.boafo.models.FavoriteItem;
import com.origgin.boafo.ui.adapter.CategoryAdapter;

public class MenuACT extends AppCompatActivity {
    Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////            deleteData();
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }
        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.boafo_logo)
                .build();

//        new DrawerBuilder().withActivity(this).build();
        SecondaryDrawerItem item1 = new SecondaryDrawerItem()
                .withIdentifier(1)
                .withName(R.string.services);
        SecondaryDrawerItem item2 = new SecondaryDrawerItem()
                .withIdentifier(2)
                .withName(R.string.location_services);
        SecondaryDrawerItem item3 = new SecondaryDrawerItem()
                .withIdentifier(3)
                .withName(R.string.my_favorites);
        SecondaryDrawerItem item4 = new SecondaryDrawerItem()
                .withIdentifier(4)
                .withName(R.string.about);


        drawer =  new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem. getIdentifier() == 1) {
                                intent = new Intent(MenuACT.this, CategoryListACT.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(MenuACT.this, LocationServicesACT.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(MenuACT.this, FavouriteActivity.class);
                            }  else if (drawerItem.getIdentifier() == 4) {
                            intent = new Intent(MenuACT.this, AboutActivity.class);
                        }
                            if (intent != null) {
                                MenuACT.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .build();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        if (item.getItemId() == android.R.id.home) {
//            finish();
//        }
        return super.onOptionsItemSelected(item);
    }


}
