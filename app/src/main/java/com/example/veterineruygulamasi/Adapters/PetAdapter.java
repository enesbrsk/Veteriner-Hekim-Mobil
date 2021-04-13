package com.example.veterineruygulamasi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineruygulamasi.Models.PetModel;
import com.example.veterineruygulamasi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.veterineruygulamasi.R.layout.petlistitemlayout;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder>{

    List<PetModel> list;
    Context context;

    public PetAdapter(List<PetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(petlistitemlayout,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.petlayoutcinsname.setText("Pet Cinsi = " +list.get(position).getPetcins().toString());
        holder.petlayoutpetname.setText("Pet ismi = "+list.get(position).getPetisim().toString());
        holder.petlayoutturname.setText("Pet Turu = "+list.get(position).getPettur().toString());

        Picasso.get().load(list.get(position).getPetresim()).into(holder.petlayoutpetimage);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView petlayoutpetname,petlayoutcinsname,petlayoutturname;
        CircleImageView petlayoutpetimage;

        //her pet için item
        public ViewHolder(View itemView) {
            super(itemView);

            petlayoutpetname = (TextView) itemView.findViewById(R.id.petlayoutpetname);
            petlayoutcinsname =(TextView)  itemView.findViewById(R.id.petlayoutcinsname);
            petlayoutturname =(TextView)  itemView.findViewById(R.id.petlayoutturname);
            petlayoutpetimage =(CircleImageView)  itemView.findViewById(R.id.petlayoutpetimage);




        }
    }
}
