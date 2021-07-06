package com.farm.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

	@Entity
	@Table(name="complaint")
	public class Complaint {

		@Id
		@Column(name="complaint_id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int cid;
		
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm",  timezone="Asia/Kolkata")
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		@JsonSerialize(using = LocalDateTimeSerializer.class)
	    private LocalDateTime c_date;
		
		@Column(name="c_message")
		private String cmessage;
		
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="farmerId", referencedColumnName = "farmerId",nullable = true)
		@JsonIgnoreProperties({"complains","hibernateLazyInitializer", "handler"})
		@LazyCollection(LazyCollectionOption.FALSE)
		private Farmer farmer;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="dealer_Id", referencedColumnName = "dealer_Id",nullable = true)
		@JsonIgnoreProperties({"complains","hibernateLazyInitializer", "handler"})
		@LazyCollection(LazyCollectionOption.FALSE)
		private Dealer dealer;
		
		
		public int getCid() {
			return cid;
		}
		public void setCid(int cid) {
			this.cid = cid;
		}
		public String getCmessage() {
			return cmessage;
		}
		public void setCmessage(String cmessage) {
			this.cmessage = cmessage;
		}
		
		public LocalDateTime getC_date() {
			return c_date;
		}
		public void setC_date(LocalDateTime c_date) {
			this.c_date = c_date;
		}
		public Farmer getFarmer() {
			return farmer;
		}
		public void setFarmer(Farmer farmer) {
			this.farmer = farmer;
		}
		public Dealer getDealer() {
			return dealer;
		}
		public void setDealer(Dealer dealer) {
			this.dealer = dealer;
		}
		
		public Complaint() {
			super();
		}
		
		public Complaint(int cid, LocalDateTime c_date, String cmessage, Farmer farmer, Dealer dealer ) {
			super();
			this.cid = cid;
			this.c_date = c_date;
			this.cmessage = cmessage;
			this.farmer = farmer;
			this.dealer = dealer;
		}
		
		@Override
		public String toString() {
			return "Complaint [cid=" + cid + ", c_date=" + c_date + ", cmessage=" + cmessage + ", farmer=" + farmer
					+ ", dealer=" + dealer + "]";
		}
		
	}



