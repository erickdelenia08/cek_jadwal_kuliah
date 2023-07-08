package com.example.cekjadwal.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cekjadwal.API.ApiClient;
import com.example.cekjadwal.API.ApiInterface;
import com.example.cekjadwal.DataItem_create_room.ResponseCreateRoom;
import com.example.cekjadwal.GetLoadRoom.DataResponLoadRoom;
import com.example.cekjadwal.GetLoadRoom.ResponseLoadRoom;
import com.example.cekjadwal.R;
import com.example.cekjadwal.classactivity.ActivityCek;
import com.example.cekjadwal.classactivity.LihatList;
import com.example.cekjadwal.classactivity.LoadRoomActivity;
import com.example.cekjadwal.classactivity.MainActivity;
import com.example.cekjadwal.semuaData.AmbilJadwal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdapterLoadRoom extends RecyclerView.Adapter<AdapterLoadRoom.ItemViewHolder> {
    List<DataResponLoadRoom> data;
    Context context;
    public  AdapterLoadRoom(List<DataResponLoadRoom> data, Context context){
        this.context=context;
        this.data=data;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_loadroom,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv1.setText(data.get(position).getNamaRoom());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Pilih operasi yang diambil");
                builder.setCancelable(true);
                builder.setPositiveButton("hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapus(data.get(position).getId());
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        holder.cv_add_jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MainActivity.class);
                intent.putExtra("id",data.get(position).getId());
                ((LoadRoomActivity) context).startActivity(intent);
            }
        });

        holder.cv_cek_participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, LihatList.class);
                intent.putExtra("id",data.get(position).getId());
                ((LoadRoomActivity) context).startActivity(intent);
            }
        });

        holder.cv_cek_jam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ActivityCek.class);
                intent.putExtra("id",data.get(position).getId());
                ((LoadRoomActivity) context).startActivity(intent);
            }
        });

        holder.cv_delete_all_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("data akan terhapus total, anda yakin ?");
                builder.setCancelable(true);
                builder.setPositiveButton("hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteAlluserAtRoom(data.get(position).getId());
                    }
                });
                builder.setNegativeButton("batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    private void deleteAlluserAtRoom(String id) {

        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<AmbilJadwal> call=apiInterface.deleteAllJadwalByIdRoom(id);
        call.enqueue(new Callback<AmbilJadwal>() {
            @Override
            public void onResponse(Call<AmbilJadwal> call, Response<AmbilJadwal> response) {
                Toast.makeText(context, response.body().getPesan(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AmbilJadwal> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hapus(String idRoom) {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLoadRoom> call=apiInterface.deleteMyRoomById(idRoom);
        call.enqueue(new Callback<ResponseLoadRoom>() {
            @Override
            public void onResponse(Call<ResponseLoadRoom> call, Response<ResponseLoadRoom> response) {
                Toast.makeText(context, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                if(response.body().isStatus()) {
                    if (data.size() == 1) {
                        ((LoadRoomActivity) context).finish();
                    } else {
                        ((LoadRoomActivity) context).ambilData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseLoadRoom> call, Throwable t) {
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView,cv_cek_participants,cv_cek_jam,cv_delete_all_user,cv_add_jadwal;
        private TextView tv1;
        public ItemViewHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.img_delete_room);
            tv1=view.findViewById(R.id.tv_room_name_load);
            cardView=view.findViewById(R.id.cv_load_room);
            cv_cek_jam=view.findViewById(R.id.cv_cek_jam_load_room);
            cv_cek_participants=view.findViewById(R.id.cv_participan_load_room);
            cv_delete_all_user=view.findViewById(R.id.cv_delete_load_room);
            cv_add_jadwal=view.findViewById(R.id.cv_tambah_jadwal_load_room);

        }
    }
}
