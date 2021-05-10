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
import com.example.veterineruygulamasi.Models.PetModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.Utils.ChangeFragments;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.veterineruygulamasi.R.layout.petlistitemlayout;
import static com.example.veterineruygulamasi.R.layout.sanalkarnepetlayout;

public class SanalKarnePetAdapter extends RecyclerView.Adapter<SanalKarnePetAdapter.ViewHolder>{

    List<PetModel> list;
    Context context;

    public SanalKarnePetAdapter(List<PetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(sanalkarnepetlayout,parent,false);
       return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.sanalkarnePetText.setText(list.get(position).getPetisim().toString());
        holder.sanalKarneBilgiText.setText(list.get(position).getPetisim().toString()+" isimli" + list.get(position).getPettur()+" Türüne " + list.get(position).getPetcins()+" cinsine" +
                " ait petiniz geçmiş aşıları " +
                "görmek için tıklayınız...");


        Picasso.get().load(list.get(position).getPetresim()).into(holder.sanalKarnePetImage);

        holder.sanalLayoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragments changeFragments = new ChangeFragments(context);
                changeFragments.changeParametre(new AsiDetayFragment(),list.get(position).getPetid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView sanalkarnePetText,sanalKarneBilgiText;
        CircleImageView sanalKarnePetImage;
        CardView sanalLayoutCardView;

        //her pet için item
        public ViewHolder(View itemView) {
            super(itemView);


            sanalkarnePetText = (TextView) itemView.findViewById(R.id.sanalkarnePetText);
            sanalKarneBilgiText =(TextView)  itemView.findViewById(R.id.sanalKarneBilgiText);

            sanalKarnePetImage =(CircleImageView)  itemView.findViewById(R.id.sanalKarnePetImage);
            sanalLayoutCardView = itemView.findViewById(R.id.sanalLayoutCardView);



        }
    }
}
