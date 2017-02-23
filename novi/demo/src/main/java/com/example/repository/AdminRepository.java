package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	Admin findById(Long id);
	List<Admin> findAll();

}
