package com.undecode.salesman;

import android.app.Application;

import com.undecode.salesman.utils.MyPreferance;
import com.undecode.salesman.utils.network.BakarRequests;

import io.realm.Realm;


public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        //MultiDex.install(this);
        Realm.init(this);
        BakarRequests.getInstance(this);
        MyPreferance.getInstance(this);
    }
}
