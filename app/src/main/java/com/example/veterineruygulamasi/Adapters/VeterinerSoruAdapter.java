package com.example.veterineruygulamasi.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.veterineruygulamasi.Models.CevaplaModel;
import com.example.veterineruygulamasi.Models.SoruModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VeterinerSoruAdapter extends RecyclerView.Adapter<VeterinerSoruAdapter.ViewHolder> {

    List<SoruModel> list;
    Context context;
    Activity activity;

    public VeterinerSoruAdapter(List<SoruModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sorularitemlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.soruKullaniciText.setText(list.get(position).getKadi().toString());
        holder.soruSoruText.setText(list.get(position).getSoru().toString());
        holder.soruAramaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ara(list.get(position).getTelefon().toString());
            }
        });

        holder.soruCevaplaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cevaplaAlert(list.get(position).getMusid().toString(),list.get(position).getSoruid().toString(),
                        position,list.get(position).getSoru().toString());
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView soruKullaniciText, soruSoruText;
        ImageView soruCevaplaButon,soruAramaButon;


        public ViewHolder(View itemView) {
            super(itemView);

            soruKullaniciText = (TextView) itemView.findViewById(R.id.soruKullaniciText);
            soruSoruText = (TextView) itemView.findViewById(R.id.soruSoruText);
            soruCevaplaButon = (ImageView) itemView.findViewById(R.id.soruCevaplaButon);
            soruAramaButon = (ImageView) itemView.findViewById(R.id.soruAramaButon);



        }
    }

    public void deleteToList(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void ara(String numara)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:"+numara));
        activity.startActivity(intent);
    }

    public void cevaplaAlert(final String musid, final String soruid,  final int position,String soru)
    {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.admincevaplaalertlayout,null);

        final EditText cevaplaEditText = (EditText)view.findViewById(R.id.cevaplaEditText);
        Button cevaplaButon = (Button)view.findViewById(R.id.cevaplaButon);
        TextView cevaplanacakSoruText = view.findViewById(R.id.cevaplanacakSoruText);

        cevaplanacakSoruText.setText(soru);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

        cevaplaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cevap = cevaplaEditText.getText().toString();
                cevaplaEditText.setText("");
                alertDialog.cancel();
                cevapla(musid,soruid,cevap,alertDialog,position);
            }
        });

        alertDialog.show();
    }

    public void cevapla(String musid, String soruid, String text, final AlertDialog alertDialog, final int position)
    {
            Call<CevaplaModel> req =  ManagerAll.getInstance().cevapla(musid,soruid,text);
            req.enqueue(new Callback<CevaplaModel>() {
                @Override
                public void onResponse(Call<CevaplaModel> call, Response<CevaplaModel> response) {

                    if (response.body().isTf())
                    {
                        Toast.makeText(context, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                            deleteToList(position);
                    }else {
                        Toast.makeText(context, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();

                    }

                }

                @Override
                public void onFailure(Call<CevaplaModel> call, Throwable t) {
                    Toast.makeText(context, "HATA...!", Toast.LENGTH_SHORT).show();
                }
            });
    }

}
