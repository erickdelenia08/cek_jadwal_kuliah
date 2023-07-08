package com.example.cekjadwal.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cekjadwal.R;
import com.example.cekjadwal.Waktu;
import com.example.cekjadwal.datakres.DetailPerson;
import com.example.cekjadwal.datakres.Person;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ItemAdapterForCek extends RecyclerView.Adapter<ItemAdapterForCek.ItemViewHolder> {

    private DetailPerson item;
    Context context;

   public ItemAdapterForCek(DetailPerson itemList, Context context) {
        this.item = itemList;
        this.context=context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cek, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ArrayList<Person> data = item.getListData();
        holder.tv1.setText(data.get(position).nama);
        holder.tv2.setText("JAM KRESS HARI "+cekHari(data.get(position).hari).toUpperCase());
        holder.tv3.setText("\n"+build(data.get(position)));

    }
    public String cekHari(int hari) {
        String a=new String();
        if(hari==1) {
            a="Senin";
        }else if(hari==2) {
            a="Selasa";
        }else if(hari==3) {
            a="Rabu";
        }else if(hari==4) {
            a="Kamis";
        }else if(hari==5) {
            a="Jumat";
        }else if(hari==6) {
            a="Sabtu";
        }else if(hari==7) {
            a="Minggu";
        }
        return a;
    }

    String build(Person data){
        StringBuilder stringBuilder=new StringBuilder();
        ArrayList<Waktu> a=data.jamKres;
        for(int i=0;i<a.size();i++){
            stringBuilder.append(a.get(i).bawah+" - "+a.get(i).atas+"\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public int getItemCount() {
        return item.getListCount();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1,tv2,tv3;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv2=itemView.findViewById(R.id.tv2_layout_item_cek);
            tv1=itemView.findViewById(R.id.tv1_layout_item_cek);
            tv3=itemView.findViewById(R.id.tv3_layout_item_cek);
        }
    }
}