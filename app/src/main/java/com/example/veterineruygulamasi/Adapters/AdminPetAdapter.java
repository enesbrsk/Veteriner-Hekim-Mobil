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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineruygulamasi.Fragments.KullaniciPetlerFragment;
import com.example.veterineruygulamasi.Models.AsiEkle;
import com.example.veterineruygulamasi.Models.KullanicilerModel;
import com.example.veterineruygulamasi.Models.PetAdminModel;
import com.example.veterineruygulamasi.Models.PetModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.example.veterineruygulamasi.Utils.ChangeFragments;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminPetAdapter extends RecyclerView.Adapter<AdminPetAdapter.ViewHolder> {

    List<PetAdminModel> list;
    Context context;
    Activity activity;
    ChangeFragments changeFragments;
    String musid;
    String tarih = "";

    public AdminPetAdapter(List<PetAdminModel> list, Context context, Activity activity,String musid) {
        this.list = list;
        this.context = context;
        this.activity = activity;
        this.musid = musid;
        changeFragments = new ChangeFragments(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.userpetitemlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

            holder.petNameText.setText(list.get(position).getPetisim().toString());
            holder.petBilgiText.setText("Bu Petin Türü "+ list.get(position).getPettur().toString() +
                    " Cinsi "+ list.get(position).getPetcins().toString() + " dur. " + list.get(position).getPetisim().toString()
            +" İsimli Pete Aşı Eklemek İçin Tıklayınız..");

        Picasso.get().load(list.get(position).getPetresim().toString()).resize(200, 200).into(holder.petImage);


        holder.petAsiEkleLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addAsiEkle(list.get(position).getPetid().toString());
                }
            });



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView petBilgiText,petNameText;

        LinearLayout petAsiEkleLayout;
        ImageView petImage;

        public ViewHolder(View itemView) {
            super(itemView);

            petBilgiText = (TextView) itemView.findViewById(R.id.petBilgiText);
            petNameText = (TextView) itemView.findViewById(R.id.petNameText);

            petImage = (ImageView) itemView.findViewById(R.id.petImage);
            petAsiEkleLayout = itemView.findViewById(R.id.petAsiEkleLayout);


        }
    }
    public void addAsiEkle(final String petId)
    {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.asieklelayout,null);

        CalendarView calendarView = view.findViewById(R.id.asiEkleTakvim);
        final EditText asiEkleAsiName = view.findViewById(R.id.asiEkleAsiName);
        Button asiEkleButon = view.findViewById(R.id.asiEkleButon);



        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(view);
        alert.setCancelable(true);
        final AlertDialog alertDialog = alert.create();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                tarih = dayOfMonth+"/ "+ (month+1)+"/ "+ year;
                Toast.makeText(context, tarih, Toast.LENGTH_SHORT).show();
            }
        });
        asiEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tarih.equals("") && !asiEkleAsiName.getText().toString().equals("")){
                    addAsi(musid,petId,asiEkleAsiName.getText().toString(),tarih,alertDialog);
                    
                }
                else {
                    Toast.makeText(context, "Lütfen Boş Alan Bırakmayınız ..!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        alertDialog.show();
    }

    public void addAsi(String musid, String petid, String asiName, String tarih, final AlertDialog alertDialog)
    {
        Call<AsiEkle> req = ManagerAll.getInstance().asiEkle(musid,petid,asiName,tarih);
        req.enqueue(new Callback<AsiEkle>() {
            @Override
            public void onResponse(Call<AsiEkle> call, Response<AsiEkle> response) {
                
                alertDialog.cancel();
                Toast.makeText(context, response.body().getText().toString(), Toast.LENGTH_SHORT).show();
           
            }

            @Override
            public void onFailure(Call<AsiEkle> call, Throwable t) {
                Toast.makeText(context, "HATA....!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
