package com.origgin.boafo.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import com.origgin.boafo.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            deleteData();
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Element adsElement = new Element();

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.origgin_logo)
                .setDescription("Boafo is developed by Origgin Business Solutions Ltd, a subsidiary of Origgin Group. Origgin Business Solutions provides mobile,  website, software development services and e-business solutions for businesses and organizations.")
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("Connect with us")
                .addEmail("info@origgin.net")
                .addWebsite("http://origgin.net/")
                .addFacebook("OrigginLtd")
//                .addItem(getCopyRightsElement())
                .create();

        setContentView(aboutPage);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_seller_list_act, menu);
//
//        return true;
        return super.onCreateOptionsMenu(menu);
    }

}
