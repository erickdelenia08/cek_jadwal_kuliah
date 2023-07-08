package com.example.cekjadwal.classactivity;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cekjadwal.API.ApiClient;
import com.example.cekjadwal.API.ApiInterface;
import com.example.cekjadwal.DataAwal.Data;
import com.example.cekjadwal.DataAwal.Profil;
import com.example.cekjadwal.R;
import com.example.cekjadwal.SessionManager;
import com.example.cekjadwal.Waktu;
import com.example.cekjadwal.createjadwalnya.CreateJadwal;

import java.time.LocalTime;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    LinearLayout l1,l2,l3,l4,l5,l6,l7;
    EditText editText;
    Profil profil;
    String id_room;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisiasi();

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_senin:
                tambahChildLayout(l1);
                break;
            case R.id.btn_selasa:
                tambahChildLayout(l2);
                break;
            case R.id.btn_rabu:
                tambahChildLayout(l3);
                break;
            case R.id.btn_kamis:
                tambahChildLayout(l4);
                break;
            case R.id.btn_jumat:
                tambahChildLayout(l5);
                break;
            case R.id.btn_sabtu:
                tambahChildLayout(l6);
                break;
            case R.id.btn_minggu:
                tambahChildLayout(l7);
                break;
            case R.id.btn_submit:
                submitJadwal();
                break;
        }
    }

    private void inisiasi() {
        Intent intent=getIntent();
        id_room=intent.getStringExtra("id");
        sessionManager=new SessionManager(this);
        editText=findViewById(R.id.edit_text_nama);
        l1=findViewById(R.id.lin_lay_senin_act_tambah);
        l2=findViewById(R.id.lin_lay_selasa_act_tambah);
        l3=findViewById(R.id.lin_lay_rabu_act_tambah);
        l4=findViewById(R.id.lin_lay_kamis_act_tambah);
        l5=findViewById(R.id.lin_lay_jumat_act_tambah);
        l6=findViewById(R.id.lin_lay_sabtu_act_tambah);
        l7=findViewById(R.id.lin_lay_minggu_act_tambah);
        tambahChildLayout(l1);
        tambahChildLayout(l2);
        tambahChildLayout(l3);
        tambahChildLayout(l4);
        tambahChildLayout(l5);
        tambahChildLayout(l6);
        tambahChildLayout(l7);
    }
    private void tambahChildLayout(LinearLayout linearLayout) {
        ArrayList a=new ArrayList();
        ArrayList b=new ArrayList();
        AppCompatSpinner sp11,sp12,sp21,sp22;

        final View view=getLayoutInflater().inflate(R.layout.layout_waktu,null,false);

        for(int i=0;i<24;i++){
            a.add(String.format("%02d",i));
        }
        for(int i=0;i<60;i++){
            b.add(String.format("%02d",i));
        }

        sp11=view.findViewById(R.id.spinner11);
        sp12=view.findViewById(R.id.spinner12);
        sp21=view.findViewById(R.id.spinner21);
        sp22=view.findViewById(R.id.spinner22);


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,a);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,b);
        sp11.setAdapter(arrayAdapter);
        sp12.setAdapter(arrayAdapter2);
        sp21.setAdapter(arrayAdapter);
        sp22.setAdapter(arrayAdapter2);

        linearLayout.addView(view);
        ImageView imageView=view.findViewById(R.id.image_remove1);
        if(linearLayout.getChildCount()>1){
            imageView.setVisibility(View.VISIBLE);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(linearLayout.getChildCount()>1){
                    linearLayout.removeView(view);
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void submitJadwal() {
        if(ceknama(editText.getText().toString())){
            saveAll();
        }else{
            Toast.makeText(this, "Nama Kosong atau Format Nama Salah !!", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveAll() {
        ArrayList<Waktu> listSenin=new ArrayList<Waktu>();
        ArrayList<Waktu> listSelasa=new ArrayList<Waktu>();
        ArrayList<Waktu> listRabu=new ArrayList<Waktu>();
        ArrayList<Waktu> listKamis=new ArrayList<Waktu>();
        ArrayList<Waktu> listJumat=new ArrayList<Waktu>();
        ArrayList<Waktu> listSabtu=new ArrayList<Waktu>();
        ArrayList<Waktu> listMinggu=new ArrayList<Waktu>();
        String nama=editText.getText().toString();
        for(int i=0;i<l1.getChildCount();i++) {
            AppCompatSpinner sp11, sp12, sp21, sp22;

            View view = l1.getChildAt(i);
            sp11 = view.findViewById(R.id.spinner11);
            sp12 = view.findViewById(R.id.spinner12);
            sp21 = view.findViewById(R.id.spinner21);
            sp22 = view.findViewById(R.id.spinner22);
            LocalTime a, b;
            a = LocalTime.of(sp11.getSelectedItemPosition(), sp12.getSelectedItemPosition());
            b = LocalTime.of(sp21.getSelectedItemPosition(), sp22.getSelectedItemPosition());
            Waktu w = new Waktu(a.toString(), b.toString());

            listSenin.add(w);
        }
        for(int i=0;i<l2.getChildCount();i++) {
            AppCompatSpinner sp11, sp12, sp21, sp22;

            View view = l2.getChildAt(i);
            sp11 = view.findViewById(R.id.spinner11);
            sp12 = view.findViewById(R.id.spinner12);
            sp21 = view.findViewById(R.id.spinner21);
            sp22 = view.findViewById(R.id.spinner22);
            LocalTime a, b;
            a = LocalTime.of(sp11.getSelectedItemPosition(), sp12.getSelectedItemPosition());
            b = LocalTime.of(sp21.getSelectedItemPosition(), sp22.getSelectedItemPosition());
            Waktu w = new Waktu(a.toString(), b.toString());

            listSelasa.add(w);
        }
        for(int i=0;i<l3.getChildCount();i++) {
            AppCompatSpinner sp11, sp12, sp21, sp22;

            View view = l3.getChildAt(i);
            sp11 = view.findViewById(R.id.spinner11);
            sp12 = view.findViewById(R.id.spinner12);
            sp21 = view.findViewById(R.id.spinner21);
            sp22 = view.findViewById(R.id.spinner22);
            LocalTime a, b;
            a = LocalTime.of(sp11.getSelectedItemPosition(), sp12.getSelectedItemPosition());
            b = LocalTime.of(sp21.getSelectedItemPosition(), sp22.getSelectedItemPosition());
            Waktu w = new Waktu(a.toString(), b.toString());

            listRabu.add(w);
        }
        for(int i=0;i<l4.getChildCount();i++) {
            AppCompatSpinner sp11, sp12, sp21, sp22;

            View view = l4.getChildAt(i);
            sp11 = view.findViewById(R.id.spinner11);
            sp12 = view.findViewById(R.id.spinner12);
            sp21 = view.findViewById(R.id.spinner21);
            sp22 = view.findViewById(R.id.spinner22);
            LocalTime a, b;
            a = LocalTime.of(sp11.getSelectedItemPosition(), sp12.getSelectedItemPosition());
            b = LocalTime.of(sp21.getSelectedItemPosition(), sp22.getSelectedItemPosition());
            Waktu w = new Waktu(a.toString(), b.toString());

            listKamis.add(w);
        }
        for(int i=0;i<l5.getChildCount();i++) {
            AppCompatSpinner sp11, sp12, sp21, sp22;

            View view = l5.getChildAt(i);
            sp11 = view.findViewById(R.id.spinner11);
            sp12 = view.findViewById(R.id.spinner12);
            sp21 = view.findViewById(R.id.spinner21);
            sp22 = view.findViewById(R.id.spinner22);
            LocalTime a, b;
            a = LocalTime.of(sp11.getSelectedItemPosition(), sp12.getSelectedItemPosition());
            b = LocalTime.of(sp21.getSelectedItemPosition(), sp22.getSelectedItemPosition());
            Waktu w = new Waktu(a.toString(), b.toString());

            listJumat.add(w);
        }
        for(int i=0;i<l6.getChildCount();i++) {
            AppCompatSpinner sp11, sp12, sp21, sp22;

            View view = l6.getChildAt(i);
            sp11 = view.findViewById(R.id.spinner11);
            sp12 = view.findViewById(R.id.spinner12);
            sp21 = view.findViewById(R.id.spinner21);
            sp22 = view.findViewById(R.id.spinner22);
            LocalTime a, b;
            a = LocalTime.of(sp11.getSelectedItemPosition(), sp12.getSelectedItemPosition());
            b = LocalTime.of(sp21.getSelectedItemPosition(), sp22.getSelectedItemPosition());
            Waktu w = new Waktu(a.toString(), b.toString());

            listSabtu.add(w);
        }
        for(int i=0;i<l7.getChildCount();i++) {
            AppCompatSpinner sp11, sp12, sp21, sp22;

            View view = l7.getChildAt(i);
            sp11 = view.findViewById(R.id.spinner11);
            sp12 = view.findViewById(R.id.spinner12);
            sp21 = view.findViewById(R.id.spinner21);
            sp22 = view.findViewById(R.id.spinner22);
            LocalTime a,b;
            a = LocalTime.of(sp11.getSelectedItemPosition(), sp12.getSelectedItemPosition());
            b = LocalTime.of(sp21.getSelectedItemPosition(), sp22.getSelectedItemPosition());
            Waktu w = new Waktu(a.toString(), b.toString());

            listMinggu.add(w);
        }
        Data data=new Data("",sessionManager.getUserDetail().get(SessionManager.EMAIL),nama,listSenin,listSelasa,listRabu,listKamis,listJumat,listSabtu,listMinggu);
        kirimDatabase(data);
        Intent intent=new Intent(this, LayarUtama.class);
        startActivity(intent);
        finish();
    }
    private void kirimDatabase(Data data) {
        boolean kondisi=true;
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<CreateJadwal> call=apiInterface.createJadwalResponse("1",id_room,data.nama,sessionManager.getUserDetail().get(SessionManager.EMAIL),cacah(data.senin),cacah(data.selasa),cacah(data.rabu),cacah(data.kamis),cacah(data.jumat),cacah(data.sabtu),cacah(data.minggu));
        call.enqueue(new Callback<CreateJadwal>() {
            @Override
            public void onResponse(Call<CreateJadwal> call, Response<CreateJadwal> response) {
                Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<CreateJadwal> call, Throwable t) {
                Toast.makeText(MainActivity.this,"gagal kirimkan data..."+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String cacah(ArrayList<Waktu> waktus){
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<waktus.size();i++){
            if(i!=waktus.size()-1){
                stringBuilder.append(waktus.get(i).bawah+","+waktus.get(i).atas+",");
            }else{
                stringBuilder.append(waktus.get(i).bawah+","+waktus.get(i).atas);
            }
        }
        return stringBuilder.toString();
    }

    private boolean ceknama(String toString) {
        ArrayList<Integer> data=new ArrayList<Integer>();
        char[] h=toString.toCharArray();
        for(int u=0;u<h.length;u++){
            data.add(((int)h[u]));
        }
        System.out.println("batas");
        for(int i=data.size()-1;i>=0;i--){
            if(data.get(i)<65){
                data.remove(i);
            }else if(data.get(i)>90 && data.get(i)<97){
                data.remove(i);
            }
        }
        if(data.size()!=0){
            return true;
        }else{
            return false;
        }
    }
}
