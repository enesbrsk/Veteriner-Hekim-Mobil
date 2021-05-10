package com.example.veterineruygulamasi.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.veterineruygulamasi.Adapters.SanalKarneGecmisAsiAdapter;
import com.example.veterineruygulamasi.Models.AsiModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.example.veterineruygulamasi.Utils.ChangeFragments;
import com.example.veterineruygulamasi.Utils.GetSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AsiDetayFragment extends Fragment {

    private View view;
    private String musid;
    private String petId;
    private GetSharedPreferences getSharedPreferences;
    private RecyclerView asidetayRecView;
    private SanalKarneGecmisAsiAdapter adapter;
    private List<AsiModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_asi_detay, container, false);
        tanimla();
        getGecmisAsi();
        return  view;
    }

    public void tanimla()
    {
        petId = getArguments().getString("petid").toString();
        getSharedPreferences = new GetSharedPreferences(getActivity());
        musid = getSharedPreferences.getSession().getString("id",null);
        asidetayRecView = (RecyclerView)view.findViewById(R.id.asidetayRecView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        asidetayRecView.setLayoutManager(layoutManager);
        list = new ArrayList<>();

    }

    public void getGecmisAsi()
    {
        Call<List<AsiModel>> req = ManagerAll.getInstance().getGecmisAsi(musid,petId);
        req.enqueue(new Callback<List<AsiModel>>() {
            @Override
            public void onResponse(Call<List<AsiModel>> call, Response<List<AsiModel>> response) {

                if (response.body().get(0).isTf())
                {
                    list = response.body();
                    adapter = new SanalKarneGecmisAsiAdapter(list,getContext());
                    asidetayRecView.setAdapter(adapter);

                }
                else
                {
                    ChangeFragments changeFragments = new ChangeFragments(getContext());
                    changeFragments.change(new SanalKarnePetler());
                    Toast.makeText(getContext(), "Geçmişte Yapılan Aşı Bulunmamaktadır...", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<AsiModel>> call, Throwable t) {

            }
        });
    }
}
