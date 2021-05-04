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
import com.coforge.vss.model.Service;
import com.coforge.vss.model.ServiceFault;
import com.coforge.vss.repository.ServiceFaultRepository;
import com.coforge.vss.repository.ServiceRepository;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class ServiceFaultController {
	@Autowired
	private ServiceRepository ServiceRepo;
	
	@Autowired
	private ServiceFaultRepository ServiceFaultRepo;
	
	@GetMapping(path="/serviceFaults")
	public List<ServiceFault> getAllServiceFault(){
		return ServiceFaultRepo.findAll();
	}
	
	@GetMapping(path="/serviceFaults/{id}")
	public ResponseEntity<ServiceFault> getServiceFaultId(@PathVariable(value="id") Long ServiceFaultId) throws ResourceNotFoundException{
		ServiceFault v = ServiceFaultRepo.findById(ServiceFaultId).orElseThrow(() -> new ResourceNotFoundException("ServiceFault not found for this id :: "+ ServiceFaultId));
		return ResponseEntity.ok().body(v);
	}
	
	@PostMapping(path="/serviceFaults")
	public ServiceFault createServiceFault(@Valid @RequestBody ServiceFault ServiceFault) {
		Service c = ServiceRepo.findById(ServiceFault.getService().getServiceId()).orElseThrow(null);
		ServiceFault.setService(c);
		return ServiceFaultRepo.save(ServiceFault);
	}
	
	@PutMapping("/serviceFaults/{id}")
	public ResponseEntity<ServiceFault> updateServiceFault(@PathVariable(value="id") Long ServiceFaultId, @Valid @RequestBody ServiceFault ServiceFaultDetails) throws ResourceNotFoundException{
		ServiceFault ServiceFault = ServiceFaultRepo.findById(ServiceFaultId).orElseThrow(() -> new ResourceNotFoundException("ServiceFault not found for this id :: "+ ServiceFaultId));
		Service c = ServiceRepo.findById(ServiceFault.getService().getServiceId()).orElseThrow(null);
		ServiceFault.setFaultType(ServiceFaultDetails.getFaultType());
		ServiceFault.setFaultDate(ServiceFaultDetails.getFaultDate());
		final ServiceFault updatedServiceFault = ServiceFaultRepo.save(ServiceFault);
		ServiceFault.setService(c);
		return ResponseEntity.ok(updatedServiceFault);
	}
	
	@DeleteMapping("/serviceFaults/{id}")
    public Map<String, Boolean> deleteServiceFault(@PathVariable(value = "id") Long ServiceFaultId)
         throws ResourceNotFoundException {
        ServiceFault ServiceFault = ServiceFaultRepo.findById(ServiceFaultId)
       .orElseThrow(() -> new ResourceNotFoundException("ServiceFault not found for this id :: " + ServiceFaultId));
        ServiceFaultRepo.delete(ServiceFault);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}


