package com.origgin.boafo;

import android.app.Application;
import android.text.TextUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by root on 6/25/17.
 */

public class AppController extends Application {
    private static AppController singleton;

    public static final String TAG = AppController.class.getSimpleName();
    // The Realm file will be located in Context.getFilesDir() with name "default.realm"

    public static AppController getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("RealmTrial1.realm")
                .build();
        Realm.setDefaultConfiguration(config);
        init();
    }

    private void init() {
        singleton = this;
    }




}
