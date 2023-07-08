package com.example.cekjadwal.lojin;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("tipe")
    private String tipe;

    @SerializedName("nama")
    private String nama;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("email")
    private String email;

    public String getTipe() {
        return tipe;
    }
    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }

    public String getUserId(){
        return userId;
    }

    public String getEmail(){
        return email;
    }
}