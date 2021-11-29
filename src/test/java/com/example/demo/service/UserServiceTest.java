package com.example.demo.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.SpringBootTestConfig;
import com.example.demo.dto.User;

public class UserServiceTest extends SpringBootTestConfig {
	@Autowired
	UserService userService;

	@Test
	public void testListAllUser() {
		List<User> list = userService.listAllUser();
		System.out.println(list.size());

	}
}
