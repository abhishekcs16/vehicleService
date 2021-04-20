package com.coforge.vss.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Mechanic_details")
public class Mechanic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mechanicId;

	private String mechanicName;

	private String mechanicContact;

	public Mechanic() {
		super();
	}

	
	public Mechanic(String mechanicName, String mechanicContact) {
		super();
		this.mechanicName = mechanicName;
		this.mechanicContact = mechanicContact;
	}


	public long getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(long id) {
		this.mechanicId = id;
	}

	

	public String getMechanicName() {
		return mechanicName;
	}


	public void setMechanicName(String mechanicName) {
		this.mechanicName = mechanicName;
	}


	public String getMechanicContact() {
		return mechanicContact;
	}

	public void setMechanicContact(String mechanicContact) {
		this.mechanicContact = mechanicContact;
	}

}