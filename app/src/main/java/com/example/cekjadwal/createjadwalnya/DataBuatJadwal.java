package com.example.cekjadwal.createjadwalnya;
import com.google.gson.annotations.SerializedName;
public class DataBuatJadwal {

    @SerializedName("kamis")
    private String kamis;

    @SerializedName("nama")
    private String nama;

    @SerializedName("senin")
    private String senin;

    @SerializedName("rabu")
    private String rabu;

    @SerializedName("sabtu")
    private String sabtu;

    @SerializedName("minggu")
    private String minggu;

    @SerializedName("jumat")
    private String jumat;

    @SerializedName("selasa")
    private String selasa;

    public void setKamis(String kamis){
        this.kamis = kamis;
    }

    public String getKamis(){
        return kamis;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }

    public void setSenin(String senin){
        this.senin = senin;
    }

    public String getSenin(){
        return senin;
    }

    public void setRabu(String rabu){
        this.rabu = rabu;
    }

    public String getRabu(){
        return rabu;
    }

    public void setSabtu(String sabtu){
        this.sabtu = sabtu;
    }

    public String getSabtu(){
        return sabtu;
    }

    public void setMinggu(String minggu){
        this.minggu = minggu;
    }

    public String getMinggu(){
        return minggu;
    }

    public void setJumat(String jumat){
        this.jumat = jumat;
    }

    public String getJumat(){
        return jumat;
    }

    public void setSelasa(String selasa){
        this.selasa = selasa;
    }

    public String getSelasa(){
        return selasa;
    }
}
