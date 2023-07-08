package com.example.cekjadwal.datakres;


import com.example.cekjadwal.DataAwal.Data;
import com.example.cekjadwal.Waktu;

import java.util.ArrayList;

public class DetailPerson {
    Person first;
    public void add(int id, String nama, Waktu a, int hari) {
        if(first==null) {
            Person baru=new Person(id,nama,a,hari);
            first=baru;
        }else {
            Person bantu=first;
            while(bantu!=null) {
                if(bantu.id==id) {
                    break;
                }
                bantu=bantu.next;
            }
            if(bantu!=null && bantu.id==id) {
                bantu.jamKres.add(a);
            }else if(bantu==null|| bantu.id!=id){
                Person baru=new Person(id,nama,a,hari);
                baru.next=first;
                first=baru;
            }

        }
    }
    public void disp() {
        Person b=first;
        while(b!=null){
            b.display();
            b=b.next;
        }
    }
    public ArrayList<Person> getListData(){
        Person bantu=first;
        ArrayList<Person> data=new ArrayList<Person>();
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
        Person bantu=first;
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

}