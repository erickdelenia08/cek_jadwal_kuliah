package com.example.cekjadwal.DataAwal;
import android.os.Parcel;
import android.os.Parcelable;
import com.example.cekjadwal.Waktu;
import com.example.cekjadwal.cek.PW;
import java.util.ArrayList;
import java.util.Date;

public class Profil implements Parcelable{
    Data first;
    public PW ppp;

    public Profil(){
        ppp=new PW();
    }

    protected Profil(Parcel in) {
        first=in.readParcelable(Data.class.getClassLoader());
        ppp=in.readParcelable(PW.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(first,flags);
        dest.writeParcelable(ppp,flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Profil> CREATOR = new Creator<Profil>() {
        @Override
        public Profil createFromParcel(Parcel in) {
            return new Profil(in);
        }

        @Override
        public Profil[] newArray(int size) {
            return new Profil[size];
        }
    };
    public void tambahData(String id,String email,String nama, ArrayList<Waktu> a, ArrayList<Waktu> b, ArrayList<Waktu> c, ArrayList<Waktu> d, ArrayList<Waktu> e, ArrayList<Waktu> f, ArrayList<Waktu> g){
        Data baru=new Data(id,email,nama,a,b,c,d,e,f,g);
        if(first==null){
            first=baru;
        }else{
            baru.next=first;
            first=baru;
        }
    }
    public void masukkan(Profil pro){
        first.next=pro.getListData().get(0);
    }
    public void insertToCeck() {
        try{
            Data bantu=first;
            while(bantu!=null) {
                ppp.insert(Integer.parseInt(bantu.id),bantu.nama, bantu.senin,bantu.selasa,bantu.rabu, bantu.kamis, bantu.jumat, bantu.sabtu, bantu.minggu);

                bantu=bantu.next;
            }
            ppp.disp();
        }catch(NullPointerException e){
            System.out.println(e.getMessage()+first.nama);
        }
    }
    public void disp(){
        Data bantu=first;
        while(bantu!=null){
            bantu.tampil();
            bantu=bantu.next;
        }
    }
    public PW getDataPW(){
        if(ppp==null){
            insertToCeck();
        }
        return ppp;
    }
    public void hapusLink(String nama){
        Data current=first;
        Data previous=first;
        while(!current.nama.equals(nama)) {
            if(current.next==null){
                break;
            }else {
                previous=current;
                current=current.next;
            }
        }
        if(current==first)
            first=first.next;
        else
            previous.next=current.next;

    }
    public void ubahLink(String nama){
        Data current=first;
        while(!current.nama.equals(nama)) {
            current=current.next;
        }
        if(current.nama.equals(nama)){
            current.nama=nama;
        }
    }
    public void ubahJam(String nama,int hari,ArrayList<Waktu> waktus){
        Data current=first;
        while(!current.nama.equals(nama)) {
            current=current.next;
        }
        if(current.nama.equals(nama) && hari==1){
            current.senin=waktus;
        }
        else if(current.nama.equals(nama) && hari==2){
            current.selasa=waktus;
        }
        else if(current.nama.equals(nama) && hari==3){
            current.rabu=waktus;
        }
        else if(current.nama.equals(nama) && hari==4){
            current.kamis=waktus;
        }else if(current.nama.equals(nama) && hari==5){
            current.jumat=waktus;
        }else if(current.nama.equals(nama) && hari==6){
            current.sabtu=waktus;
        }else if(current.nama.equals(nama) && hari==7){
            current.minggu=waktus;
        }
    }
    public ArrayList<Data> getListData(){
        Data bantu=first;
        ArrayList<Data> data=new ArrayList<Data>();
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
    public int getListCount(){
        Data bantu=first;
        int a=0;
        try{
            while(bantu!=null){
                a++;
                bantu=bantu.next;
            }
        }catch (NullPointerException e){
            System.out.println(e.getLocalizedMessage()+"HAHAHAHAHAIAKHSLAKHSLKAHSA_*)*_)*_)");
        }
        return a;
    }
    public void setfilterProfil(String filter){
        Data bantu=first;
        while(bantu!=null){
            if(!bantu.nama.contains(filter)){
                hapusLink(bantu.nama);
            }
            bantu=bantu.next;
        }
    }

}

