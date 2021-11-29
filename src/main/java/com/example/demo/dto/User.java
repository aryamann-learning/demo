package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class User {
	private int id;
	private String firstName;
	private String lastName;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(int id2) {
		this.id = id2;
	}

}
