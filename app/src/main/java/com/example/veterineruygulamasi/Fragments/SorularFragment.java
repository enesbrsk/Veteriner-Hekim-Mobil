package com.example.veterineruygulamasi.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.veterineruygulamasi.Adapters.VeterinerSoruAdapter;
import com.example.veterineruygulamasi.Models.SoruModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.example.veterineruygulamasi.Utils.ChangeFragments;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SorularFragment extends Fragment {

    private View view;
    private RecyclerView soruRecylerView;
    private List<SoruModel> list;
    private VeterinerSoruAdapter veterinerSoruAdapter;
    private ChangeFragments changeFragments;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sorular, container, false);
        tanimla();
        istekAt();
        return view;

    }

    public void tanimla()
    {
        soruRecylerView = view.findViewById(R.id.soruRecylerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        soruRecylerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        changeFragments = new ChangeFragments(getContext());
    }
    public void istekAt()
    {
        Call<List<SoruModel>> req = ManagerAll.getInstance().getSoru();
        req.enqueue(new Callback<List<SoruModel>>() {
            @Override
            public void onResponse(Call<List<SoruModel>> call, Response<List<SoruModel>> response) {
                if (response.body().get(0).isTf())
                {
                    list = response.body();
                    veterinerSoruAdapter = new VeterinerSoruAdapter(list,getContext(),getActivity());
                    soruRecylerView.setAdapter(veterinerSoruAdapter);
                }
                else {
                    Toast.makeText(getContext(), "Veteriner Hekime Sorulan Soru Bulunmamaktadır", Toast.LENGTH_SHORT).show();
                    changeFragments.change(new AdminHomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<SoruModel>> call, Throwable t) {
                Toast.makeText(getContext(), "HATA...!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
