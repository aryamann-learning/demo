package com.example.demo.service;

import java.util.Comparator;

import com.example.demo.dto.Book;

public class SortByauthorName implements Comparator<Book> {

	@Override
	public int compare(Book one, Book two) {
		return one.getAuthorName().compareTo(two.getAuthorName());
	}

}
