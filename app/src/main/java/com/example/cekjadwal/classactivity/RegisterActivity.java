package com.example.cekjadwal.classactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cekjadwal.API.ApiClient;
import com.example.cekjadwal.API.ApiInterface;
import com.example.cekjadwal.R;
import com.example.cekjadwal.ambil_kode_regis.SetCodeRegis;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText etEmail,etname,etPassword;
    Button btnRegister;
    TextView tvLogin;
    String email,password,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        etEmail=findViewById(R.id.etRegisterEmail);
        etname=findViewById(R.id.et_room_name);
        etPassword=findViewById(R.id.et_password_room);
        tvLogin=findViewById(R.id.tvlogin);
        btnRegister=findViewById(R.id.btnVerifikasi);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnVerifikasi:
                FirebaseAuth.getInstance().signOut();
                email=etEmail.getText().toString().replaceAll("\\s", "");
                password=etPassword.getText().toString().replaceAll("\\s", "");
                name=etname.getText().toString();
                auth(email,password);
                break;
            case R.id.tvlogin:
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void auth(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Email Valid", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseAuth.getInstance().signOut();
                            user.delete();
                            sentAskCodeRegis(email);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Email Tidak Valid", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void sentAskCodeRegis(String email) {
        ApiInterface apiInterface;
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<SetCodeRegis> call=apiInterface.sentAskCodeRegis(email);
        call.enqueue(new Callback<SetCodeRegis>() {
            @Override
            public void onResponse(Call<SetCodeRegis> call, Response<SetCodeRegis> response) {
                if(response.body().isStatus()){
                    Intent intent=new Intent(RegisterActivity.this,KodeRegistrasi.class);
                    intent.putExtra("email",email);
                    intent.putExtra("password",password);
                    intent.putExtra("nama",name);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SetCodeRegis> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}