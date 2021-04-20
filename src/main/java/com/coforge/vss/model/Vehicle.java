package com.coforge.vss.model;



import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="vehicle_details")
public class Vehicle{

	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long vehicleRegNo;
	
	
	private String vehicleModel;
	
	
	private String engineNo;
	
	
	private String chasisNo;
	
    @OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "id")
	private Customer customer;
    

	public Vehicle() {
		super();
	}


	public Vehicle(String vehicleModel, String engineNo, String chasisNo, Customer customer) {
		super();
		this.vehicleModel = vehicleModel;
		this.engineNo = engineNo;
		this.chasisNo = chasisNo;
		this.customer = customer;
	}

	
	public Long getVehicleRegNo() {
		return vehicleRegNo;
	}

	public void setVehicleRegNo(Long id) {
		this.vehicleRegNo = id;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getChasisNo() {
		return chasisNo;
	}

	public void setChasisNo(String chasisNo) {
		this.chasisNo = chasisNo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
	