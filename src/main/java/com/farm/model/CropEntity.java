package com.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import com.sun.istack.NotNull;

@Entity
@Table(name="crops")
public class CropEntity {
	
		@Id
		@NotNull
		@Column(name = "Id", nullable = false)
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long cropId;
		@NotNull
		private long farmerId;
		@NotBlank(message = "Name is required")
		@Pattern(regexp="(?:[A-Z][a-z.-]+[ ]?)+",message="Enter Valid Name. Starting Letter Should Be Capital")
		private String farmerName;
		@NotNull
		@Size(min=3, message="Name should have atleast 3 characters")
		private String cropName;
		@NotNull
		public long price;
		
		public CropEntity() {
			super();
			// TODO Auto-generated constructor stub
		}
		public long getCropId() {
			return cropId;
		}
		public void setCropId(long cropId) {
			this.cropId = cropId;
		}
		public long getFarmerId() {
			return farmerId;
		}
		public void setFarmerId(long farmerId) {
			this.farmerId = farmerId;
		}
		public String getFarmerName() {
			return farmerName;
		}
		public void setFarmerName(String farmerName) {
			this.farmerName = farmerName;
		}
		public String getCropName() {
			return cropName;
		}
		public void setCropName(String cropName) {
			this.cropName = cropName;
		}
		public long getPrice() {
			return price;
		}
		public void setPrice(long price) {
			this.price = price;
		}
		@Override
		public String toString() {
			return "CropEntity [cropId=" + cropId + ", farmerId=" + farmerId + ", farmerName=" + farmerName + ", cropName="
					+ cropName + ", price=" + price + "]";
		}
		

	}

