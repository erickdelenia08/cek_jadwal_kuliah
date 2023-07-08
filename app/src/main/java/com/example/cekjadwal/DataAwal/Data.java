package com.example.cekjadwal.DataAwal;



import android.os.Parcel;
import android.os.Parcelable;

import com.example.cekjadwal.Waktu;

import java.util.ArrayList;


public class Data implements Parcelable{
    public String id;
    public String email;
    public String nama;
    public ArrayList<Waktu> senin;
    public ArrayList<Waktu> selasa;
    public ArrayList<Waktu> rabu;
    public ArrayList<Waktu> kamis;
    public ArrayList<Waktu> jumat;
    public ArrayList<Waktu> sabtu;
    public ArrayList<Waktu> minggu;
    Data next;
    public Data(String id,String email,String nama,ArrayList<Waktu> a,ArrayList<Waktu> b,ArrayList<Waktu> c,ArrayList<Waktu> d,ArrayList<Waktu> e,ArrayList<Waktu> f,ArrayList<Waktu> g){
        this.id=id;this.email=email;this.nama=nama;this.senin=a;selasa=b;rabu=c;kamis=d;jumat=e;sabtu=f;minggu=g;
    }

    protected Data(Parcel in) {
        id=in.readString();
        email = in.readString();
        nama = in.readString();
        next = in.readParcelable(Data.class.getClassLoader());
        senin=in.createTypedArrayList(Waktu.CREATOR);
        selasa=in.createTypedArrayList(Waktu.CREATOR);
        rabu=in.createTypedArrayList(Waktu.CREATOR);
        kamis=in.createTypedArrayList(Waktu.CREATOR);
        jumat=in.createTypedArrayList(Waktu.CREATOR);
        sabtu=in.createTypedArrayList(Waktu.CREATOR);
        minggu=in.createTypedArrayList(Waktu.CREATOR);
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public void tampil(){
        System.out.println("NAMA : "+nama);
        System.out.println("Senin panjangnya"+senin.size());
        for(int i=0;i<senin.size();i++){
            System.out.print(senin.get(i).atas);
            System.out.print(" - ");
            System.out.print(senin.get(i).bawah);
            System.out.println();
        }
        System.out.println("Selasa panjjangnya"+selasa.size());
        for(int i=0;i<selasa.size();i++){
            System.out.print(selasa.get(i).atas);
            System.out.print(" - ");
            System.out.print(selasa.get(i).bawah);
            System.out.println();
        }
        System.out.println("rabu"+rabu.size());
        for(int i=0;i<rabu.size();i++){
            System.out.print(rabu.get(i).atas);
            System.out.print(" - ");
            System.out.print(rabu.get(i).bawah);
            System.out.println();
        }

        System.out.println("kamis"+kamis.size());
        for(int i=0;i<kamis.size();i++){
            System.out.print(kamis.get(i).atas);
            System.out.print(" - ");
            System.out.print(kamis.get(i).bawah);
            System.out.println();
        }
        System.out.println("jumat"+jumat.size());
        for(int i=0;i<jumat.size();i++){
            System.out.print(jumat.get(i).atas);
            System.out.print(" - ");
            System.out.print(jumat.get(i).bawah);
            System.out.println();
        }
        System.out.println("sabtu"+sabtu.size());
        for(int i=0;i<sabtu.size();i++){
            System.out.print(sabtu.get(i).atas);
            System.out.print(" - ");
            System.out.print(sabtu.get(i).bawah);
            System.out.println();
        }
        System.out.println("minggu"+minggu.size());
        for(int i=0;i<minggu.size();i++){
            System.out.print(minggu.get(i).atas);
            System.out.print(" - ");
            System.out.print(minggu.get(i).bawah);
            System.out.println();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
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