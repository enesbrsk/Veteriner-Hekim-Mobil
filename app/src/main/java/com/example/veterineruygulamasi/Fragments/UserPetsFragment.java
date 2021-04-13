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

import com.example.veterineruygulamasi.Adapters.PetAdapter;
import com.example.veterineruygulamasi.Models.PetModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.example.veterineruygulamasi.Utils.ChangeFragments;
import com.example.veterineruygulamasi.Utils.GetSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPetsFragment extends Fragment {

    View view;
    private RecyclerView petlistrecylerview;
    private PetAdapter petAdapter;
    private List<PetModel> petList;
    private ChangeFragments changeFragments;
    private String mus_id;
    private GetSharedPreferences getSharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_pets, container, false);
        tanimla();
        getPets(mus_id);

        return view;
    }

    public void tanimla(){
        petList = new ArrayList<>();
        petlistrecylerview = view.findViewById(R.id.petlistrecylerview);
        RecyclerView.LayoutManager mng = new GridLayoutManager(getContext(),1);
        petlistrecylerview.setLayoutManager(mng);
        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        mus_id = getSharedPreferences.getSession().getString("id",null);
    }

    public void getPets(String mus_id)
    {
        Call<List<PetModel>> req= ManagerAll.getInstance().getPets(mus_id);
        req.enqueue(new Callback<List<PetModel>>() {
            @Override
            public void onResponse(Call<List<PetModel>> call, Response<List<PetModel>> response) {
                if (response.body().get(0).isTf())
                {
                    petList = response.body();
                    petAdapter = new PetAdapter(petList,getContext());
                    petlistrecylerview.setAdapter(petAdapter);




                }else {
                    Toast.makeText(getContext(), "Sistemde pet bulunmamaktadır", Toast.LENGTH_LONG).show();
                    changeFragments.change(new HomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<PetModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Hata", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
