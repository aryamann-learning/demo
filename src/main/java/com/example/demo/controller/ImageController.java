package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ImageDto;
import com.example.demo.service.ImageService;

@RestController
public class ImageController {
	@Autowired
	ImageService imageService;

	@PostMapping("/upload/{source}/image")
	public void uploadImage(@RequestParam("image") MultipartFile file, @PathVariable("source") String source) {
		imageService.uploadImage(file, source);
	}

	@GetMapping("{source}/image/{name}")
	public ImageDto getImage(@PathVariable("source") String source, @PathVariable("name") String name) {
		return imageService.getImage(source, name);
	}

	@GetMapping("view/image/{name}")
	public String viewImage(@PathVariable("name") String name) {
		return imageService.viewImage(name);
	}
}
