package com.example.cekjadwal.API;

import com.example.cekjadwal.DataItem_create_room.ResponseCreateRoom;
import com.example.cekjadwal.GetLoadRoom.ResponseLoadRoom;
import com.example.cekjadwal.ambil_kode_regis.GetCodeRegis;
import com.example.cekjadwal.ambil_kode_regis.SetCodeRegis;
import com.example.cekjadwal.createjadwalnya.CreateJadwal;
import com.example.cekjadwal.lojin.Login;
import com.example.cekjadwal.registrasi.Register;
import com.example.cekjadwal.semuaData.AmbilJadwal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface  ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("tipe") String tipe,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("cek_login.php")
    Call<Login> cekloginResponse(
            @Field("tipe") String tipe,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("tipe") String tipe,
            @Field("email") String email,
            @Field("password") String password,
            @Field("nama") String nama
    );

    @FormUrlEncoded
    @POST("create_kode_register.php")
    Call<SetCodeRegis> sentAskCodeRegis(
            @Field("email_user") String email
    );

    @FormUrlEncoded
    @POST("delete_kode_regis_satusatu.php")
    Call<SetCodeRegis> deleteCodeRegis_byone(
            @Field("email_user") String email
    );

    @FormUrlEncoded
    @POST("get_one_kode_akses.php")
    Call<GetCodeRegis> getCodeRegisUser(
            @Field("email_user") String email_user
    );

    @FormUrlEncoded
    @POST("createJadwal.php")
    Call<CreateJadwal> createJadwalResponse(
            @Field("tipe") String tipe,
            @Field("id_room") String id_room,
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("senin") String senin,
            @Field("selasa") String selasa,
            @Field("rabu") String rabu,
            @Field("kamis") String kamis,
            @Field("jumat") String jumat,
            @Field("sabtu") String sabtu,
            @Field("minggu") String minggu
    );

    @GET("ambilJadwal.php")
    Call<AmbilJadwal> ambilJadwal();

    @FormUrlEncoded
    @POST("ambil_jadwal_by_id_room.php")
    Call<AmbilJadwal> ambilJadwalByIdRoom(
            @Field("id_room") String id_room
    );

    @FormUrlEncoded
    @POST("hapus_jadwal_by_id_room.php")
    Call<AmbilJadwal> deleteAllJadwalByIdRoom(
            @Field("id_room") String id_room
    );

    @FormUrlEncoded
    @POST("hapusJadwal.php")
    Call<AmbilJadwal> deleteJadwalSatuSatu(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("create_room.php")
    Call<ResponseCreateRoom> createMyRoom(
            @Field("nama_room") String nama_room,
            @Field("owner") String owner,
            @Field("kode_akses") String kode_akses
    );

    @FormUrlEncoded
    @POST("ambil_room_owner.php")
    Call<ResponseLoadRoom> getMyRoom(
            @Field("owner") String owner
    );

    @FormUrlEncoded
    @POST("delete_room.php")
    Call<ResponseLoadRoom> deleteMyRoomById(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("update_room.php")
    Call<ResponseLoadRoom> updateMyRoomById(
            @Field("id") String id,
            @Field("nama_room") String nama_room,
            @Field("kode_akses") String kode_akses
    );


}

