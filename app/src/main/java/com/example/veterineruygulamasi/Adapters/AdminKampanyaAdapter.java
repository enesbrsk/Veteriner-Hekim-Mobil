package com.example.veterineruygulamasi.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.veterineruygulamasi.Models.AdminKampanyaModel;
import com.example.veterineruygulamasi.Models.KampanyaSilModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminKampanyaAdapter extends RecyclerView.Adapter<AdminKampanyaAdapter.ViewHolder> {

    List<AdminKampanyaModel> list;
    Context context;
    Activity activity;

    public AdminKampanyaAdapter(List<AdminKampanyaModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adminkampanyaitemlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.AdminkampanyaBaslikText.setText(list.get(position).getBaslik().toString());
        holder.Adminkampanyatext.setText(list.get(position).getText().toString());
        Picasso.get().load(list.get(position).getResim().toString()).resize(200, 200).into(holder.AdminkampanyaImageView);

        holder.AdminkampanyaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kampanyaSil(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Adminkampanyatext, AdminkampanyaBaslikText;
        ImageView AdminkampanyaImageView;
        CardView AdminkampanyaCardView;

        public ViewHolder(View itemView) {
            super(itemView);

            Adminkampanyatext = (TextView) itemView.findViewById(R.id.Adminkampanyatext);
            AdminkampanyaBaslikText = (TextView) itemView.findViewById(R.id.AdminkampanyaBaslikText);
            AdminkampanyaImageView = (ImageView) itemView.findViewById(R.id.AdminkampanyaImageView);
            AdminkampanyaCardView = itemView.findViewById(R.id.AdminkampanyaCardView);


        }
    }

    public void kampanyaSil(final int position) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.kampanyasillayout, null);

        Button kampanyaSilTamam = view.findViewById(R.id.kampanyaSilTamam);
        final Button kampanyaSilIptal = view.findViewById(R.id.kampanyaSilIptal);


        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

        kampanyaSilTamam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kampanyaSil(list.get(position).getId().toString(),position);
                alertDialog.cancel();
            }
        });
        kampanyaSilIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        alertDialog.show();
    }

    public void kampanyaSil(String id, final int position) {
        Call<KampanyaSilModel> req = ManagerAll.getInstance().kampanyaSil(id);
        req.enqueue(new Callback<KampanyaSilModel>() {
            @Override
            public void onResponse(Call<KampanyaSilModel> call, Response<KampanyaSilModel> response) {
                if (response.body().isTf()) {
                    Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                    deleteToList(position);
                } else {
                    Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KampanyaSilModel> call, Throwable t) {
                Toast.makeText(context, "HATA OLUSTU", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteToList(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
