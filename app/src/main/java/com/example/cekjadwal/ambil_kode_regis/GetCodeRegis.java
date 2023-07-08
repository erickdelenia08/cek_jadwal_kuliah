package com.example.cekjadwal.ambil_kode_regis;

import com.google.gson.annotations.SerializedName;

public class GetCodeRegis {
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private boolean status;
    @SerializedName("kode_akses")
    private String kode_akses;
    public String getMessage(){
        return message;
    }
    public String getKode_akses(){
        return this.kode_akses;
    }

    public boolean isStatus(){
        return status;
    }
}
