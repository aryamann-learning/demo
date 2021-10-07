package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
    BookService bookService;

    @GetMapping("")
    public List<Book> list() {
        return bookService.listAllBook();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> get(@PathVariable Long IDBN) {
        try {
            Book book = bookService.get(IDBN);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody List<Book> books) {
        bookService.saveBooks(books);
    }
    @PutMapping("/{isbn}")
    public ResponseEntity<?> update(@RequestBody Book book, @PathVariable Long IDBN) {
        try {
            bookService.get(IDBN);
            book.setIsbn(IDBN);            
            bookService.saveBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{isbn}")
    public void delete(@PathVariable Long isbn) {

        bookService.deleteUser(isbn);
    }

}
