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
import com.coforge.vss.repository.CustomerRepository;



@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerRepository CustomerRepo;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomer() {
		return CustomerRepo.findAll();
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerId(@PathVariable(value ="id") Long customerId) throws ResourceNotFoundException {
		Customer e = CustomerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		return ResponseEntity.ok().body(e);
	}

	@PostMapping("/customers")
	public Customer createCustomer(@Valid @RequestBody Customer Customer) {
		return CustomerRepo.save(Customer);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
			@Valid @RequestBody Customer CustomerDetails) throws ResourceNotFoundException {
		Customer Customer = CustomerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		Customer.setCustomerName(CustomerDetails.getCustomerName());
		Customer.setCustomerContact(CustomerDetails.getCustomerContact());
		Customer.setCustomerAddress(CustomerDetails.getCustomerAddress());
		final Customer updatedCustomer = CustomerRepo.save(Customer);
		return ResponseEntity.ok(updatedCustomer);

	}

	@DeleteMapping("/customers/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
		Customer Customer = CustomerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

		CustomerRepo.delete(Customer);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

