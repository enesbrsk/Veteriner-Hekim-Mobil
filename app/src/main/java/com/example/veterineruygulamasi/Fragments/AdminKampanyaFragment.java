package com.example.veterineruygulamasi.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.veterineruygulamasi.Adapters.AdminKampanyaAdapter;
import com.example.veterineruygulamasi.Models.AdminKampanyaModel;
import com.example.veterineruygulamasi.Models.KampanyaEkleModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.example.veterineruygulamasi.Utils.ChangeFragments;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminKampanyaFragment extends Fragment {

    private View view;
    private RecyclerView AdminkampanyaRecView;
    private List<AdminKampanyaModel> kampanyaList;
    private AdminKampanyaAdapter kampanyaAdapter;
    private ChangeFragments changeFragments;
    private Button kampanyaEkle;
    private  ImageView kampanyaEkleImageView;
   private Bitmap bitmap = null;
    private String imageString;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.adminfragment_kampanya, container, false);
        tanimla();
        getKampanya();
        click();
        return view;
    }
    public void tanimla()
    {
        AdminkampanyaRecView = view.findViewById(R.id.AdminkampanyaRecView);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(),1);
        AdminkampanyaRecView.setLayoutManager(mng);
        kampanyaList = new ArrayList<>();
        changeFragments = new ChangeFragments(getContext());
        kampanyaEkle = view.findViewById(R.id.kampanyaEkle);
        bitmap = null;
        imageString = "";
    }

    public void click()
    {
        kampanyaEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKampanya();
            }
        });
    }
    public void getKampanya()
    {
        Call<List<AdminKampanyaModel>> req = ManagerAll.getInstance().AdmingetKampanya();
        req.enqueue(new Callback<List<AdminKampanyaModel>>() {
            @Override
            public void onResponse(Call<List<AdminKampanyaModel>> call, Response<List<AdminKampanyaModel>> response) {
                if (response.body().get(0).isTf())
                {
                    kampanyaList = response.body();
                    kampanyaAdapter = new AdminKampanyaAdapter(kampanyaList,getContext(),getActivity());
                    AdminkampanyaRecView.setAdapter(kampanyaAdapter);

                }else
                {
                    Toast.makeText(getContext(), "Herhangi Bir Kampanya Bulunmamaktadır", Toast.LENGTH_SHORT).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<AdminKampanyaModel>> call, Throwable t) {
                Toast.makeText(getContext(), "HATA", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addKampanya()
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.kampanyaeklelayout,null);

        final EditText kampanyaBaslikEdittext = view.findViewById(R.id.kampanyaBaslikEdittext);
        final EditText kampanyaIcerikText = view.findViewById(R.id.kampanyaIcerikText);
        Button kampanyaEkleButon = view.findViewById(R.id.kampanyaEkleButon);
        Button kampanyaImageEkleButon = view.findViewById(R.id.kampanyaImageEkleButon);
        kampanyaEkleImageView  = view.findViewById(R.id.kampanyaEkleImageView);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

        kampanyaImageEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galeriAc();
            }
        });

        kampanyaEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (!imageToString().equals("") && !kampanyaBaslikEdittext.getText().toString().equals("") &&
                    !kampanyaIcerikText.getText().toString().equals(""))
                    {

                        kampanyaEkle(kampanyaBaslikEdittext.getText().toString(),kampanyaIcerikText.getText().toString(),
                                imageToString());

                        kampanyaBaslikEdittext.setText("");
                        kampanyaIcerikText.setText("");
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Tüm Alanların Doldurulması Gerekmektedir", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        alertDialog.show();
    }

     void galeriAc()
     {
         Intent ıntent =  new Intent();
         ıntent.setType("image/*");
         ıntent.setAction(Intent.ACTION_GET_CONTENT);

         startActivityForResult(ıntent,777);

     }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==777 && data !=null)
        {
            Uri path = data.getData();

            try {


                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);

                kampanyaEkleImageView.setImageBitmap(bitmap);
                kampanyaEkleImageView.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public String imageToString()
    {
        if (bitmap!=null)
        {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte [] byt = byteArrayOutputStream.toByteArray();
             imageString = Base64.encodeToString(byt,Base64.DEFAULT);
            return  imageString;
        }
        else {
            return imageString;
        }

    }

    public void kampanyaEkle(String baslik, String icerik, String imageString)
    {

         Call<KampanyaEkleModel>  req = ManagerAll.getInstance().addKampanya(baslik,icerik,imageString);
         req.enqueue(new Callback<KampanyaEkleModel>() {
            @Override
            public void onResponse(Call<KampanyaEkleModel> call, Response<KampanyaEkleModel> response) {
                if (response.body().isTf())
                {
                    Toast.makeText(getContext(), response.body().getSonuc(), Toast.LENGTH_SHORT).show();
                    getKampanya();


                }
                else {

                    Toast.makeText(getContext(), response.body().getSonuc(), Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<KampanyaEkleModel> call, Throwable t) {
                Toast.makeText(getContext(), "HATA", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
