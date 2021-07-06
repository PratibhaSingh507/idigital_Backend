package com.farm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



/**
 * This is Farmer Entity class
 * @author Manoj Chuadhary
 * 
 */

@Entity
@Table(name="Farmer1")
public class Farmer {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int farmerId;
	@NotNull
	private String farmerName;
	@NotNull
	@NotBlank(message = "Email Id is mandatory")
	
	//@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+", message = "enter email in valid format")
	private String emailId;
	@NotBlank(message = "password is mandatory")
	@NotNull
	@Size(min=8,max=16, message="Password must be equal  or greater than 8")
	private String password;
	@NotBlank(message = "location is mandatory")
	@NotNull
	private String  location;
	@NotBlank(message = "Crop name is mandatory")
	@NotNull
	private String cropName;
	@NotNull
	@NotBlank(message = "Contact  is mandatory")
	@Pattern(regexp="(^$|[0-9]{10})")
	private String contactNo;
	
	public Farmer() {
		super();
		
	}
	public Farmer(int farmerId, String farmerName, String emailId, String password, String location, String cropName,
			String contactNo) {
		super();
		this.farmerId = farmerId;
		this.farmerName = farmerName;
		this.emailId = emailId;
		this.password = password;
		this.location = location;
		this.cropName = cropName;
		this.contactNo = contactNo;
	}
	public int getFarmerId() {
		return farmerId;
	}
	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}
	public String getFarmerName() {
		return farmerName;
	}
	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	@Override
	public String toString() {
		return "Employee [farmerId=" + farmerId + ", farmerName=" + farmerName + ", emailId=" + emailId + ", password="
				+ password + ", location=" + location + ", cropName=" + cropName + ", contactNo=" + contactNo + "]";
	}
	
	
	
	

}
