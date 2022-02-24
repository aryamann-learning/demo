package com.example.demo.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.example.demo.dto.Book;

public interface PracticeBookService {
	public List<Book> getBooks(String source) throws IOException, ParseException;

	public void updateBook(String source, Long isbn, Book book);

	public void createBook(Book book, String source) throws IOException;

	public void deleteBook(String source, Long isbn);
}
