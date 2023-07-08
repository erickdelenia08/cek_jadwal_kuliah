package com.example.cekjadwal.createjadwalnya;


import com.google.gson.annotations.SerializedName;

public class CreateJadwal{

    @SerializedName("data")
    private DataBuatJadwal dataBuatJadwal;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public void setDataBuatJadwal(DataBuatJadwal dataBuatJadwal){
        this.dataBuatJadwal = dataBuatJadwal;
    }

    public DataBuatJadwal getDataBuatJadwal(){
        return dataBuatJadwal;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }
}
