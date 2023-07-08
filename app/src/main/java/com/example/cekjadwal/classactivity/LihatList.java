package com.example.cekjadwal.classactivity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cekjadwal.API.ApiClient;
import com.example.cekjadwal.API.ApiInterface;
import com.example.cekjadwal.DataAwal.Profil;
import com.example.cekjadwal.R;
import com.example.cekjadwal.SessionManager;
import com.example.cekjadwal.Waktu;
import com.example.cekjadwal.adapter.ItemAdapter;
import com.example.cekjadwal.semuaData.AmbilJadwal;
import com.example.cekjadwal.semuaData.DataItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LihatList extends AppCompatActivity {
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;
    String id_room;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_list);
        Intent intent=getIntent();
        id_room=intent.getStringExtra("id");
        srlData=findViewById(R.id.swl_data);
        pbData=findViewById(R.id.pb_data);
        sessionManager=new SessionManager(this);
        ambilData();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                ambilData();
                srlData.setRefreshing(false);
            }
        });
    }
    public void setRecycler(Profil profil) {
        RecyclerView rvItem = findViewById(R.id.rvLihatList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(LihatList.this);
        ItemAdapter itemAdapter = new ItemAdapter(profil,this);
        rvItem.setAdapter(itemAdapter);
        rvItem.setLayoutManager(layoutManager);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back_lihat_activity:
                Intent intent=new Intent(LihatList.this, LayarUtama.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    public void ambilData() {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<AmbilJadwal> call=apiInterface.ambilJadwalByIdRoom(id_room);
        call.enqueue(new Callback<AmbilJadwal>() {
            @Override
            public void onResponse(Call<AmbilJadwal> call, Response<AmbilJadwal> response) {
                if(response.body().isStatus()){
                    buatData(response.body().getData());
                }else{
                    Toast.makeText(LihatList.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AmbilJadwal> call, Throwable t) {
                Toast.makeText(LihatList.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void buatData(List<DataItem> dataItem) {
        Profil profil=new Profil();
        for(int i=0;i<dataItem.size();i++){
            String id=dataItem.get(i).getId();
            String nama=dataItem.get(i).getNama();
            String email=dataItem.get(i).getEmail();
            ArrayList<Waktu> a1,a2,a3,a4,a5,a6,a7;
            a1=new ArrayList<>();
            a2=new ArrayList<>();
            a3=new ArrayList<>();
            a4=new ArrayList<>();
            a5=new ArrayList<>();
            a6=new ArrayList<>();
            a7=new ArrayList<>();
            String[] s1,s2,s3,s4,s5,s6,s7;
            s1=dataItem.get(i).getSenin().split(",");
            s2=dataItem.get(i).getSelasa().split(",");
            s3=dataItem.get(i).getRabu().split(",");
            s4=dataItem.get(i).getKamis().split(",");
            s5=dataItem.get(i).getJumat().split(",");
            s6=dataItem.get(i).getSabtu().split(",");
            s7=dataItem.get(i).getMinggu().split(",");
            for(int j=0;j<s1.length;j+=2){
                Waktu waktu=new Waktu(s1[j],s1[j+1]);
                a1.add(waktu);
            }
            for(int j=0;j<s2.length;j+=2){
                Waktu waktu=new Waktu(s2[j],s2[j+1]);
                a2.add(waktu);
            }
            for(int j=0;j<s3.length;j+=2){
                Waktu waktu=new Waktu(s3[j],s3[j+1]);
                a3.add(waktu);
            }
            for(int j=0;j<s4.length;j+=2){
                Waktu waktu=new Waktu(s4[j],s4[j+1]);
                a4.add(waktu);
            }
            for(int j=0;j<s5.length;j+=2){
                Waktu waktu=new Waktu(s5[j],s5[j+1]);
                a5.add(waktu);
            }
            for(int j=0;j<s6.length;j+=2){
                Waktu waktu=new Waktu(s6[j],s6[j+1]);
                a6.add(waktu);
            }
            for(int j=0;j<s7.length;j+=2){
                Waktu waktu=new Waktu(s7[j],s7[j+1]);
                a7.add(waktu);
            }
            profil.tambahData(id,email,nama,a1,a2,a3,a4,a5,a6,a7);
        }
        setRecycler(profil);
        sessionManager.createLoginSession(profil);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        SearchView searchView=(SearchView) menu.findItem(R.id.item_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText=newText.toLowerCase(Locale.ROOT);
                Profil prof=sessionManager.mboh();
                prof.setfilterProfil(newText);
                setRecycler(prof);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}