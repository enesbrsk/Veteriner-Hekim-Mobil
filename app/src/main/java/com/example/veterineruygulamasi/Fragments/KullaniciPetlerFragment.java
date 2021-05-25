package com.example.veterineruygulamasi.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.veterineruygulamasi.Adapters.AdminPetAdapter;
import com.example.veterineruygulamasi.Models.PetAdminModel;
import com.example.veterineruygulamasi.Models.PetEkle;
import com.example.veterineruygulamasi.Models.PetModel;
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

public class KullaniciPetlerFragment extends Fragment {

    private View view;
    private String musid;
    private ChangeFragments changeFragments;
    private RecyclerView userPetListRecylerView;
   private ImageView petEkleResimYok;
    private TextView petEkleUyarıText;
    private  Button userPetEkle;
    private List<PetAdminModel> list;
    private AdminPetAdapter adminPetAdapter;
    private  ImageView petEkleImageView;
    Bitmap bitmap ;
    private String imageString="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_kullanici_petler, container, false);
        tanimla();
        getPets(musid);
        click();
        return view;
    }

    public void tanimla() {
        musid = getArguments().get("userid").toString();
        Log.i("gelenmusid", musid);
        changeFragments = new ChangeFragments(getContext());

        userPetListRecylerView  = view.findViewById(R.id.userPetListRecylerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        userPetListRecylerView.setLayoutManager(layoutManager);
        petEkleResimYok = view.findViewById(R.id.petEkleResimYok);
        petEkleUyarıText = view.findViewById(R.id.petEkleUyarıText);
        userPetEkle = view.findViewById(R.id.userPetEkle);
        list = new ArrayList<>();
        bitmap = null;
    }

    public void click()
    {
        userPetEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petEkleAlert();

            }
        });
    }

    public void getPets(String id) {
        final Call<List<PetAdminModel>> req = ManagerAll.getInstance().getPetsAdmin(id);
        req.enqueue(new Callback<List<PetAdminModel>>() {
            @Override
            public void onResponse(Call<List<PetAdminModel>> call, Response<List<PetAdminModel>> response) {
                if (response.body().get(0).isTf()) {
                    userPetListRecylerView.setVisibility(View.VISIBLE);
                    petEkleResimYok.setVisibility(View.GONE);
                    petEkleUyarıText.setVisibility(View.GONE);
                    list = response.body();
                    adminPetAdapter = new AdminPetAdapter(list,getContext(),getActivity(),musid);
                    userPetListRecylerView.setAdapter(adminPetAdapter);
                    Toast.makeText(getContext(), "Kullanıcıya Ait "+response.body().size()+" Tane Pet Vardır", Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(getContext(), "Kullanıcıya Ait Pet Bulunamamıştır...!", Toast.LENGTH_LONG).show();
                    petEkleResimYok.setVisibility(View.VISIBLE);
                    petEkleUyarıText.setVisibility(View.VISIBLE);
                    userPetListRecylerView.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<List<PetAdminModel>> call, Throwable t) {
                Toast.makeText(getContext(), "HATA...!", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void petEkleAlert()
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.peteklelayout,null);

        final EditText petEkleNameEditText = view.findViewById(R.id.petEkleNameEditText);
        final EditText petEkleTurEditText = view.findViewById(R.id.petEkleTurEditText);
        final EditText petEkleCinsEditText = view.findViewById(R.id.petEkleCinsEditText);
        Button petEkleEkleButon = view.findViewById(R.id.petEkleEkleButon);
        Button petEkleResimSeButon = view.findViewById(R.id.petEkleResimSeButon);
        petEkleImageView  = view.findViewById(R.id.petEkleImageView);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

        petEkleResimSeButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galeriAc();
            }
        });

        petEkleEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!imageToString().equals("") && !petEkleNameEditText.getText().toString().equals("") &&
                        !petEkleTurEditText.getText().toString().equals("") &&
                        !petEkleCinsEditText.getText().toString().equals(""))
                {

                    petEkle(musid,petEkleNameEditText.getText().toString(),petEkleTurEditText.getText().toString(),
                            petEkleCinsEditText.getText().toString(),
                            imageToString(),alertDialog);

                    petEkleNameEditText.setText("");
                    petEkleTurEditText.setText("");
                    petEkleCinsEditText.setText("");
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==777 && data !=null)
        {
            Uri path = data.getData();

            try {


                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);

                petEkleImageView.setImageBitmap(bitmap);
                petEkleImageView.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public void petEkle(final String musid, String petismi, String pettur, String petcins, String imageString , final AlertDialog alertDialog)
    {
            Call<PetEkle> req = ManagerAll.getInstance().petEkle(musid,petismi,pettur,petcins,imageString);
            req.enqueue(new Callback<PetEkle>() {
                @Override
                public void onResponse(Call<PetEkle> call, Response<PetEkle> response) {
                    if (response.body().isTf())
                    {

                        getPets(musid);
                        Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                    else
                    {
                        Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                }

                @Override
                public void onFailure(Call<PetEkle> call, Throwable t) {
                    Toast.makeText(getContext(), "HATA....!!", Toast.LENGTH_SHORT).show();

                }
            });
    }
}
