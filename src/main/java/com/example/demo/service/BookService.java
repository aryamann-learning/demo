package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
@Service
@Transactional
public class BookService {
	@Autowired
    private BookRepository bookRepository;
    public List<Book> listAllBook() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }
    public void saveBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }
    public Book get(Long IDBN) {
        return bookRepository.findById(IDBN).get();
    }

    public void deleteUser(Long IDBN) {
        bookRepository.deleteById(IDBN);
    }

	
}
