package com.techchallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techchallenge.model.Account;
import com.techchallenge.repository.AccountRepo;


@Service
public class LoginService implements UserDetailsService{
	
	@Autowired
	private AccountRepo repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Account account = repo.findByEmail(email);
		
		if(account != null) {
			
			var checkedAccount = User.withUsername(account.getEmail())
					.password(account.getPassword())
					.roles(account.getRole().toString())
					.build();
			
			return checkedAccount;
		}
		
		return null;
	}


	
	
}
