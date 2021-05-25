package com.example.veterineruygulamasi.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;


import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.Utils.ChangeFragments;


public class AdminHomeFragment extends Fragment {

    private LinearLayout kampanyaLayout,asiTakipLayout,soruLayotu,kullanicilerLayout;
    private View view;
    private ChangeFragments changeFragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.adminfragment_home, container, false);
        tanimla();
        clickToLayout();
        return view;

    }

    public void tanimla()
    {
        kullanicilerLayout =view.findViewById(R.id.kullanicilerLayout);
        soruLayotu=view.findViewById(R.id.soruLayotu);
        kampanyaLayout=view.findViewById(R.id.kampanyaLayout);
        asiTakipLayout=view.findViewById(R.id.asiTakipLayout);
        changeFragments = new ChangeFragments(getContext());
    }



    public void clickToLayout()
    {
        kampanyaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new AdminKampanyaFragment());
            }
        });
      asiTakipLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new AsiTakipFragment());
            }
        });
        soruLayotu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new SorularFragment());
            }
        });
        kullanicilerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new KullanicilerFragment());
            }
        });
    }
}
