package com.ycode.stock.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ycode.stock.model.OpenIndexOutput;
import com.ycode.stock.model.OpenIndexResponse;
import com.ycode.stock.utill.FileUtill;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@Api(value = "OpenIndexController")
@RestController
@RequestMapping("/api")
public class OpenIndexController {

	@GetMapping("/openIndex")
	public ResponseEntity<OpenIndexResponse> uploadFile(@RequestParam("fileName") String fileName,
			@RequestParam("userId") String userId) throws IOException {

		OpenIndexResponse response = new OpenIndexResponse();

		ArrayList<OpenIndexOutput> openIndex = FileUtill.readFile(userId, fileName);

		response.setOpenIndex(openIndex);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
