package com.coforge.vss.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.vss.exception.ResourceNotFoundException;
import com.coforge.vss.model.Vehicle;
import com.coforge.vss.model.JobCard;
import com.coforge.vss.repository.VehicleRepository;
import com.coforge.vss.repository.JobCardRepository;
@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class JobCardController {
	@Autowired
	private VehicleRepository VehicleRepo;
	
	@Autowired
	private JobCardRepository JobCardRepo;
	
	@GetMapping(path="/jobCards")
	public List<JobCard> getAllJobCard(){
		return JobCardRepo.findAll();
	}
	
	@GetMapping(path="/jobCards/{id}")
	public ResponseEntity<JobCard> getJobCardId(@PathVariable(value="id") Long jobCardId) throws ResourceNotFoundException{
		JobCard v = JobCardRepo.findById(jobCardId).orElseThrow(() -> new ResourceNotFoundException("JobCard not found for this id :: "+ jobCardId));
		return ResponseEntity.ok().body(v);
	}
	
	@PostMapping(path="/jobCards")
	public JobCard createJobCard(@Valid @RequestBody JobCard JobCard) {
		Vehicle c = VehicleRepo.findById(JobCard.getVehicle().getVehicleRegNo()).orElseThrow(null);
		JobCard.setVehicle(c);
		return JobCardRepo.save(JobCard);
	}
	
	@PutMapping("/jobCards/{id}")
	public ResponseEntity<JobCard> updateJobCard(@PathVariable(value="id") Long jobCardId, @Valid @RequestBody JobCard JobCardDetails) throws ResourceNotFoundException{
		JobCard JobCard = JobCardRepo.findById(jobCardId).orElseThrow(() -> new ResourceNotFoundException("JobCard not found for this id :: "+ jobCardId));
		Vehicle c = VehicleRepo.findById(JobCard.getVehicle().getVehicleRegNo()).orElseThrow(null);
		JobCard.setIssues(JobCardDetails.getIssues());
		JobCard.setRemarks(JobCardDetails.getRemarks());
		JobCard.setDate(JobCardDetails.getDate());
		
		JobCard.setVehicle(c);
		final JobCard updatedJobCard = JobCardRepo.save(JobCard);
		return ResponseEntity.ok(updatedJobCard);
	}
	
	@DeleteMapping("/jobCards/{id}")
    public Map<String, Boolean> deleteJobCard(@PathVariable(value = "id") Long jobCardId)
         throws ResourceNotFoundException {
        JobCard JobCard = JobCardRepo.findById(jobCardId)
       .orElseThrow(() -> new ResourceNotFoundException("JobCard not found for this id :: " + jobCardId));
        JobCardRepo.delete(JobCard);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

	
	
	
