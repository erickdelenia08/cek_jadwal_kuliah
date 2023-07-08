package com.example.cekjadwal.classactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cekjadwal.API.ApiClient;
import com.example.cekjadwal.API.ApiInterface;
import com.example.cekjadwal.DataItem_create_room.ResponseCreateRoom;
import com.example.cekjadwal.R;
import com.example.cekjadwal.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRoomActivity extends AppCompatActivity {
    TextView tv_name,tv_kode_akses;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        sessionManager=new SessionManager(this);
        tv_name=findViewById(R.id.et_room_name);
        tv_kode_akses=findViewById(R.id.et_password_room);
    }

    public void onClick(View view) {
        String nama=tv_name.getText().toString();
        String kode=tv_kode_akses.getText().toString();
        if(!nama.equals("") && !kode.equals("")){
            sendDatabase(nama,kode);
        }
    }

    private void sendDatabase(String nama,String kode) {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseCreateRoom> call=apiInterface.createMyRoom(nama,sessionManager.getUserDetail().get(SessionManager.EMAIL),kode);
        call.enqueue(new Callback<ResponseCreateRoom>() {
            @Override
            public void onResponse(Call<ResponseCreateRoom> call, Response<ResponseCreateRoom> response) {
                Toast.makeText(CreateRoomActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                if(response.body().isStatus()) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseCreateRoom> call, Throwable t) {
                Toast.makeText(CreateRoomActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}