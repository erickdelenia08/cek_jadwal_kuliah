package com.example.cekjadwal.GetLoadRoom;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLoadRoom{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataResponLoadRoom> data;

	@SerializedName("status")
	private boolean status;

	public String getPesan(){
		return pesan;
	}

	public List<DataResponLoadRoom> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}
}