package com.coforge.vss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.vss.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Long>{ 

}