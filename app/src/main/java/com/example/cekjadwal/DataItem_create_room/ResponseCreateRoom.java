package com.example.cekjadwal.DataItem_create_room;

import com.google.gson.annotations.SerializedName;

public class ResponseCreateRoom{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}