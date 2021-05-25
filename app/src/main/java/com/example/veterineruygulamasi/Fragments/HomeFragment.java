package com.example.veterineruygulamasi.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.veterineruygulamasi.Adapters.AnswersAdapter;
import com.example.veterineruygulamasi.Models.AnswersModel;
import com.example.veterineruygulamasi.Models.AskQuestionModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.example.veterineruygulamasi.Utils.ChangeFragments;
import com.example.veterineruygulamasi.Utils.GetSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    public View view;
    private LinearLayout petlerimLayout,sorusorlinearlayout,cevapLayout,kampanyaLinearLayout,asiTakipLayout
            ,sanalKarneLayout;
    private ChangeFragments changeFragments;
    private GetSharedPreferences getSharedPreferences;
    private String id;
    private AnswersAdapter answersAdapter;
    private List<AnswersModel>  answerList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        tanimla();
        action();
        return view;

    }

    public void tanimla()
    {
        sorusorlinearlayout =(LinearLayout) view.findViewById(R.id.sorusorlinearlayout);
        cevapLayout =(LinearLayout) view.findViewById(R.id.cevapLayout);
       petlerimLayout=(LinearLayout)view.findViewById(R.id.petlerimLayout);
        asiTakipLayout =(LinearLayout)view.findViewById(R.id.asiTakipLayout);
        sanalKarneLayout=(LinearLayout)view.findViewById(R.id.sanalKarneLayout);
       answerList = new ArrayList<>();
        kampanyaLinearLayout= (LinearLayout) view.findViewById(R.id.kampanyaLinearLayout);


        changeFragments = new ChangeFragments(getContext());
        getSharedPreferences = new GetSharedPreferences(getActivity());
        id = getSharedPreferences.getSession().getString("id",null);



    }
    public void action()
    {
        petlerimLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new UserPetsFragment());
            }
        });

        sorusorlinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuestionAlert();

            }
        });
        cevapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getAnswers(id);
            }
        });
        kampanyaLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new KampanyaFragment());
            }
        });
        asiTakipLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new AsiFragment());
            }
        });
        sanalKarneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new SanalKarnePetler());

            }
        });

    }

    public void openQuestionAlert()
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sorusoralertlayout,null);

        final EditText sorusoredittext = (EditText)view.findViewById(R.id.sorusoredittext);
        Button sorusorbuton = (Button)view.findViewById(R.id.sorusorbuton);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

        sorusorbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String soru = sorusoredittext.getText().toString();
               sorusoredittext.setText("");
                alertDialog.cancel();
                askQuestion(id,soru,alertDialog);
            }
        });

        alertDialog.show();
    }

    public void askQuestion(String mus_id, String text, final AlertDialog alr)
    {
       Call<AskQuestionModel> req = ManagerAll.getInstance().sorSor(mus_id,text);
       req.enqueue(new Callback<AskQuestionModel>() {
           @Override
           public void onResponse(Call<AskQuestionModel> call, Response<AskQuestionModel> response) {
               
               if (response.body().isTf())
               {
                   Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                    alr.cancel();
               }
               else 
               {
                   Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_LONG).show();

               }
           }

           @Override
           public void onFailure(Call<AskQuestionModel> call, Throwable t) {

               Toast.makeText(getContext(), "Mesaj gönderilemedi", Toast.LENGTH_SHORT).show();

           }
       });
    }
    
    public void getAnswers(String mus_id)
    {
        final Call<List<AnswersModel>> req = ManagerAll.getInstance().getAnswers(mus_id);
        req.enqueue(new Callback<List<AnswersModel>>() {
            @Override
            public void onResponse(Call<List<AnswersModel>> call, Response<List<AnswersModel>> response) {
                if (response.body().get(0).isTf())
                {
                    if (response.isSuccessful()){
                        answerList = response.body();
                        answersAdapter = new AnswersAdapter(answerList,getContext());
                        openAnswerAlert();

                    }

                }
                else {
                    Toast.makeText(getContext(), "Herhangi bir cevap bulunmamaktadır", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AnswersModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Hata Oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openAnswerAlert()
    {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.cevapalertlayout,null);

        RecyclerView cevapRecylerView  = (RecyclerView)view.findViewById(R.id.cevapRecylerView);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

      RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
      cevapRecylerView.setLayoutManager(layoutManager);
      cevapRecylerView.setAdapter(answersAdapter);

        alertDialog.show();
    }
}
