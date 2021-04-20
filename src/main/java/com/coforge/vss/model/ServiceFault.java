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
@Table(name="serviceFault_details")
public class ServiceFault {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long faultid;
	
    private String faultType;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "serviceId")
    private Service service;
    
    private String faultDate;
	
   

	public ServiceFault() {
		// TODO Auto-generated constructor stub
	}



	public ServiceFault(String faultType, Service service, String faultDate) {
		super();
		this.faultType = faultType;
		this.service = service;
		this.faultDate = faultDate;
	}



	public Long getFaultid() {
		return faultid;
	}



	public void setFaultid(Long id) {
		this.faultid = id;
	}



	public String getFaultType() {
		return faultType;
	}



	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}



	public Service getService() {
		return service;
	}



	public void setService(Service service) {
		this.service = service;
	}



	public String getFaultDate() {
		return faultDate;
	}



	public void setFaultDate(String faultDate) {
		this.faultDate = faultDate;
	}

	
	
    	
	}
    