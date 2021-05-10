package com.example.veterineruygulamasi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.veterineruygulamasi.Fragments.HomeFragment;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.Utils.ChangeFragments;
import com.example.veterineruygulamasi.Utils.GetSharedPreferences;

public class MainActivity extends AppCompatActivity {

        private SharedPreferences getSharedPreferences;
        private GetSharedPreferences preferences;
        private Button anasayfaButon;
        private  ChangeFragments changeFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragment();
        tanimla();
        kontrol();
        action();


    }

    private void getFragment() {
        changeFragments  = new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());
    }


    public void action(){
        anasayfaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new HomeFragment());
            }
        });
    }

    public void tanimla(){
        preferences = new GetSharedPreferences(MainActivity.this);
        getSharedPreferences= preferences.getSession();

        anasayfaButon = (Button)findViewById(R.id.anasayfaButon);

    }
    public void kontrol(){

        Log.i("sahare",getSharedPreferences.getString("username",null));
        if (getSharedPreferences.getString("id",null)==null &&
                getSharedPreferences.getString("username",null)==null &&
                getSharedPreferences.getString("mailadres",null)==null){
            Intent intent = new Intent(MainActivity.this,GirisYapActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
