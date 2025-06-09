package com.techchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techchallenge.model.Shipping;

public interface ShippingRepo extends JpaRepository<Shipping, Integer>{
	
	public Shipping findByShippingTrackingNumber(String trackingNumber);

}
