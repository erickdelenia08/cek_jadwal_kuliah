package com.example.cekjadwal.cek;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.cekjadwal.Waktu;

import java.util.ArrayList;

public class Time implements Parcelable {
    public int id;
    public String nama;
    public ArrayList<Waktu> senin,selasa,rabu,kamis,jumat,sabtu,minggu;
    Time next;
    public Time(int id,String nama,ArrayList<Waktu> senin,ArrayList<Waktu> selasa,ArrayList<Waktu> rabu,ArrayList<Waktu> kamis,ArrayList<Waktu> jumat,ArrayList<Waktu> sabtu,ArrayList<Waktu> minggu) {
        this.id=id;this.nama=nama;this.senin=senin;this.selasa=selasa;this.rabu=rabu;this.kamis=kamis;this.jumat=jumat;this.sabtu=sabtu;this.minggu=minggu;
    }

    protected Time(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        next = in.readParcelable(Time.class.getClassLoader());
        senin=in.createTypedArrayList(Waktu.CREATOR);
        selasa=in.createTypedArrayList(Waktu.CREATOR);
        rabu=in.createTypedArrayList(Waktu.CREATOR);
        kamis=in.createTypedArrayList(Waktu.CREATOR);
        jumat=in.createTypedArrayList(Waktu.CREATOR);
        sabtu=in.createTypedArrayList(Waktu.CREATOR);
        minggu=in.createTypedArrayList(Waktu.CREATOR);

    }

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };

    public void disp() {
        System.out.println(id);
        System.out.println(nama);
        System.out.println("jam hari senin");
        for(int i=0;i<senin.size();i++){
            System.out.print(senin.get(i).bawah);
            System.out.println(" - "+senin.get(i).atas);
        }
        System.out.println("jam hari selasa");
        for(int i=0;i<selasa.size();i++){
            System.out.print(selasa.get(i).bawah);
            System.out.println(" - "+selasa.get(i).atas);
        }
        System.out.println("jam hari rabu".toUpperCase());
        for(int i=0;i<rabu.size();i++){
            System.out.print(rabu.get(i).bawah);
            System.out.println(" - "+rabu.get(i).atas);
        }
        System.out.println("jam hari kamis".toUpperCase());
        for(int i=0;i<kamis.size();i++){
            System.out.print(kamis.get(i).bawah);
            System.out.println(" - "+kamis.get(i).atas);
        }
        System.out.println("jam hari jumat".toUpperCase());
        for(int i=0;i<jumat.size();i++){
            System.out.print(jumat.get(i).bawah);
            System.out.println(" - "+jumat.get(i).atas);
        }
        System.out.println("jam hari sabtu".toUpperCase());
        for(int i=0;i<sabtu.size();i++){
            System.out.print(sabtu.get(i).bawah);
            System.out.println(" - "+sabtu.get(i).atas);
        }
        System.out.println("jam hari minggu".toUpperCase());
        for(int i=0;i<minggu.size();i++){
            System.out.print(minggu.get(i).bawah);
            System.out.println(" - "+minggu.get(i).atas);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeParcelable(next, flags);
        dest.writeTypedList(senin);
        dest.writeTypedList(selasa);
        dest.writeTypedList(rabu);
        dest.writeTypedList(kamis);
        dest.writeTypedList(jumat);
        dest.writeTypedList(sabtu);
        dest.writeTypedList(minggu);
    }

}