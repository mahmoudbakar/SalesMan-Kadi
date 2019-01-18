package com.undecode.salesman.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.undecode.salesman.models.LoginResponse;

/**
 * Created by mahmo on 4/9/2018.
 */

public class MyPreferance
{
    Context context;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    private static MyPreferance instance;

    public static MyPreferance getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new MyPreferance(context);
        }
        return instance;
    }

    public static MyPreferance getInstance()
    {
        return instance;
    }

    public MyPreferance(Context context)
    {
        this.context = context;
        preferences = context.getSharedPreferences("SalesMan", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setLang(String lang)
    {
        editor.putString("lang", lang);
        editor.commit();
    }

    public String getLang()
    {
        return preferences.getString("lang", "en");
    }

    public int getIntLang()
    {
        int lang = 1;
        if (getLang().equals("en"))
        {
            lang = 2;
        }else if (getLang().equals("ar"))
        {
            lang = 1;
        }
        return lang;
    }

//    public void login(LoginResponse loginResponse)
//    {
//        editor.putString(Constants.KEY.TOKEN, id);
//        editor.putString(Constants.KEY.ID, user);
//        editor.putString(Constants.KEY.PASSWORD, password);
//        editor.putString(Constants.KEY.Phone1, password);
//        editor.putString(Constants.KEY.Phone2, password);
//        editor.putString(Constants.KEY.Phone3, password);
//        editor.putString(Constants.KEY.PASSWORD, password);
//        editor.putString(Constants.KEY.PASSWORD, password);
//        editor.putString(Constants.KEY.PASSWORD, password);
//        editor.commit();
//    }
//
//    public void logout()
//    {
//        editor.remove(Constants.KEY.TOKEN);
//        editor.remove(Constants.KEY.ID);
//        editor.remove(Constants.KEY.PHONE);
//        editor.commit();
//    }

//    public void setNotification(boolean state)
//    {
//        editor.putBoolean("isNotificationActive", state);
//        editor.commit();
//    }
//
//    public void setSound(boolean state)
//    {
//        editor.putBoolean("isSoundActive", state);
//        editor.commit();
//    }
//
//    public boolean getSound()
//    {
//        return preferences.getBoolean("isSoundActive", true);
//    }
//
//    public boolean getNotifiction()
//    {
//        return preferences.getBoolean("isNotificationActive", true);
//    }
//
//    public void setClicks(String day)
//    {
//        int clicks = preferences.getInt("clicks", 0) + 1;
//        editor.putInt("clicks", clicks);
//        editor.putString("day",day);
//        editor.commit();
//    }
//
//    public boolean haveAds(String day)
//    {
//        boolean haveAds;
//        if (day.equals(preferences.getString("day", day)))
//        {
//            haveAds = preferences.getInt("clicks", 0) <= 2;
//        }else
//        {
//            editor.putInt("clicks", 0);
//            editor.putString("day",day);
//            editor.commit();
//            haveAds = true;
//        }
//        return haveAds;
//    }

}
