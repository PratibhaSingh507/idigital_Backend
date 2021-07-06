package com.farm.model;

import javax.persistence.Column;
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
 * Dealer Model Class
 * @author Nisha
 *
 */
@Entity
@Table(name="dealers")
public class Dealer {

	@Id
	@Column(name="dealer_Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int dealerId;
	
	@NotNull
	@Size(min=3, message="Name should have atleast 3 characters")
	@Column(name="dealer_name")
	private String dealerName;
	
	@NotNull
	@Size(min=10,max=10)
	//@Pattern(regexp="^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",message="Enter Phone NUmber with only 10 digit country code")
	@Column(name="dealer_contactNumber")
	private String dealerContactNumber;
	
	@NotBlank(message="Email_Id is mandatory!!")
	@Email
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+",message = "enter email in valid format")
	@Column(name="emailId")
	private String dealerEmailId;
	
	@NotNull
	//@Pattern(regexp="(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}",message = "Enter the password containing at least one digit,one uppercase letter,one lowercase letter,special symbol,no whitespace,atleast upto 8 places")
	@Column(name="password")
	private String dealerPassword;
	
//	@OneToMany(mappedBy = "deal]er", cascade = CascadeType.ALL)
//	@JsonIgnoreProperties({"dealer","hibernateLazyInitializer", "handler"})
//	private List<Advertisement> complains;

	public Dealer() {
		super();
	}

	public Dealer(int dealerId,
			@NotNull @Size(min = 3, message = "Name should have atleast 3 characters") String dealerName,
			@NotNull @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Enter Phone NUmber with only 10 digit country code") String dealerContactNumber,
			@NotNull(message = "Email_Id is mandatory!!") @Email @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+", message = "enter email in valid format") String dealerEmailId,
			@NotNull String dealerPassword) {
		super();
		this.dealerId = dealerId;
		this.dealerName = dealerName;
		this.dealerContactNumber = dealerContactNumber;
		this.dealerEmailId = dealerEmailId;
		this.dealerPassword = dealerPassword;
	}

	public int getDealerId() {
		return dealerId;
	}

	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerContactNumber() {
		return dealerContactNumber;
	}

	public void setDealerContactNumber(String dealerContactNumber) {
		this.dealerContactNumber = dealerContactNumber;
	}

	public String getDealerEmailId() {
		return dealerEmailId;
	}

	public void setDealerEmailId(String dealerEmailId) {
		this.dealerEmailId = dealerEmailId;
	}

	public String getDealerPassword() {
		return dealerPassword;
	}

	public void setDealerPassword(String dealerPassword) {
		this.dealerPassword = dealerPassword;
	}

	@Override
	public String toString() {
		return "Dealer [dealerId=" + dealerId + ", dealerName=" + dealerName + ", dealerContactNumber="
				+ dealerContactNumber + ", dealerEmailId=" + dealerEmailId + ", dealerPassword=" + dealerPassword + "]";
	}
	
}

