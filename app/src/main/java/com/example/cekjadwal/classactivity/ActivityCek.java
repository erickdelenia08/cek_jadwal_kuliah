package com.example.cekjadwal.classactivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cekjadwal.API.ApiClient;
import com.example.cekjadwal.API.ApiInterface;
import com.example.cekjadwal.DataAwal.Profil;
import com.example.cekjadwal.R;
import com.example.cekjadwal.SessionManager;
import com.example.cekjadwal.Waktu;
import com.example.cekjadwal.adapter.ItemAdapterForCek;
import com.example.cekjadwal.cek.PW;
import com.example.cekjadwal.datakres.DetailPerson;
import com.example.cekjadwal.semuaData.AmbilJadwal;
import com.example.cekjadwal.semuaData.DataItem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCek extends AppCompatActivity {
    RecyclerView rvItem ;
    SessionManager sessionManager;
    Profil profil;
    PW pw;
    String id_room;
    DetailPerson detailPerson;
    AppCompatSpinner spin1,spin2,spin3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek);
        Intent intent=getIntent();
        sessionManager=new SessionManager(this);
        id_room=intent.getStringExtra("id");
        ambilData();
        rvItem = findViewById(R.id.rv_cek);
        spin1=findViewById(R.id.spin1_cek);
        spin2=findViewById(R.id.spin2_cek);
        spin3=findViewById(R.id.spin3_cek);
        profil=sessionManager.mboh();
        profil.insertToCeck();
        pw=profil.getDataPW();
        setSpinner();
    }

    private void setSpinner() {
        ArrayList a=new ArrayList();
        ArrayList b=new ArrayList();
        ArrayList c=new ArrayList();
        a.add("Jam");
        b.add("Menit");
        c.add("Hari");
        c.add("Senin");
        c.add("Selasa");
        c.add("Rabu");
        c.add("Kamis");
        c.add("Jumat");
        c.add("Sabtu");
        c.add("Minggu");

        for(int i=0;i<24;i++){
            a.add(String.format("%02d",i));
        }
        for(int i=0;i<60;i++){
            b.add(String.format("%02d",i));
        }
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,c);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,a);
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,b);
        spin1.setAdapter(arrayAdapter1);
        spin2.setAdapter(arrayAdapter2);
        spin3.setAdapter(arrayAdapter3);

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
                    Toast.makeText(ActivityCek.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AmbilJadwal> call, Throwable t) {
                Toast.makeText(ActivityCek.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
        sessionManager.createLoginSession(profil);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view) {
        TextView tvKondisi=findViewById(R.id.tv1_cek_kondisi);
        switch (view.getId()){

            case R.id.btn_cek:
                rvItem.removeAllViewsInLayout();

                if(spin1.getSelectedItemPosition()==0 || spin2.getSelectedItemPosition()==0 || spin3.getSelectedItemPosition()==0 ){
                    Toast.makeText(this, "Isi Dulu", Toast.LENGTH_SHORT).show();
                }else{
                    boolean kondisi;
                    LocalTime a = LocalTime.of(spin2.getSelectedItemPosition()-1, spin3.getSelectedItemPosition()-1);
                    kondisi=pw.cek(a,spin1.getSelectedItemPosition());
                    DetailPerson det=pw.cekWaktu(a,spin1.getSelectedItemPosition());
                    det.disp();
                    if(kondisi){
                        tvKondisi.setText("Ngga Kress");
                        tvKondisi.setBackgroundColor(Color.parseColor("#29ED31"));
                    }else{
                        tvKondisi.setText("Kress");
                        tvKondisi.setBackgroundColor(Color.parseColor("#D30D0D"));
                        setRecyclerView(det);
                    }
                }
                break;
        }

    }
    public void setRecyclerView(DetailPerson detailPerson){

        LinearLayoutManager layoutManager = new LinearLayoutManager(ActivityCek.this);
        ItemAdapterForCek itemAdapter = new ItemAdapterForCek(detailPerson,this);
        rvItem.setAdapter(itemAdapter);
        rvItem.setLayoutManager(layoutManager);
    }
}