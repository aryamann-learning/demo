package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.SpringBootTestConfig;

public class UserRepositoryTest extends SpringBootTestConfig {
	@Autowired
	UserRepository userRepository;

}
