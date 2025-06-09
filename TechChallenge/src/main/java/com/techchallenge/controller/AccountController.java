package com.techchallenge.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.techchallenge.model.Account;
import com.techchallenge.model.RegisterDTO;
import com.techchallenge.repository.AccountRepo;

import jakarta.validation.Valid;

@Controller
public class AccountController {

	@Autowired
	private AccountRepo repo;

	@GetMapping("/register")
	public String register(Model model) {
		RegisterDTO registerDTO = new RegisterDTO();
		model.addAttribute(registerDTO);
		model.addAttribute("success", false);
		return "register";
	}

	@PostMapping("/register")
	public String register(Model model, @Valid @ModelAttribute RegisterDTO registerDTO, BindingResult result) {

		if (registerDTO.getFirstName() == null || registerDTO.getFirstName().trim().isEmpty()) {
			result.addError(new FieldError("registerDTO", "firstName", "First Name cannot be blank."));
		}

		if (registerDTO.getLastName() == null || registerDTO.getLastName().trim().isEmpty()) {
			result.addError(new FieldError("registerDTO", "lastName", "Last Name cannot be blank."));
		}

		if (registerDTO.getEmail() == null || registerDTO.getEmail().trim().isEmpty()) {
			result.addError(new FieldError("registerDTO", "email", "Email cannot be blank."));
		}

		Account account = repo.findByEmail(registerDTO.getEmail());
		if (account != null) {
			result.addError(new FieldError("registerDTO", "email", "Email is already registered"));

		}

		if (result.hasErrors()) {
			return "register";
		}

		try {

			var bCryptEncoder = new BCryptPasswordEncoder();

			Account newAccount = new Account();
			newAccount.setFirstName(registerDTO.getFirstName());
			newAccount.setLastName(registerDTO.getLastName());
			newAccount.setEmail(registerDTO.getEmail());
			newAccount.setPassword(bCryptEncoder.encode("password"));
			newAccount.setRole(Integer.valueOf(registerDTO.getRole()));
			newAccount.setCreatedAt(new Date());
			newAccount.setUpdatedAt(new Date());

			repo.save(newAccount);

			model.addAttribute("registerDTO", new RegisterDTO());
			model.addAttribute("success", true);

		} catch (Exception ex) {
			result.addError(new FieldError("registerDTO", "firstName", ex.getMessage()));
		}

		return "register";
	}

}
