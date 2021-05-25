package com.example.veterineruygulamasi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.veterineruygulamasi.Models.LoginModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.example.veterineruygulamasi.Utils.ChangeFragments;
import com.example.veterineruygulamasi.Utils.GetSharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GirisYapAdminActivity extends AppCompatActivity {

    private EditText loginMailAdressAdmin,loginPasswordAdmin;
    private TextView loginText;
    private Button loginButtonAdmin;
    private ChangeFragments changeFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap_admin);
        tanimla();
        click();
    }

    public void tanimla()
    {
        loginMailAdressAdmin=(EditText)findViewById(R.id.loginMailAdressAdmin);
        loginPasswordAdmin=(EditText)findViewById(R.id.loginPasswordAdmin);
        loginText=(TextView)findViewById(R.id.loginText);
        loginButtonAdmin=(Button)findViewById(R.id.loginButtonAdmin);



    }

    public void click(){

        loginButtonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail= loginMailAdressAdmin.getText().toString();
                String pass = loginPasswordAdmin.getText().toString();
                login(mail,pass);
                delete();
            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GirisYapAdminActivity.this,KayitOlActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void delete(){
        loginMailAdressAdmin.setText("");
        loginPasswordAdmin.setText("");
    }

    public void login(String mailAdres,String parola){
        Call<LoginModel> req= ManagerAll.getInstance().girisYapAdmin(mailAdres,parola);

        req.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body().isTf())
                {
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(GirisYapAdminActivity.this,MainAdminActivity.class);
                    GetSharedPreferences getSharedPreferences = new GetSharedPreferences(GirisYapAdminActivity.this);
                    getSharedPreferences.setSession(response.body().getId(),response.body().getUsername(),response.body().getMailadres());
                    startActivity(intent);
                    finish();


                }else {
                    Toast.makeText(getApplicationContext(),response.body().getText(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(GirisYapAdminActivity.this, "Lütfen Tekrar Deneyiniz", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
