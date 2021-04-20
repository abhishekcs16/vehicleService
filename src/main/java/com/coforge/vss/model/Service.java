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
@Table(name="Service_details")
public class Service {
	
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long serviceId;
	
	
	private String serviceType;
	
	
	private String addedPart;
	
	
	private String serviceDate;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "jobId")
	private JobCard jobCard;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "mechanicId")
	private Mechanic mechanic;

	
	public Service() {
		super();
	}

	public Service(String serviceType, String addedPart, String serviceDate, JobCard jobCard, Mechanic mechanic) {
		super();
		this.serviceType = serviceType;
		this.addedPart = addedPart;
		this.serviceDate = serviceDate;
		this.jobCard = jobCard;
		this.mechanic = mechanic;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long id) {
		this.serviceId = id;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getAddedPart() {
		return addedPart;
	}

	public void setAddedPart(String addedPart) {
		this.addedPart = addedPart;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public JobCard getJobCard() {
		return jobCard;
	}

	public void setJobCard(JobCard jobCard) {
		this.jobCard = jobCard;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	
}