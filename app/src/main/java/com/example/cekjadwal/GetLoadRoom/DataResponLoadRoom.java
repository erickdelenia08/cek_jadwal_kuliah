package com.example.cekjadwal.GetLoadRoom;

import com.google.gson.annotations.SerializedName;

public class DataResponLoadRoom {

	@SerializedName("kode_akses")
	private String kodeAkses;

	@SerializedName("nama_room")
	private String namaRoom;

	@SerializedName("id")
	private String id;

	public String getKodeAkses(){
		return kodeAkses;
	}

	public String getNamaRoom(){
		return namaRoom;
	}

	public String getId(){
		return id;
	}
}