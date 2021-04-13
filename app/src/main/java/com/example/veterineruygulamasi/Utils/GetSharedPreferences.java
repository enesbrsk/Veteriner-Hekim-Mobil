package com.example.veterineruygulamasi.Utils;

import android.app.Activity;

import android.content.SharedPreferences;

import com.example.veterineruygulamasi.Activities.GirisYapActivity;

public class GetSharedPreferences {
    private   SharedPreferences sharedPreferences;
        private Activity activity;

    public GetSharedPreferences(Activity activity) {
        this.activity=activity;

    }


    public  SharedPreferences getSession(){

        sharedPreferences = activity.getApplicationContext().getSharedPreferences("session",0);
        return sharedPreferences;
    }
    public void setSession(String id,String username,String mailAdres){
        sharedPreferences = activity.getApplicationContext().getSharedPreferences("session",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("username",username);
        editor.putString("mailadres",mailAdres);
        editor.commit();

    }
}
