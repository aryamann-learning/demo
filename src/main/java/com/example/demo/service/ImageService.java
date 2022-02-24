package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ImageDto;

public interface ImageService {
	public void uploadImage(MultipartFile file, String source);

	public ImageDto getImage(String source, String name);

	public String viewImage(String name);

}
