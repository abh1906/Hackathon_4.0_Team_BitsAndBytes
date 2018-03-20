package com.example.burhan.libsearch;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by burhan on 5/3/18.
 */

public class User {

    Context context;

    public  void removeUser(){
        sharedPreferences.edit().clear().commit();
    }

    public String getName() {
        name=sharedPreferences.getString("Username","");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        sharedPreferences.edit().putString("Username",name).commit();
    }

    private String name;
    SharedPreferences sharedPreferences;

    public User(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
    }
}
