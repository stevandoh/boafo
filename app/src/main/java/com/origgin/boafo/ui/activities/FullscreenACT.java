package com.origgin.boafo.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;
import com.crashlytics.android.Crashlytics;
import com.origgin.boafo.R;

import io.fabric.sdk.android.Fabric;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenACT extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_fullscreen);
        View easySplashScreenView = new EasySplashScreen(FullscreenACT.this)
                .withFullScreen()
                .withTargetActivity(MenuACT.class)
                .withSplashTimeOut(3000)
                .withBackgroundResource(R.color.colorPrimaryLight)
                .withFooterText("Copyright 2017")
                .withLogo(R.drawable.boafo_logo)
                .create();

        setContentView(easySplashScreenView);
    }




}
