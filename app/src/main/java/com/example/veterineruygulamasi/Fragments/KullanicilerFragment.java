package com.example.veterineruygulamasi.Fragments;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.veterineruygulamasi.Adapters.UserAdapter;
import com.example.veterineruygulamasi.Models.KullanicilerModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.example.veterineruygulamasi.Utils.ChangeFragments;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KullanicilerFragment extends Fragment {

    private View view;
    private ChangeFragments changeFragments;
    private RecyclerView kullaniciRecylerView;
    private List<KullanicilerModel> list;
    private UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kullaniciler, container, false);
        tanimla();
        getKullaniciler();
        return view;
    }

    public void tanimla()
    {
        changeFragments = new ChangeFragments(getContext());
        kullaniciRecylerView = view.findViewById(R.id.kullaniciRecylerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        kullaniciRecylerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();

    }

    public void getKullaniciler()
    {
        Call<List<KullanicilerModel>> req = ManagerAll.getInstance().getKullaniciler();
        req.enqueue(new Callback<List<KullanicilerModel>>() {
            @Override
            public void onResponse(Call<List<KullanicilerModel>> call, Response<List<KullanicilerModel>> response) {
                if(response.body().get(0).isTf())
                {
                    list = response.body();
                    userAdapter = new UserAdapter(list,getContext(),getActivity());
                    Log.i("kullaniciler",response.body().toString());
                    kullaniciRecylerView.setAdapter(userAdapter);
                }else
                {
                    Toast.makeText(getContext(), "Sisteme Kayıtlı Kullanıcı Bulunmamaktadır...!", Toast.LENGTH_LONG).show();
                    changeFragments.change(new AdminHomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<KullanicilerModel>> call, Throwable t) {
                Toast.makeText(getContext(), "HATAA:...!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
