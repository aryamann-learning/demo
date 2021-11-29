package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.entity.BookEntity;
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<BookEntity, Long>{
}

