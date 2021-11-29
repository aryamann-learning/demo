package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.Book;
import com.example.demo.entity.BookEntity;
import com.example.demo.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	public List<Book> listAllBook() {
		List<BookEntity> lst = bookRepository.findAll();
		List<Book> lstBooks = new ArrayList<>();
		for (BookEntity bookEntity : lst) {
			lstBooks.add(bookEntity.toDto());
		}
		return lstBooks;
	}

	public void saveBook(Book book) {
		BookEntity en = new BookEntity();
		en.setAuthorName(book.getAuthorName());
		en.setBookName( book.getBookName());
		en.setBookType(book.getBookType());
		en.setIsbn(book.getIsbn());
		en.setReleaseDate(book.getReleaseDate());
		bookRepository.save(en);
	}

	public void saveBooks(List<Book> books) {
		List<BookEntity> lst = new ArrayList<>();
		for (Book book : books) {
			BookEntity en = new BookEntity();
			en.setAuthorName(book.getAuthorName());
			en.setBookName( book.getBookName());
			en.setBookType(book.getBookType());
			en.setIsbn(book.getIsbn());
			en.setReleaseDate(book.getReleaseDate());
			lst.add(en);
		}
		bookRepository.saveAll(lst);
	}

	public Book get(Long IDBN) {
		return bookRepository.findById(IDBN).get().toDto();
	}

	public void deleteUser(Long IDBN) {
		bookRepository.deleteById(IDBN);
	}

}
