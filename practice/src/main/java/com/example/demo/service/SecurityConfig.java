package com.example.demo.service;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/admin/signin").permitAll()
                .defaultSuccessUrl("/admin/contacts")
                .failureUrl("/login?error")
                .usernameParameter("email")
                .passwordParameter("password")
              ).logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))	  
                .logoutSuccessUrl("/admin/signin")
              ).authorizeHttpRequests(authz -> authz
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/contact/register","/contact/complete","/contact/confirm","/contact","/admin/signin","/admin/signup","/admin/signup/confirm2","/admin/signup/register","/admin/signup/complete2").permitAll()
                .anyRequest().authenticated()
              );
		return http.build();
    }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	

}