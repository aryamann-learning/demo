package com.example.demo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Book;
import com.example.demo.service.PracticeBookService;

@RestController
@RequestMapping("/practice-books")
public class PracticeBookController {
	@Autowired
	PracticeBookService practiceBookService;

	@GetMapping("/{source}/info")
	public List<Book> getBooks(@PathVariable("source") String source) throws IOException, ParseException {
		return practiceBookService.getBooks(source);
	}

	@PutMapping("/{source}/{isbn}")
	public void updateBook(@PathVariable("source") String source, @PathVariable("isbn") Long isbn,
			@RequestBody Book book) {
		practiceBookService.updateBook(source, isbn, book);
	}

	@PostMapping("/{source}/book")
	public void createBook(@PathVariable("source") String source, @Valid @RequestBody Book book) throws IOException {
		practiceBookService.createBook(book, source);
	}

	@DeleteMapping("/{source}/{isbn}")
	public void createBook(@PathVariable("source") String source, @PathVariable("isbn") Long isbn) {
		practiceBookService.deleteBook(source, isbn);
	}
}
