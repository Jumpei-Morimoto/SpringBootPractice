package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.admins;

@Repository
public interface AdminRepository extends JpaRepository<admins,Long>{

	admins findByEmail(String username);

}
