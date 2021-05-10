package com.example.veterineruygulamasi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineruygulamasi.Fragments.AsiDetayFragment;
import com.example.veterineruygulamasi.Models.AsiModel;
import com.example.veterineruygulamasi.Models.PetModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.Utils.ChangeFragments;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.veterineruygulamasi.R.layout.sanalkarnegecmislayout;
import static com.example.veterineruygulamasi.R.layout.sanalkarnepetlayout;

public class SanalKarneGecmisAsiAdapter extends RecyclerView.Adapter<SanalKarneGecmisAsiAdapter.ViewHolder>{

    List<AsiModel> list;
    Context context;

    public SanalKarneGecmisAsiAdapter(List<AsiModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(sanalkarnegecmislayout,parent,false);
       return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.sanalKarneGecmisAsiTexxt.setText(list.get(position).getAsiisim().toString()+" Aşısı Yapıldı");

        holder.sanalKarneGecmisAsiBilgi.setText(list.get(position).getPetisim().toString()+" İsimli Petinize" +
                list.get(position).getAsitarih()+" Tarihinde"+ list.get(position).getAsiisim()+ " Aşısı Yapılmıştır");

        Picasso.get().load(list.get(position).getPetresim().toString()).into(holder.sanalKarneGecmisAsiImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView sanalKarneGecmisAsiTexxt,sanalKarneGecmisAsiBilgi;
        CircleImageView sanalKarneGecmisAsiImage;


        //her pet için item
        public ViewHolder(View itemView) {
            super(itemView);


            sanalKarneGecmisAsiTexxt = (TextView) itemView.findViewById(R.id.sanalKarneGecmisAsiTexxt);
            sanalKarneGecmisAsiBilgi =(TextView)  itemView.findViewById(R.id.sanalKarneGecmisAsiBilgi);
            sanalKarneGecmisAsiImage =(CircleImageView)  itemView.findViewById(R.id.sanalKarneGecmisAsiImage);




        }
    }
}
