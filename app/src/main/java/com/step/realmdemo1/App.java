package com.step.realmdemo1;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {


    private void realmInit(){
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    public void onCreate() {
        realmInit();
        super.onCreate();
    }
}
