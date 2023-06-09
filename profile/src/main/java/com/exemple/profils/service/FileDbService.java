package com.exemple.profils.service;



import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.exemple.profils.entities.FileDb;
import com.exemple.profils.repos.FileDbRepository;

import dto.FileResponse;



@Service
public class FileDbService {
	
	@Autowired
	private FileDbRepository fileDbRepository;
	
	
	public FileResponse store(  @RequestParam("numTel") String numTel,  MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		FileDb fileDb = new FileDb(numTel, fileName, file.getContentType(), file.getBytes());
		fileDbRepository.save(fileDb);
		return  mapToFileResponse(fileDb);
	}
	
	public FileDb getFileById(String id) {
		
		Optional<FileDb> fileOptional = fileDbRepository.findById(id);
		
		if(fileOptional.isPresent()) {
			return fileOptional.get();
		}
		return null;
	}
	
	public List<FileResponse> getFileList(){
		return fileDbRepository.findAll().stream().map(this::mapToFileResponse).collect(Collectors.toList());
	}
	
	private FileResponse mapToFileResponse(FileDb fileDb) {
		return new FileResponse(fileDb.getNumTel(), fileDb.getType(), fileDb.getName());
	}
	

}

