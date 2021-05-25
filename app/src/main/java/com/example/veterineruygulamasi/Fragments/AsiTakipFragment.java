package com.example.veterineruygulamasi.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.veterineruygulamasi.Adapters.PetAsiTakipAdapterAdapter;
import com.example.veterineruygulamasi.Models.PetAsiTakipModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.example.veterineruygulamasi.Utils.ChangeFragments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AsiTakipFragment extends Fragment {

    private View view;
    private DateFormat format;
    private Date date;
    private String today;
    private ChangeFragments changeFragments;
    private RecyclerView asiTakipAdminRecylerView;
    private List<PetAsiTakipModel> list;
    private PetAsiTakipAdapterAdapter petAsiTakipAdapterAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_asi_takip, container, false);
        tanimla();
        istekAt(today);
        return view;
    }

    public void tanimla( )
    {
        format = new SimpleDateFormat("dd/MM/yyyy");
        date = Calendar.getInstance().getTime();
      today = format.format(date);
        asiTakipAdminRecylerView=(RecyclerView)view.findViewById(R.id.asiTakipAdminRecylerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        asiTakipAdminRecylerView.setLayoutManager(layoutManager);
        changeFragments = new ChangeFragments(getContext());
        list = new ArrayList<>();

    }

    public void istekAt(String tarih)
    {
        Call<List<PetAsiTakipModel>> req = ManagerAll.getInstance().getAsiPet(tarih);
        req.enqueue(new Callback<List<PetAsiTakipModel>>() {
            @Override
            public void onResponse(Call<List<PetAsiTakipModel>> call, Response<List<PetAsiTakipModel>> response) {
                if (response.body().get(0).isTf())
                {
                    Toast.makeText(getContext(), "Bugün"+ response.body().size() + " Pete Aşı Yapılacaktır", Toast.LENGTH_SHORT).show();
                    list = response.body();

                    petAsiTakipAdapterAdapter = new PetAsiTakipAdapterAdapter(list,getContext(),getActivity());
                    asiTakipAdminRecylerView.setAdapter(petAsiTakipAdapterAdapter);
                }
                else {
                    Toast.makeText(getContext(), "Bugün Aşı Yapılacak Pet Bulunmamaktadır", Toast.LENGTH_SHORT).show();
                    changeFragments.change(new AdminHomeFragment());
                }
            }

            @Override
            public void onFailure(Call<List<PetAsiTakipModel>> call, Throwable t) {
                Toast.makeText(getContext(), "HATA!!!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
