package com.undecode.salesman;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.undecode.salesman.models.LoginResponse;
import com.undecode.salesman.utils.network.API;
import com.undecode.salesman.utils.network.OnDataReady;

import io.realm.Realm;
import io.realm.RealmResults;

public class SplashActivity extends AppCompatActivity
{
    Context context;
    Realm realm;
    RealmResults<LoginResponse> loginResponses;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;

        realm = Realm.getDefaultInstance();
        loginResponses = realm.where(LoginResponse.class).findAll();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        int secondsDelayed = 4;
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                if (loginResponses.size() > 0)
                {
                    API.getInstance().login(context, loginResponses.get(0).getUser(), loginResponses.get(0).getPassword(), new OnDataReady.ObjectReady() {
                        @Override
                        public void onObjectReady(Object object)
                        {
                            if (object != null)
                            {
                                try
                                {
                                    LoginResponse loginResponse = ((LoginResponse) object);
                                    if (loginResponse.getAccessToken().length() > 3)
                                    {
                                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else
                                    {
                                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }catch (ClassCastException e)
                                {
                                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onError(Object object)
                        {

                        }
                    }, false);
                }else
                {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, secondsDelayed * 1000);

//        API.getInstance().getItems(this, new OnDataReady.ArrayReady() {
//            @Override
//            public void onArrayReady(List list)
//            {
//                ArrayList<Item> items = new ArrayList<>();
//                items.addAll(list);
//            }
//
//            @Override
//            public void onError(Object object) {
//                Toast.makeText(SplashActivity.this, "Error Loading Data.", Toast.LENGTH_SHORT).show();
//            }
//        });
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
    }
}
