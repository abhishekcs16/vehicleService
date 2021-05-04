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
import com.coforge.vss.model.Insurance;
import com.coforge.vss.model.Vehicle;
import com.coforge.vss.repository.InsuranceRepository;
import com.coforge.vss.repository.VehicleRepository;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class InsuranceController {
	
	@Autowired
	private VehicleRepository VehicleRepo;

	@Autowired
	private InsuranceRepository InsuranceRepo;
	
	@GetMapping(path= "/insurances" )
	public List<Insurance> getAllinsurances(){
		return InsuranceRepo.findAll();
	}
	
	@GetMapping(path="/insurances/{id}" )
	public ResponseEntity<Insurance> getinsuranceByNo(@PathVariable Long insuranceId) throws ResourceNotFoundException{
		Insurance e = InsuranceRepo.findById(insuranceId).orElseThrow(() -> new ResourceNotFoundException("Insurance not found for this id :: "+ insuranceId));
		return ResponseEntity.ok().body(e);
	}
	
	@PostMapping(path = "/insurances" )
	public Insurance createInsurance(@Valid @RequestBody Insurance Insurance) throws ResourceNotFoundException{
		Vehicle v = VehicleRepo.findById(Insurance.getVehicle().getVehicleRegNo()).orElseThrow(null);
		Insurance.setVehicle(v);
		return InsuranceRepo.save(Insurance);
	}
	
	@PutMapping(path = "/insurances/{id}")
	public ResponseEntity<Insurance> updateInsurance(@PathVariable Long insuranceId, @Valid @RequestBody Insurance InsuranceDetails) throws ResourceNotFoundException{
		Insurance Insurance = InsuranceRepo.findById(insuranceId).orElseThrow(() -> new ResourceNotFoundException("Insurance not found for this id :: "+ insuranceId));
		Vehicle v = VehicleRepo.findById(Insurance.getVehicle().getVehicleRegNo()).orElseThrow(null);
		Insurance.setInsuranceNo(InsuranceDetails.getInsuranceNo());
		Insurance.setInsuranceAmount(InsuranceDetails.getInsuranceAmount());
		Insurance.setInsuranceDate(InsuranceDetails.getInsuranceDate());
		Insurance.setVehicle(v);
		final Insurance updatedInsurance = InsuranceRepo.save(Insurance);
		return ResponseEntity.ok(updatedInsurance);
	}
	
	@DeleteMapping(path = "/insurances/{id}")
    public Map<String, Boolean> deleteInsurance(@PathVariable Long insuranceId)
         throws ResourceNotFoundException {
		Insurance Insurance = InsuranceRepo.findById(insuranceId)
       .orElseThrow(() -> new ResourceNotFoundException("Insurance not found for this id :: " + insuranceId));
        InsuranceRepo.delete(Insurance);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }	
}