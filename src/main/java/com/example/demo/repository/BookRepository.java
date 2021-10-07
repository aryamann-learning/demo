package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.model.Book;
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book, Long>{
}

