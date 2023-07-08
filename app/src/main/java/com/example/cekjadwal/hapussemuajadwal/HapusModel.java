package com.example.cekjadwal.hapussemuajadwal;

import com.google.gson.annotations.SerializedName;

public class HapusModel{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("status")
	private boolean status;

	public String getPesan(){
		return pesan;
	}

	public boolean isStatus(){
		return status;
	}
}