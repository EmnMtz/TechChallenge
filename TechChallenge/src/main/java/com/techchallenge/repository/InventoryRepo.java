package com.techchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techchallenge.model.Inventory;

public interface InventoryRepo  extends JpaRepository<Inventory, Integer>{
	

}
