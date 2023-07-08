package com.example.cekjadwal.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//    private static final String BASE_URL="http://192.168.1.86/cek_jadwal/";
    private static final String BASE_URL="https://erickdelenia79.my.id/";

//    private static final String BASE_URL="http://192.168.43.59/cek_jadwal/";

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}


