package com.example.cekjadwal.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cekjadwal.API.ApiClient;
import com.example.cekjadwal.API.ApiInterface;
import com.example.cekjadwal.DataAwal.Data;
import com.example.cekjadwal.DataAwal.Profil;
import com.example.cekjadwal.classactivity.LayarUtama;
import com.example.cekjadwal.classactivity.LihatList;
import com.example.cekjadwal.R;
import com.example.cekjadwal.Waktu;
import com.example.cekjadwal.semuaData.AmbilJadwal;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Profil item;
    Context context;
    View viewsetFilter;

    public ItemAdapter(Profil itemList,Context context) {
        this.item = itemList;
        this.context=context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        viewsetFilter=view;
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ArrayList<Data> data = item.getListData();
        holder.tv1.setText(data.get(position).nama);
        holder.tv_email.setText("("+data.get(position).email+")");
        holder.tv2.setText(build(data.get(position),0));
        holder.tv3.setText(build(data.get(position),1));
        holder.tv4.setText(build(data.get(position),2));
        holder.tv5.setText(build(data.get(position),3));
        holder.tv6.setText(build(data.get(position),4));
        holder.tv7.setText(build(data.get(position),5));
        holder.tv8.setText(build(data.get(position),6));
        holder.tv9.setText(data.get(position).id);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Pilih operasi yang diambil");
                builder.setCancelable(true);
                builder.setPositiveButton("hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(item.getListCount()==1){
                            hapusItem(data.get(position).nama,data.get(position).id);
                            Intent intent=new Intent(context, LayarUtama.class);
                            context.startActivity(intent);
                            ((LihatList)context).finish();
                            dialog.dismiss();
                        }else{
                            hapusItem(data.get(position).nama,data.get(position).id);
                            dialog.dismiss();
                            ((LihatList)context).setRecycler(item);
                        }
                    }
                });
                builder.setNegativeButton("batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return false;
            }
        });

    }

    private void hapusItem(String n,String id) {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<AmbilJadwal> call=apiInterface.deleteJadwalSatuSatu(id);
        call.enqueue(new Callback<AmbilJadwal>() {
            @Override
            public void onResponse(Call<AmbilJadwal> call, Response<AmbilJadwal> response) {
                if(response.body().isStatus()){
                    Toast.makeText(context, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    ((LihatList)context).ambilData();
                }else{
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AmbilJadwal> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    String build(Data data,int hari){
        StringBuilder stringBuilder=new StringBuilder();
        if(hari==0){
            ArrayList<Waktu> s=data.senin;
            for(int i=0;i<data.senin.size();i++){
                stringBuilder.append("\n"+s.get(i).bawah+" - "+s.get(i).atas+"\n");
            }
        }
        if(hari==1){
            ArrayList<Waktu> s=data.selasa;
            for(int i=0;i<data.selasa.size();i++){
                stringBuilder.append("\n"+s.get(i).bawah+" - "+s.get(i).atas+"\n");
            }
        }
        if(hari==2){
            ArrayList<Waktu> s=data.rabu;
            for(int i=0;i<data.rabu.size();i++){
                stringBuilder.append("\n"+s.get(i).bawah+" - "+s.get(i).atas+"\n");
            }
        }
        if(hari==3){
            ArrayList<Waktu> s=data.kamis;
            for(int i=0;i<data.kamis.size();i++){
                stringBuilder.append("\n"+s.get(i).bawah+" - "+s.get(i).atas+"\n");
            }
        }
        if(hari==4){
            ArrayList<Waktu> s=data.jumat;
            for(int i=0;i<data.jumat.size();i++){
                stringBuilder.append("\n"+s.get(i).bawah+" - "+s.get(i).atas+"\n");
            }
        }
        if(hari==5){
            ArrayList<Waktu> s=data.sabtu;
            for(int i=0;i<data.sabtu.size();i++){
                stringBuilder.append("\n"+s.get(i).bawah+" - "+s.get(i).atas+"\n");
            }
        }
        if(hari==6){
            ArrayList<Waktu> s=data.minggu;
            for(int i=0;i<data.minggu.size();i++){
                stringBuilder.append("\n"+s.get(i).bawah+" - "+s.get(i).atas+"\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public int getItemCount() {
        return item.getListCount();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        private TextView tv_email,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cv_layout_item);
            tv_email=itemView.findViewById(R.id.tv_email_list);
            tv2=itemView.findViewById(R.id.tv2_layout_list);
            tv1=itemView.findViewById(R.id.tv_layout_list);
            tv3=itemView.findViewById(R.id.tv3_layout_list);
            tv4=itemView.findViewById(R.id.tv4_layout_list);
            tv5=itemView.findViewById(R.id.tv5_layout_list);
            tv6=itemView.findViewById(R.id.tv6_layout_list);
            tv7=itemView.findViewById(R.id.tv7_layout_list);
            tv8=itemView.findViewById(R.id.tv8_layout_list);
            tv9=itemView.findViewById(R.id.tv9_layout_list);
        }
    }
}