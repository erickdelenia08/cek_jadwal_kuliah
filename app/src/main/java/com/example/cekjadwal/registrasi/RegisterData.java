package com.example.cekjadwal.registrasi;


import com.google.gson.annotations.SerializedName;

public class RegisterData {

    @SerializedName("nama")
    private String nama;

    @SerializedName("tipe")
    private String tipe;

    @SerializedName("email")
    private String email;

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }

    public String getTipe(){
        return tipe;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }
}