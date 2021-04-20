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
import com.coforge.vss.model.Customer;
import com.coforge.vss.model.Vehicle;
import com.coforge.vss.repository.CustomerRepository;
import com.coforge.vss.repository.VehicleRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class VehicleController {
	@Autowired
	private CustomerRepository CustomerRepo;
	
	@Autowired
	private VehicleRepository vehicleRepo;
	
	@GetMapping(path="/vehicles")
	public List<Vehicle> getAllVehicle(){
		return vehicleRepo.findAll();
	}
	
	@GetMapping(path="/vehicles/{id}")
	public ResponseEntity<Vehicle> getVehicleId(@PathVariable(value="id") Long vehicleId) throws ResourceNotFoundException{
		Vehicle v = vehicleRepo.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: "+ vehicleId));
		return ResponseEntity.ok().body(v);
	}
	
	@PostMapping(path="/vehicles")
	public Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
		Customer c = CustomerRepo.findById(vehicle.getCustomer().getId()).orElseThrow(null);
		vehicle.setCustomer(c);
		return vehicleRepo.save(vehicle);
	}
	
	@PutMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable(value="id") Long vehicleId, @Valid @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException{
		Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: "+ vehicleId));
		Customer c = CustomerRepo.findById(vehicle.getCustomer().getId()).orElseThrow(null);
		vehicle.setVehicleModel(vehicleDetails.getVehicleModel());
		vehicle.setEngineNo(vehicleDetails.getEngineNo());
		vehicle.setChasisNo(vehicleDetails.getChasisNo());
		
		vehicle.setCustomer(c);
		final Vehicle updatedVehicle = vehicleRepo.save(vehicle);
		return ResponseEntity.ok(updatedVehicle);
	}
	
	@DeleteMapping("/vehicles/{id}")
    public Map<String, Boolean> deleteVehicle(@PathVariable(value = "id") Long vehicleId)
         throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepo.findById(vehicleId)
       .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
        vehicleRepo.delete(vehicle);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}


