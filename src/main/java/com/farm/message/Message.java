package com.farm.message;

import java.util.List;

import com.farm.model.Advertisement;

public class Message {
	private String resMessage;
	private List<Advertisement> advList;
	private int status;
	public String getResMessage() {
		return resMessage;
	}
	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}
	public List<Advertisement> getAdvList() {
		return advList;
	}
	public void setAdvList(List<Advertisement> advList) {
		this.advList = advList;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

}
