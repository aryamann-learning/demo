package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Book;

public interface BookService {
	public List<Book> listAllBook();

	public void saveBook(Book book);

	public void saveBooks(List<Book> books);

	public Book get(Long IDBN);

	public void deleteUser(Long IDBN);

}
