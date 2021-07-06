package com.farm.model ;

import java.util.List;

public class MessageEntity {
	
	private String message;
	private int status;
	private List<Farmer> farmerLst;
	
	
	public MessageEntity() {
		super();
		
	}



	public MessageEntity(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}





	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	


	public List<Farmer> getFarmerLst() {
		return farmerLst;
	}

	public void setFarmerLst(List<Farmer> farmerLst) {
		this.farmerLst = farmerLst;
	}




	@Override
	public String toString() {
		return "MessageEntity [message=" + message + ", status=" + status + "]";
	}
	
	
	
	

}
