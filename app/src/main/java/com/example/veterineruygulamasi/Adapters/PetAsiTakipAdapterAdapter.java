package com.example.veterineruygulamasi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.veterineruygulamasi.Models.AsiOnaylaModel;
import com.example.veterineruygulamasi.Models.PetAsiTakipModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PetAsiTakipAdapterAdapter extends RecyclerView.Adapter<PetAsiTakipAdapterAdapter.ViewHolder> {

    List<PetAsiTakipModel> list;
    Context context;
    Activity activity;

    public PetAsiTakipAdapterAdapter(List<PetAsiTakipModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adminasitakiplayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.asiTakipPetName.setText(list.get(position).getPetismi());
        holder.asiTakipBilgiText.setText(list.get(position).getKadi()+" İsimli Kullanıcının" + list.get(position).getPetismi()
        +" İsimli Petinin "+ list.get(position).getAsiisim()+ " Aşısı Yapılacaktır");
        Picasso.get().load(list.get(position).getPetresim()).resize(200, 200).into(holder.asiTakipImage);
        holder.asiTakipAraButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ara(list.get(position).getTelefon().toString());
            }
        });

        holder.asiTakipOkButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asiOnayla(list.get(position).getAsiid().toString(),position);
            }
        });

        holder.asiTakipCancelButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onaylama(list.get(position).getAsiid().toString(),position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView asiTakipPetName, asiTakipBilgiText;
        ImageView asiTakipOkButon,asiTakipCancelButon,asiTakipAraButon,asiTakipImage;


        public ViewHolder(View itemView) {
            super(itemView);

            asiTakipPetName = (TextView) itemView.findViewById(R.id.asiTakipPetName);
            asiTakipBilgiText = (TextView) itemView.findViewById(R.id.asiTakipBilgiText);
            asiTakipOkButon = (ImageView) itemView.findViewById(R.id.asiTakipOkButon);
            asiTakipCancelButon = (ImageView) itemView.findViewById(R.id.asiTakipCancelButon);
            asiTakipAraButon = (ImageView) itemView.findViewById(R.id.asiTakipAraButon);
            asiTakipImage = (ImageView) itemView.findViewById(R.id.asiTakipImage);


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

    public void asiOnayla(String id, final int position)
    {
        Call<AsiOnaylaModel> req = ManagerAll.getInstance().asiOnayla(id);
        req.enqueue(new Callback<AsiOnaylaModel>() {
            @Override
            public void onResponse(Call<AsiOnaylaModel> call, Response<AsiOnaylaModel> response) {
                Toast.makeText(context, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                deleteToList(position);
            }

            @Override
            public void onFailure(Call<AsiOnaylaModel> call, Throwable t) {
                Toast.makeText(context,"HATA...!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void onaylama(String id,final int position)
    {
        Call<AsiOnaylaModel> req = ManagerAll.getInstance().asiİptal(id);
        req.enqueue(new Callback<AsiOnaylaModel>() {
            @Override
            public void onResponse(Call<AsiOnaylaModel> call, Response<AsiOnaylaModel> response) {
                Toast.makeText(context, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
                deleteToList(position);
            }

            @Override
            public void onFailure(Call<AsiOnaylaModel> call, Throwable t) {
                Toast.makeText(context,"HATA...!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
