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
@Table(name="JobCard_details")
public class JobCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long jobId;
    private String issues;
    private String remarks;
    private String date;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "vehicleRegNo")
	private Vehicle vehicle;
	
	
	public JobCard() {
		super();
	}

	

	public JobCard(String issues, String remarks, String date, Vehicle vehicle) {
		super();
		this.issues = issues;
		this.remarks = remarks;
		this.date = date;
		this.vehicle = vehicle;
	}



	public long getJobId() {
		return jobId;
	}

	public void setJobId(long id) {
		this.jobId = id;
	}

	public String getIssues() {
		return issues;
	}

	public void setIssues(String issues) {
		this.issues = issues;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	

}
