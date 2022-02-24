package com.example.demo.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.ImageDto;
import com.example.demo.entity.ImageEntity;
import com.example.demo.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageRepository imageRepository;

	@Override
	public void uploadImage(MultipartFile file, String source) {
		switch (source) {
		case "db":
			saveImageToDb(file);
			break;
		case "local":
			saveImageToLocal(file);
			break;
		default:
			break;
		}
	}

	private void saveImageToLocal(MultipartFile file) {

	}

	private void saveImageToDb(MultipartFile file) {
		try {
			ImageEntity imageEntity = new ImageEntity();
			imageEntity.setName(file.getOriginalFilename());
			imageEntity.setImage(file.getBytes());
			imageRepository.save(imageEntity);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ImageDto getImage(String source, String name) {
		ImageDto image = null;
		switch (source) {
		case "db":
			image = getImageFromDb(name);

			break;
		case "local":
			image = getImageFromLocal(name);
			break;
		default:
			break;
		}
		return image;

	}

	private ImageDto getImageFromLocal(String name) {
		return null;

	}

	private ImageDto getImageFromDb(String name) {
		Optional<ImageEntity> imageFromDb = imageRepository.findByName(name);
		if (imageFromDb.isPresent()) {
			return imageFromDb.get().toDto();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image does not exist");
		}
	}

	@Override
	public String viewImage(String name) {
		Optional<ImageEntity> image = imageRepository.findByName(name);
		if (image.isPresent()) {
			return Base64.getEncoder().encodeToString(image.get().getImage());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image not found");
		}
	}
}
