package com.example.cekjadwal.classactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cekjadwal.API.ApiClient;
import com.example.cekjadwal.API.ApiInterface;
import com.example.cekjadwal.GetLoadRoom.DataResponLoadRoom;
import com.example.cekjadwal.GetLoadRoom.ResponseLoadRoom;
import com.example.cekjadwal.R;
import com.example.cekjadwal.SessionManager;
import com.example.cekjadwal.adapter.AdapterLoadRoom;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadRoomActivity extends AppCompatActivity {
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_room);
        sessionManager=new SessionManager(this);
        ambilData();
    }

    public void ambilData() {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLoadRoom> call=apiInterface.getMyRoom(sessionManager.getUserDetail().get(SessionManager.EMAIL));
        call.enqueue(new Callback<ResponseLoadRoom>() {
            @Override
            public void onResponse(Call<ResponseLoadRoom> call, Response<ResponseLoadRoom> response) {
                if(response.body().isStatus()) {
                    setRecycle(response.body().getData());
                }else{
                    Toast.makeText(LoadRoomActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLoadRoom> call, Throwable t) {
                Toast.makeText(LoadRoomActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecycle(List<DataResponLoadRoom> data) {
        RecyclerView rvItem = findViewById(R.id.rv_load_room);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterLoadRoom itemAdapter = new AdapterLoadRoom(data,this);
        rvItem.setAdapter(itemAdapter);
        rvItem.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        ambilData();
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_room,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_daftar_room:
                Intent intent=new Intent(this, CreateRoomActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}