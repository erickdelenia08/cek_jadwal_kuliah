package com.example.cekjadwal;
import android.os.Parcel;
import android.os.Parcelable;
public class Waktu implements Parcelable{
    public String bawah;
    public String atas;
    public Waktu(String bawah,String atas){
        this.atas=atas;this.bawah=bawah;
    }

    protected Waktu(Parcel in) {
        bawah = in.readString();
        atas = in.readString();
    }

    public static final Creator<Waktu> CREATOR = new Creator<Waktu>() {
        @Override
        public Waktu createFromParcel(Parcel in) {
            return new Waktu(in);
        }

        @Override
        public Waktu[] newArray(int size) {
            return new Waktu[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bawah);
        dest.writeString(atas);
    }
}
