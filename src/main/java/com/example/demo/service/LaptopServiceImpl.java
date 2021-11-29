package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.constants.DemoConstants;
import com.example.demo.entity.LaptopEntity;
import com.example.demo.enums.SortEnum;
import com.example.demo.repository.LaptopRepository;

@Service
@Transactional
public class LaptopServiceImpl implements LaptopService {
	@Autowired
	private LaptopRepository lrepo;

	public List<LaptopEntity> listAllLaptops(Integer pageNo, Integer pageSize, String sortBy, String order) {
		Sort sort = null;
		if (order == null || SortEnum.find(order) == null) {
			order = SortEnum.DESC.value();
		}
		if (sortBy == null) {
			sort = Sort.by(DemoConstants.DEFAULT_FIELD);
		}
		if (sortBy != null) {
			sort = Sort.by(sortBy);
		}
		if (order.equals(SortEnum.DESC.value())) {
			sort = sort.descending();
		} else {
			sort = sort.ascending();
		}
		Pageable paging = PageRequest.of(pageNo, pageSize, sort);
		Page<LaptopEntity> pagedResult = lrepo.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<LaptopEntity>();
		}
	}

	public LaptopEntity get(int price) {
		LaptopEntity laptop = null;
		return laptop;
	}
}
