package com.example.demo.repository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.LaptopEntity;

@EnableJpaRepositories
public interface LaptopRepository extends PagingAndSortingRepository<LaptopEntity, Long> {
	
}
