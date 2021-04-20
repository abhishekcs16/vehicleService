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
@Table(name="invoice_details")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long invoiceId;
	
    private String labourCharges;
    
    private String serviceCharges;
    
    private String date;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "serviceId")
    private Service service;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "insuranceNo")
    private Insurance insurance;
	public Invoice() {
		// TODO Auto-generated constructor stub
	}
	public Invoice(String labourCharges, String serviceCharges, String date, Service service, Insurance insurance) {
		super();
		this.labourCharges = labourCharges;
		this.serviceCharges = serviceCharges;
		this.date = date;
		this.service = service;
		this.insurance = insurance;
	}
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long id) {
		this.invoiceId = id;
	}
	public String getLabourCharges() {
		return labourCharges;
	}
	public void setLabourCharges(String labourCharges) {
		this.labourCharges = labourCharges;
	}
	public String getServiceCharges() {
		return serviceCharges;
	}
	public void setServiceCharges(String serviceCharges) {
		this.serviceCharges = serviceCharges;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Insurance getInsurance() {
		return insurance;
	}
	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
}
	
	