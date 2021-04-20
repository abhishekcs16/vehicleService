package com.coforge.vss.repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.coforge.vss.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}


