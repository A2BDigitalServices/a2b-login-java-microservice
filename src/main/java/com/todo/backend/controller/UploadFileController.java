package com.todo.backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.todo.backend.services.StorageService;

@CrossOrigin(origins = {"https://a2bdigital.000webhostapp.com/","https://a2bdigitalservice.000webhostapp.com/"})
//@CrossOrigin
@RestController
@RequestMapping("/image")
public class UploadFileController {
	
	@Autowired
	private StorageService service;

	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		String uploadImage = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] imageData=service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/jpeg"))
				.body(imageData);

	}
}
