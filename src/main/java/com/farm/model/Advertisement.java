package com.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author Ashwini
 *
 */
@Entity
@Table(name="Advertisement")

public class Advertisement {

	@Id
	@Column(name="aid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	
	@NotNull
	@Size(min=3, message="Name should have atleast 3 characters")
	@Column(name="cropname")
	private String cropname;
	
	@NotNull
	@Size(min=1,max=10000 )
	@Column(name="quantity")
	private String quantity;
	
	@NotNull
	@Pattern(regexp="^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",message="Enter Phone NUmber with only 10 digit country code")
	@Column(name="contact")
	private String contact;
	@NotNull
	@Size(min=3, message="Name should have atleast 3 characters")
	@Column(name="name")
	private String name;
	@NotNull
	@Column(name="unit")
	private String unit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "dealerId"), name = "dealerId")
	@JsonIgnore
	private Dealer dealer;
	
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getCropname() {
		return cropname;
	}

	public void setCropname(String cropname) {
		this.cropname = cropname;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Dealer getDealer() {
		return dealer;
	}
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Advertisement(int aid, String cropname, String quantity,
			@NotNull @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Enter Phone NUmber with only 10 digit country code") String contact,
			String name, String unit, Dealer dealer) {
		super();
		this.aid = aid;
		this.cropname = cropname;
		this.quantity = quantity;
		this.contact = contact;
		this.name = name;
		this.unit = unit;
		this.dealer = dealer;
	}

	public Advertisement() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Advertisement [aid=" + aid + ", cropname=" + cropname + ", quantity=" + quantity + ", contact="
				+ contact + ", name=" + name + ", unit=" + unit + ", dealer=\" + dealer + \"]";
	}
	

}