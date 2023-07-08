package com.example.cekjadwal.classactivity;

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
import com.example.cekjadwal.SessionManager;
import com.example.cekjadwal.lojin.Login;
import com.example.cekjadwal.lojin.LoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail,etPassword;
    Button btnLogin;
    String email,password;
    TextView tvRegister;
    ApiInterface apiInterface;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvRegister=findViewById(R.id.tvCreateAccount);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                email=etEmail.getText().toString().replaceAll("\\s", "");
                password=etPassword.getText().toString().replaceAll("\\s", "");
                login("1",email,password);
                break;
            case R.id.tvCreateAccount:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;

        }
    }
    private void login(String tipe, String email, String password) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall=apiInterface.loginResponse(tipe,email, password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {
                    if(response.body()!=null && response.isSuccessful() && response.body().isStatus()){
                        sessionManager=new SessionManager(LoginActivity.this);
                        LoginData loginData=response.body().getLoginData();
                        sessionManager.createLoginSession(loginData);

                        Toast.makeText(LoginActivity.this,response.body().getLoginData().getNama(),Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,LayarUtama.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}