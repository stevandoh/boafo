//package com.origgin.boafo.utils;
//
//import android.content.res.Resources;
//import android.util.Log;
//
//import com.origgin.boafo.R;
//import com.origgin.boafo.models.Entity;
//
//import java.io.InputStream;
//
//import io.realm.Realm;
//import io.realm.RealmResults;
//
///**
// * Created by root on 6/25/17.
// */
//
//
//public class RealmImporter {
//
//    Resources resources;
//    TransactionTime transactionTime;
//
//    public RealmImporter(Resources resources) {
//        this.resources = resources;
//    }
//
//    public  RealmResults<Entity> importFromJson(){
//        Realm realm = Realm.getDefaultInstance();
//
//        //transaction timer
//        transactionTime = new TransactionTime();
//        transactionTime.setStart(System.currentTimeMillis());
//
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                InputStream inputStream = resources.openRawResource(R.raw.data);
//                try {
//                    realm.createAllFromJson(Entity.class, inputStream);
//                    transactionTime.setEnd(System.currentTimeMillis());
//                } catch (Exception e){
//                    realm.cancelTransaction();
//                } finally {
//                    if(realm != null) {
//                        realm.close();
//                    }
//                }
//
//            }
//        });
//
//        RealmResults<Entity>  results= realm.where(Entity.class).findAll();
//
//        Log.d( "Realm","createAllFromJson Task completed in " + transactionTime.getDuration() + "ms" );
//
//        return  results;
//    }
//
//}
