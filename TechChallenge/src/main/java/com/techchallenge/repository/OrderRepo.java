package com.techchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techchallenge.model.Order;

public interface OrderRepo  extends JpaRepository<Order, Integer>{

	public Order findByOrderTrackingNumber(String orderTrackingNumber);
	
}
