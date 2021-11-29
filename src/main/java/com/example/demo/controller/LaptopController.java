package com.example.demo.controller;

import java.util.List;

import javax.el.MethodNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.LaptopEntity;
import com.example.demo.service.LaptopService;

@RestController
@RequestMapping("/laptops")
public class LaptopController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LaptopService serviceimpl;

	@PostMapping("/all")
	public ResponseEntity<List<LaptopEntity>> listAllLaptops(@RequestParam Integer pageNo,
			@RequestParam Integer pageSize, @RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String order) {
		try {
			List<LaptopEntity> list = serviceimpl.listAllLaptops(pageNo, pageSize, sortBy, order);
			return new ResponseEntity<List<LaptopEntity>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (MethodNotFoundException exc) {
			logger.error(exc.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", exc);
		}
	}
}