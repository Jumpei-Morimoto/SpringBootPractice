package com.example.demo.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.admins;
import com.example.demo.repository.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
	private final AdminRepository repository;
	
	@Autowired
    public UserDetailsServiceImpl(AdminRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		admins user = repository.findByEmail(username);
		if (user == null) {
				throw(new UsernameNotFoundException("not found"));
		}
		return User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles("USER")
				.build();
	}
	
}
