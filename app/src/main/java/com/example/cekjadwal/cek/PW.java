package com.example.cekjadwal.cek;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.example.cekjadwal.Waktu;
import com.example.cekjadwal.datakres.DetailPerson;

import java.time.LocalTime;
import java.util.ArrayList;

public class PW implements Parcelable{
    Time first;
    public PW(){

    }

    protected PW(Parcel in) {
        first=in.readParcelable(Time.class.getClassLoader());
    }

    public static final Creator<PW> CREATOR = new Creator<PW>() {
        @Override
        public PW createFromParcel(Parcel in) {
            return new PW(in);
        }

        @Override
        public PW[] newArray(int size) {
            return new PW[size];
        }
    };

    public void insert(int id,String nama, ArrayList<Waktu> senin, ArrayList<Waktu> selasa, ArrayList<Waktu> rabu, ArrayList<Waktu> kamis, ArrayList<Waktu> jumat, ArrayList<Waktu> sabtu, ArrayList<Waktu> minggu){
        Time baru=new Time(id,nama,senin,selasa,rabu,kamis,jumat,sabtu,minggu);
        if(first==null) {
            first=baru;
        }else {
            baru.next=first;
            first=baru;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean cek(LocalTime tujuan, int hari) {
        Time bantu=first;
        boolean kondisi=true;
        while(bantu!=null) {
            if(hari==1) {
                for(int i=0;i<bantu.senin.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.senin.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.senin.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.senin.get(i).bawah))){
                        kondisi=false;
                    }
                }
            }else if(hari==2) {
                for(int i=0;i<bantu.selasa.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.selasa.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.selasa.get(i).atas)))||tujuan.equals(LocalTime.parse(bantu.selasa.get(i).bawah))){
                        kondisi=false;
                    }
                }
            }else if(hari==3) {
                for(int i=0;i<bantu.rabu.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.rabu.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.rabu.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.rabu.get(i).bawah))){
                        kondisi=false;
                    }
                }
            }else if(hari==4) {
                for(int i=0;i<bantu.kamis.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.kamis.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.kamis.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.kamis.get(i).bawah))){
                        kondisi=false;
                    }
                }
            }else if(hari==5) {
                for(int i=0;i<bantu.jumat.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.jumat.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.jumat.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.jumat.get(i).bawah))){
                        kondisi=false;
                    }
                }
            }else if(hari==6) {
                for(int i=0;i<bantu.sabtu.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.sabtu.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.sabtu.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.sabtu.get(i).bawah))){
                        kondisi=false;
                    }
                }
            }else if(hari==7) {
                for(int i=0;i<bantu.minggu.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.minggu.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.minggu.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.minggu.get(i).bawah))){
                        kondisi=false;
                    }
                }
            }
            bantu=bantu.next;
        }
        return kondisi;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DetailPerson cekWaktu(LocalTime tujuan, int hari) {
        DetailPerson dPers = new DetailPerson();
        Time bantu=first;
        boolean kondisi=true;
        while(bantu!=null) {
            if(hari==1) {
                for(int i=0;i<bantu.senin.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.senin.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.senin.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.senin.get(i).bawah))){
                        dPers.add(bantu.id, bantu.nama,bantu.senin.get(i),hari);
                        kondisi=false;
                    }
                }
            }else if(hari==2) {
                for(int i=0;i<bantu.selasa.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.selasa.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.selasa.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.selasa.get(i).bawah))){
                        dPers.add(bantu.id, bantu.nama,bantu.selasa.get(i),hari);
                        kondisi=false;
                    }
                }
            }else if(hari==3) {
                for(int i=0;i<bantu.rabu.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.rabu.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.rabu.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.rabu.get(i).bawah))){
                        dPers.add(bantu.id, bantu.nama,bantu.rabu.get(i),hari);
                        kondisi=false;
                    }
                }
            }else if(hari==4) {
                for(int i=0;i<bantu.kamis.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.kamis.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.kamis.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.kamis.get(i).bawah))){
                        dPers.add(bantu.id, bantu.nama,bantu.kamis.get(i),hari);
                        kondisi=false;
                    }
                }
            }else if(hari==5) {
                for(int i=0;i<bantu.jumat.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.jumat.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.jumat.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.jumat.get(i).bawah))){
                        dPers.add(bantu.id, bantu.nama,bantu.jumat.get(i),hari);
                        kondisi=false;
                    }
                }
            }else if(hari==6) {
                for(int i=0;i<bantu.sabtu.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.sabtu.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.sabtu.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.sabtu.get(i).bawah))){
                        dPers.add(bantu.id, bantu.nama,bantu.sabtu.get(i),hari);
                        kondisi=false;
                    }
                }
            }else if(hari==7) {
                for(int i=0;i<bantu.minggu.size();i++){
                    if((tujuan.isAfter(LocalTime.parse(bantu.minggu.get(i).bawah)) && tujuan.isBefore(LocalTime.parse(bantu.minggu.get(i).atas)))|| tujuan.equals(LocalTime.parse(bantu.minggu.get(i).bawah))){
                        dPers.add(bantu.id, bantu.nama,bantu.minggu.get(i),hari);
                        kondisi=false;
                    }
                }
            }
            bantu=bantu.next;
        }
        return dPers;
    }

    public void disp() {
        Time bantu=first;
        while(bantu!=null) {
            bantu.disp();
            System.out.println();
            bantu=bantu.next;
        }
    }
    public int getLength() {
        int i=0;
        Time bantu=first;
        while(bantu!=null) {
            i++;
            bantu=bantu.next;
        }
        return i;
    }
    public ArrayList<Time> getListData(){
        Time bantu=first;
        ArrayList<Time> data=new ArrayList<Time>();
        try{
            while(bantu!=null){
                data.add(bantu);
                bantu=bantu.next;
            }
        }catch (NullPointerException e){
            System.out.println(e.getLocalizedMessage()+"HAHAHAHAHAIAKHSLAKHSLKAHSA_*)*_)*_)");
        }
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(first,flags);
    }
}
