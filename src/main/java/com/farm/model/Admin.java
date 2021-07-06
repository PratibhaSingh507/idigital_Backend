package com.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "adlogin")
public class Admin {

	@Id
	@NotNull
	@Column(name = "Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;


	@NotBlank(message = "Email Id is mandatory")
	@Email
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+", message = "enter email in valid format")
	@Column(name = "email" , unique = true)
	private String email;

	@NotNull
	@Column(name = "password")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "Enter the password containing at least one digit,one uppercase letter,one lowercase letter,special symbol,no whitespace,atleast upto 8 places")
	private String password;
	
	
	
	

	public Admin() {
		super();
	}

	public Admin(long id,
			@NotBlank(message = "Email Id is mandatory") @Email @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z]+.[a-zA-A]+", message = "enter email in valid format") String email,
			@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "Enter the password containing at least one digit,one uppercase letter,one lowercase letter,special symbol,no whitespace,atleast upto 8 places") String password) {
		super();
		Id = id;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [Id=" + Id + ", email=" + email + ", password=" + password + "]";
	}
	
}
