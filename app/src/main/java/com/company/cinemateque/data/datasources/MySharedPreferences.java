package com.company.cinemateque.data.datasources;

import android.content.Context;

public class MySharedPreferences {
    private final android.content.SharedPreferences sharedPreferences;

    private final android.content.SharedPreferences.Editor editor;

    public static final String PREFERENCE_NAME = "MY_APP_PREF";


    public MySharedPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void put(String line, String content){
        editor.putString(line, content);
        editor.apply();
    }

    public String out(String line){
        return sharedPreferences.getString(line, "");
    }
}
