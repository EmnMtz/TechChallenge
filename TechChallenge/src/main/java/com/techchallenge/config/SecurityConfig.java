package com.techchallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		requestCache.setMatchingRequestParameterName("");
		http.authorizeHttpRequests(auth -> 
			auth.requestMatchers("/").permitAll()
				.requestMatchers("/index").permitAll()
				.requestMatchers("/register").permitAll()
				.anyRequest().authenticated())
				.formLogin(form -> form.defaultSuccessUrl("/home", true))
				.logout(config -> config.logoutSuccessUrl("/"))
				.requestCache((cache) -> cache
			            .requestCache(requestCache));

		return http.build();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
