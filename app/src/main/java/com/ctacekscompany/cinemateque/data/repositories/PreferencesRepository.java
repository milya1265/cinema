package com.ctacekscompany.cinemateque.data.repositories;

import android.content.Context;

import com.ctacekscompany.cinemateque.data.datasources.MySharedPreferences;

public class PreferencesRepository {

    public MySharedPreferences sharedPreferences;

    public PreferencesRepository(Context context) {
        sharedPreferences = new MySharedPreferences(context);
    }

    public void put(String line, String content){
        sharedPreferences.put(line, content);
    }

    public String out(String line){
        return sharedPreferences.out(line);
    }

}