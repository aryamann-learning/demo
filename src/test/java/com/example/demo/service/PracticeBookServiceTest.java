package com.example.demo.service;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.SpringBootTestConfig;

public class PracticeBookServiceTest extends SpringBootTestConfig {
	@Autowired
	PracticeBookService servicePracticeBook;

	@Test
	public void testGetBooks() throws IOException, ParseException {
		servicePracticeBook.getBooks("excel");
	}
}
