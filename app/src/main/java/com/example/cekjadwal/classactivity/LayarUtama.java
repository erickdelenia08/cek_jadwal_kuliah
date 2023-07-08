package com.example.cekjadwal.classactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cekjadwal.API.ApiClient;
import com.example.cekjadwal.API.ApiInterface;
import com.example.cekjadwal.DataAwal.Profil;
import com.example.cekjadwal.R;
import com.example.cekjadwal.SessionManager;
import com.example.cekjadwal.Waktu;
import com.example.cekjadwal.hapussemuajadwal.HapusModel;
import com.example.cekjadwal.lojin.Login;
import com.example.cekjadwal.semuaData.AmbilJadwal;
import com.example.cekjadwal.semuaData.DataItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarUtama extends AppCompatActivity{
    Profil profil;
    SessionManager sessionManager;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_utama);
        sessionManager=new SessionManager(this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }else{
            textView=findViewById(R.id.idx);

            profil=new Profil();
            ambilData();
            sessionManager=new SessionManager(this);
            sessionManager.createLoginSession(profil);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cek_login();
    }
    private void moveToLogin() {
        Intent intent=new Intent(LayarUtama.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
    private void cek_login(){
        String email=sessionManager.getUserDetail().get(SessionManager.EMAIL);
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall=apiInterface.cekloginResponse("1",email);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (!response.body().isStatus()){
                    sessionManager.logoutSession();
                    Toast.makeText(LayarUtama.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    moveToLogin();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }

    private void ambilData() {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<AmbilJadwal> call=apiInterface.ambilJadwal();
        call.enqueue(new Callback<AmbilJadwal>() {
            @Override
            public void onResponse(Call<AmbilJadwal> call, Response<AmbilJadwal> response) {
                if(response.body().isStatus()){
                    buatData(response.body().getData());
                    textView.setBackgroundColor(Color.parseColor("#25DF19"));
                    textView.setText("Data Ada !");
                }else{
                    textView.setBackgroundColor(Color.parseColor("#E40E0E"));
                    textView.setText("Data Kosong !");
                }

            }

            @Override
            public void onFailure(Call<AmbilJadwal> call, Throwable t) {
                textView.setBackgroundColor(Color.parseColor("#E40E0E"));
                textView.setText("mode offline");

            }
        });

    }
    private void buatData(List<DataItem> dataItem) {
        Profil pr=new Profil();
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
            pr.tambahData(id,email,nama,a1,a2,a3,a4,a5,a6,a7);
        }
        sessionManager.createLoginSession(pr);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.cv2_main:
                Intent intent2=new Intent(this,LoadRoomActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.actionLogout:
                sessionManager.logoutSession();
                moveToLogin();
        }
        return super.onOptionsItemSelected(item);
    }

}