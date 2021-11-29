package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.LaptopEntity;

public interface LaptopService {
	public List<LaptopEntity> listAllLaptops(Integer pageNo, Integer pageSize, String sortBy, String order);
}
