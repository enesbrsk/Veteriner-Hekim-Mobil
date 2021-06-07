package com.example.veterineruygulamasi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterineruygulamasi.Models.AnswersModel;
import com.example.veterineruygulamasi.Models.DeleteAnswerModel;
import com.example.veterineruygulamasi.Models.PetModel;
import com.example.veterineruygulamasi.R;
import com.example.veterineruygulamasi.RestApi.ManagerAll;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.veterineruygulamasi.R.layout.cevapitemlayout;
import static com.example.veterineruygulamasi.R.layout.petlistitemlayout;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder>{

    List<AnswersModel> list;
    Context context;

    public AnswersAdapter(List<AnswersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(context).inflate(cevapitemlayout,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

holder.cevapCevapText.setText("Cevap: "+list.get(position).getCevap().toString());
holder.cevapSoruText.setText("Soru: " +list.get(position).getSoru().toString());

holder.cevapSilButon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        deleteToService(list.get(position).getCevapid().toString(),list.get(position).getSoruid().toString(),position);
    }
});

    }

    public void deleteToList(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();

    }
    public  void deleteToService(String cevapid,String soruid,final int pos)
    {
        Call<DeleteAnswerModel> req = ManagerAll.getInstance().deleteAnswer(cevapid,soruid);
        req.enqueue(new Callback<DeleteAnswerModel>() {
            @Override
            public void onResponse(Call<DeleteAnswerModel> call, Response<DeleteAnswerModel> response) {
                if (response.body().isTf())
                {
                    if (response.isSuccessful())
                    {
                        Toast.makeText(context, response.body().getText(), Toast.LENGTH_LONG).show();
                        deleteToList(pos);
                    }

                }else {
                    Toast.makeText(context, response.body().getText(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<DeleteAnswerModel> call, Throwable t) {
                Toast.makeText(context, "Hata", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView cevapSoruText,cevapCevapText;
        Button cevapSilButon;

        public ViewHolder(View itemView) {
            super(itemView);

            cevapSoruText = (TextView) itemView.findViewById(R.id.cevapSoruText);
            cevapCevapText =(TextView) itemView.findViewById(R.id.cevapCevapText);
            cevapSilButon =(Button) itemView.findViewById(R.id.cevapSilButon);




        }
    }
}
