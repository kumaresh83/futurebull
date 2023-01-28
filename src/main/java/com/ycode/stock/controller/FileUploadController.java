package com.ycode.stock.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ycode.stock.model.FileUploadResponse;
import com.ycode.stock.repository.TradeRepository;
import com.ycode.stock.utill.FileUtill;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@Api(value = "FileUploadController")
@RestController
@RequestMapping("/api")
public class FileUploadController {
	
	@Autowired
	TradeRepository tradeRepository;
	
	@PostMapping("/uploadFile")
	public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("userId") String userId) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		String destinationFileName = FileUtill.saveFile(userId, fileName, multipartFile);

		FileUploadResponse response = new FileUploadResponse();
		response.setFileName(destinationFileName);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}