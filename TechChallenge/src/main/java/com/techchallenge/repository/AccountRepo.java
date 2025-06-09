package com.techchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techchallenge.model.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>{

	public Account findByEmail(String email);
	
}
