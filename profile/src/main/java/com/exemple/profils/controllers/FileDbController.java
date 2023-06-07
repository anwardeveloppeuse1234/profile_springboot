package com.exemple.profils.controllers;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exemple.profils.entities.FileDb;
import com.exemple.profils.service.FileDbService;

import dto.FileResponse;



@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileDbController {
	
	@Autowired
	private FileDbService fileDbService;
	
	@PostMapping
	public FileResponse uploadFile(@RequestParam("numTel")String numTel, @RequestParam("file") MultipartFile  file) throws IOException {
		return fileDbService.store(numTel,file);
	}
	
	@GetMapping("/{id}")
	public FileDb getFile(@PathVariable String id) {
		
		return fileDbService.getFileById(id);
		
	}
	
	@GetMapping("/list")
	public List<FileResponse> getFileList(){
		return fileDbService.getFileList();
	}
	

}
