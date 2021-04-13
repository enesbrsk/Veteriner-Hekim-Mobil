package com.example.veterineruygulamasi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineruygulamasi.Models.AnswersModel;
import com.example.veterineruygulamasi.Models.DeleteAnswerModel;
import com.example.veterineruygulamasi.Models.KampanyaModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.veterineruygulamasi.R.layout.cevapitemlayout;
import static com.example.veterineruygulamasi.R.layout.kampanyaitemlayout;

public class KampanyaAdapter extends RecyclerView.Adapter<KampanyaAdapter.ViewHolder>{

    List<KampanyaModel> list;
    Context context;

    public KampanyaAdapter(List<KampanyaModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(kampanyaitemlayout,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.kampanyaBaslikText.setText(list.get(position).getBaslik().toString());
        holder.kampanyatext.setText(list.get(position).getText().toString());
        Picasso.get().load(list.get(position).getResim()).into(holder.kampanyaImageView);


    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView kampanyatext,kampanyaBaslikText;
        ImageView kampanyaImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            kampanyatext = (TextView) itemView.findViewById(R.id.kampanyatext);
            kampanyaBaslikText =(TextView) itemView.findViewById(R.id.kampanyaBaslikText);
            kampanyaImageView =(ImageView) itemView.findViewById(R.id.kampanyaImageView);




        }
    }
}
