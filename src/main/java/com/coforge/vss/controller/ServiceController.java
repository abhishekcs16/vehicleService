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
import com.coforge.vss.model.JobCard;
import com.coforge.vss.model.Mechanic;
import com.coforge.vss.model.Service;
import com.coforge.vss.repository.JobCardRepository;
import com.coforge.vss.repository.MechanicRepository;
import com.coforge.vss.repository.ServiceRepository;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class ServiceController {

	@Autowired
	private JobCardRepository JobCardRepo;
	
	@Autowired
	private ServiceRepository ServiceRepo;
	
	@Autowired
	private MechanicRepository  MechanicRepo;
	
	@GetMapping(path="/services")
	public List<Service> getAllService(){
		return ServiceRepo.findAll();
	}
	
	@GetMapping(path="/services/{id}")
	public ResponseEntity<Service> getServiceId(@PathVariable(value="id") Long serviceId) throws ResourceNotFoundException{
		Service v = ServiceRepo.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: "+ serviceId));
		return ResponseEntity.ok().body(v);
	}
	
	@PostMapping(path="/services")
	public Service createService(@Valid @RequestBody Service Service) {
		JobCard c = JobCardRepo.findById(Service.getJobCard().getJobId()).orElseThrow(null);
		Mechanic m = MechanicRepo.findById(Service.getMechanic().getMechanicId()).orElseThrow(null);
		Service.setJobCard(c);
		Service.setMechanic(m);
		return ServiceRepo.save(Service);
	}
	
	@PutMapping("/services/{id}")
	public ResponseEntity<Service> updateService(@PathVariable(value="id") Long serviceId, @Valid @RequestBody Service ServiceDetails) throws ResourceNotFoundException{
		Service Service = ServiceRepo.findById(serviceId).orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: "+ serviceId));
		JobCard c = JobCardRepo.findById(Service.getJobCard().getJobId()).orElseThrow(null);
		Mechanic m = MechanicRepo.findById(Service.getMechanic().getMechanicId()).orElseThrow(null);
		Service.setServiceType(ServiceDetails.getServiceType());
		Service.setAddedPart(ServiceDetails.getAddedPart());
		Service.setServiceDate(ServiceDetails.getServiceDate());
		
		Service.setJobCard(c);
		Service.setMechanic(m);
		final Service updatedService = ServiceRepo.save(Service);
		return ResponseEntity.ok(updatedService);
	}
	
	@DeleteMapping("/services/{id}")
    public Map<String, Boolean> deleteService(@PathVariable(value = "id") Long serviceId)
         throws ResourceNotFoundException {
        Service Service = ServiceRepo.findById(serviceId)
       .orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + serviceId));
        ServiceRepo.delete(Service);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}


