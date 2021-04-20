package com.coforge.vss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.vss.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}

