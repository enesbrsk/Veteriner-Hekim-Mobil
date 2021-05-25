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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineruygulamasi.Fragments.KullaniciPetlerFragment;
import com.example.veterineruygulamasi.Fragments.KullanicilerFragment;
import com.example.veterineruygulamasi.Models.AdminKampanyaModel;
import com.example.veterineruygulamasi.Models.KampanyaSilModel;
import com.example.veterineruygulamasi.Models.KullanicilerModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.example.veterineruygulamasi.Utils.ChangeFragments;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<KullanicilerModel> list;
    Context context;
    Activity activity;
    ChangeFragments changeFragments;

    public UserAdapter(List<KullanicilerModel> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
        changeFragments = new ChangeFragments(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.kullaniciitemlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.kullanciNameText.setText(list.get(position).getKadi().toString());
        holder.userAramaYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ara(list.get(position).getTelefon());
            }
        });
        holder.userPetlerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.changeParametre2(new KullaniciPetlerFragment(),list.get(position).getId().toString());
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kullanciNameText;
        Button userPetlerButon,userAramaYap;
        CardView userCardView;

        public ViewHolder(View itemView) {
            super(itemView);

            kullanciNameText = (TextView) itemView.findViewById(R.id.kullanciNameText);
            userAramaYap = (Button) itemView.findViewById(R.id.userAramaYap);
            userPetlerButon = (Button) itemView.findViewById(R.id.userPetLists);
            userCardView = itemView.findViewById(R.id.userCardView);


        }
    }

    public void ara(String numara)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:"+numara));
        activity.startActivity(intent);
    }


}
