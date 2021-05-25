package com.example.veterineruygulamasi.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.veterineruygulamasi.Fragments.AdminHomeFragment;
import com.example.veterineruygulamasi.Fragments.HomeFragment;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.Utils.ChangeFragments;
import com.example.veterineruygulamasi.Utils.GetSharedPreferences;

public class MainAdminActivity extends AppCompatActivity {

        private SharedPreferences getSharedPreferences;
        private GetSharedPreferences preferences;
        private Button anasayfaAdminButon;
        private  ChangeFragments changeFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        getFragment();
        tanimla();
        kontrol();
        action();


    }

    private void getFragment() {
        changeFragments  = new ChangeFragments(MainAdminActivity.this);
        changeFragments.change(new AdminHomeFragment());
    }


    public void action(){
        anasayfaAdminButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new AdminHomeFragment());
            }
        });
    }

    public void tanimla(){
        preferences = new GetSharedPreferences(MainAdminActivity.this);
        getSharedPreferences= preferences.getSession();

        anasayfaAdminButon = (Button)findViewById(R.id.anasayfaAdminButon);

    }
    public void kontrol(){

        Log.i("sahare",getSharedPreferences.getString("username",null));
        if (getSharedPreferences.getString("id",null)==null &&
                getSharedPreferences.getString("username",null)==null &&
                getSharedPreferences.getString("mailadres",null)==null){
            Intent intent = new Intent(MainAdminActivity.this,GirisYapAdminActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
