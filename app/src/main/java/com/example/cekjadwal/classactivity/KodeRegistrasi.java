package com.example.cekjadwal.classactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cekjadwal.API.ApiClient;
import com.example.cekjadwal.API.ApiInterface;
import com.example.cekjadwal.R;
import com.example.cekjadwal.ambil_kode_regis.GetCodeRegis;
import com.example.cekjadwal.ambil_kode_regis.SetCodeRegis;
import com.example.cekjadwal.registrasi.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KodeRegistrasi extends AppCompatActivity {
    String email,password,nama,kodeAkses;
    EditText etkodeakses;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode_registrasi);
        Intent intent=getIntent();
        textView=findViewById(R.id.tv_ket);
        etkodeakses=findViewById(R.id.etkodeakses);
        nama=intent.getStringExtra("nama");
        email=intent.getStringExtra("email");
        password=intent.getStringExtra("password");
//        sentAskCodeRegis(email);
        getCodeRegis(email);
    }

    private void getCodeRegis(String email) {
        ApiInterface apiInterface;
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<GetCodeRegis> call=apiInterface.getCodeRegisUser(email);
        call.enqueue(new Callback<GetCodeRegis>() {
            @Override
            public void onResponse(Call<GetCodeRegis> call, Response<GetCodeRegis> response) {
                if(response.body().isStatus()){
                    kodeAkses=response.body().getKode_akses();
                }
            }
            @Override
            public void onFailure(Call<GetCodeRegis> call, Throwable t) {
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ApiInterface apiInterface;
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<SetCodeRegis> call=apiInterface.deleteCodeRegis_byone(email);
        call.enqueue(new Callback<SetCodeRegis>() {
            @Override
            public void onResponse(Call<SetCodeRegis> call, Response<SetCodeRegis> response) {
                Toast.makeText(KodeRegistrasi.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SetCodeRegis> call, Throwable t) {
                Toast.makeText(KodeRegistrasi.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

//    private void sentAskCodeRegis(String email) {
//        ApiInterface apiInterface;
//        apiInterface= ApiClient.getClient().create(ApiInterface.class);
//        Call<SetCodeRegis> call=apiInterface.sentAskCodeRegis(email);
//        call.enqueue(new Callback<SetCodeRegis>() {
//            @Override
//            public void onResponse(Call<SetCodeRegis> call, Response<SetCodeRegis> response) {
//                textView.setText(response.body().getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<SetCodeRegis> call, Throwable t) {
//                textView.setText(t.getLocalizedMessage());
//            }
//        });
//    }

    public void onClick(View view) {
        if(kodeAkses.equals(etkodeakses.getText().toString().replaceAll("\\s", ""))){
            register();
        }else{
            Toast.makeText(this, "kode salah", Toast.LENGTH_SHORT).show();
        }
    }

    private void register() {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call=apiInterface.registerResponse("1",email, password, nama);
        call.enqueue(new Callback<Register>() {
            @Override

            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.body()!=null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(KodeRegistrasi.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(KodeRegistrasi.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(KodeRegistrasi.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(KodeRegistrasi.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}