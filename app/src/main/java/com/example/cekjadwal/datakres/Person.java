package com.example.cekjadwal.datakres;


import com.example.cekjadwal.Waktu;

import java.util.ArrayList;

public class Person{
    public int id;
    public String nama;
    public int hari;
    public ArrayList<Waktu> jamKres;
    Person next;
    public Person(int id,String nama,Waktu a,int hari){
        this.id=id;
        this.hari=hari;
        this.nama=nama;
        ArrayList<Waktu> ar=new ArrayList<Waktu>();
        ar.add(a);
        this.jamKres=ar;
    }
    public void display() {
        System.out.println("Nama : "+nama);
        System.out.println("id : "+id);
        System.out.println(cekHari(this.hari));
        System.out.println("Jam Kress sizenya yaitu "+ jamKres.size() +"  \n");
        for(int i=0;i<jamKres.size();i++) {
            System.out.println(jamKres.get(i).bawah.toString()+"-"+jamKres.get(i).atas.toString());
        }
    }
    public String cekHari(int hari) {
        String a=new String();
        if(hari==1) {
            a="Senin";
        }else if(hari==2) {
            a="Selasa";
        }else if(hari==3) {
            a="Rabu";
        }else if(hari==4) {
            a="Kamis";
        }else if(hari==5) {
            a="Jumat";
        }else if(hari==6) {
            a="Sabtu";
        }else if(hari==7) {
            a="Minggu";
        }
        return a;
    }
}