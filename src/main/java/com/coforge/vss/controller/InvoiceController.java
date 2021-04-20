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
import com.coforge.vss.model.Invoice;
import com.coforge.vss.model.Service;
import com.coforge.vss.repository.InsuranceRepository;
import com.coforge.vss.repository.InvoiceRepository;
import com.coforge.vss.repository.ServiceRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class InvoiceController {

	@Autowired
	private InsuranceRepository InsuranceRepo;
	
	@Autowired
	private ServiceRepository ServiceRepo;
	@Autowired
	private InvoiceRepository InvoiceRepo;
	
	@GetMapping(path="/invoices")
	public List<Invoice> getAllInvoice(){
		return InvoiceRepo.findAll();
	}
	
	@GetMapping(path="/invoices/{id}")
	public ResponseEntity<Invoice> getInvoiceId(@PathVariable(value="id") Long InvoiceId) throws ResourceNotFoundException{
		Invoice v = InvoiceRepo.findById(InvoiceId).orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: "+ InvoiceId));
		return ResponseEntity.ok().body(v);
	}
	
	@PostMapping(path="/invoices")
	public Invoice createInvoice(@Valid @RequestBody Invoice Invoice) {
		Insurance c = InsuranceRepo.findById(Invoice.getInsurance().getInsuranceNo()).orElseThrow(null);
		Invoice.setInsurance(c);
		Service d = ServiceRepo.findById(Invoice.getService().getServiceId()).orElseThrow(null);
		Invoice.setService(d);
		return InvoiceRepo.save(Invoice);
	}
	
	@PutMapping("/invoices/{id}")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable(value="id") Long InvoiceId, @Valid @RequestBody Invoice InvoiceDetails) throws ResourceNotFoundException{
		Invoice Invoice = InvoiceRepo.findById(InvoiceId).orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: "+ InvoiceId));
		Insurance c = InsuranceRepo.findById(Invoice.getInsurance().getInsuranceNo()).orElseThrow(null);
	    Service d = ServiceRepo.findById(Invoice.getService().getServiceId()).orElseThrow(null);
		
		Invoice.setLabourCharges(InvoiceDetails.getLabourCharges());
		Invoice.setServiceCharges(InvoiceDetails.getServiceCharges());
		Invoice.setDate(InvoiceDetails.getDate());
		
		Invoice.setInsurance(c);
		Invoice.setService(d);
		
		final Invoice updatedInvoice = InvoiceRepo.save(Invoice);
		return ResponseEntity.ok(updatedInvoice);
	}
	
	@DeleteMapping("/invoices/{id}")
    public Map<String, Boolean> deleteInvoice(@PathVariable(value = "id") Long InvoiceId)
         throws ResourceNotFoundException {
        Invoice Invoice = InvoiceRepo.findById(InvoiceId)
       .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for this id :: " + InvoiceId));
        InvoiceRepo.delete(Invoice);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}


