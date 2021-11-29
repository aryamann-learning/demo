package com.example.demo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.SpringBootTestConfig;
import com.example.demo.dto.Book;

import junit.framework.Assert;

public class BookServiceTest extends SpringBootTestConfig{
	@Autowired
	BookService bookService;
	
	@Test
	public void testGet(){
		Book book=bookService.get(1234567L);
		
		Assert.assertEquals(1234567L, book.getIsbn());
	}

}
