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
import com.coforge.vss.model.Mechanic;
import com.coforge.vss.repository.MechanicRepository;

//@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MechanicController {
	@Autowired
	private MechanicRepository MechanicRepo;
	
	@GetMapping("/mechanics")
	public List<Mechanic> getAllMechanic() {
		return MechanicRepo.findAll();
	}

	@GetMapping("/mechanics/{id}")
	public ResponseEntity<Mechanic> getMechanicId(@PathVariable(value ="id") Long mechanicId) throws ResourceNotFoundException {
		Mechanic e = MechanicRepo.findById(mechanicId)
				.orElseThrow(() -> new ResourceNotFoundException("Mechanic not found for this id :: " + mechanicId));
		return ResponseEntity.ok().body(e);
	}

	@PostMapping("/mechanics")
	public Mechanic createMechanic(@Valid @RequestBody Mechanic Mechanic) {
		return MechanicRepo.save(Mechanic);
	}

	@PutMapping("/mechanics/{id}")
	public ResponseEntity<Mechanic> updateMechanic(@PathVariable(value = "id") Long mechanicId,
			@Valid @RequestBody Mechanic MechanicDetails) throws ResourceNotFoundException {
		Mechanic Mechanic = MechanicRepo.findById(mechanicId)
				.orElseThrow(() -> new ResourceNotFoundException("Mechanic not found for this id :: " + mechanicId));
		Mechanic.setMechanicName(MechanicDetails.getMechanicName());
		Mechanic.setMechanicContact(MechanicDetails.getMechanicContact());
		
		final Mechanic updatedMechanic = MechanicRepo.save(Mechanic);
		return ResponseEntity.ok(updatedMechanic);

	}

	@DeleteMapping("/mechanics/{id}")
	public Map<String, Boolean> deleteMechanic(@PathVariable(value = "id") Long mechanicId) throws ResourceNotFoundException {
		Mechanic Mechanic = MechanicRepo.findById(mechanicId)
				.orElseThrow(() -> new ResourceNotFoundException("Mechanic not found for this id :: " + mechanicId));

		MechanicRepo.delete(Mechanic);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}


