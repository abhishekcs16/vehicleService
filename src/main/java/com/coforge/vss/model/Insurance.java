package com.coforge.vss.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="insurance_details")
public class Insurance  {
    
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long insuranceNo;
	
	private String insuranceDate;
	
	private String insuranceAmount;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "vehicleRegNo")
	private Vehicle vehicle; 
	
	
	public Insurance() {
		// TODO Auto-generated constructor stub
	}
	


	public Insurance(String insuranceDate, String insuranceAmount, Vehicle vehicle) {
		super();
		this.insuranceDate = insuranceDate;
		this.insuranceAmount = insuranceAmount;
		this.vehicle = vehicle;
	}



	public Long getInsuranceNo() {
		return insuranceNo;
	}


	public void setInsuranceNo(Long id) {
		this.insuranceNo = id;
	}


	public String getInsuranceDate() {
		return insuranceDate;
	}


	public void setInsuranceDate(String insuranceDate) {
		this.insuranceDate = insuranceDate;
	}


	public String getInsuranceAmount() {
		return insuranceAmount;
	}


	public void setInsuranceAmount(String insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}


	public Vehicle getVehicle() {
		return vehicle;
	}


	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	


	

    
	
}