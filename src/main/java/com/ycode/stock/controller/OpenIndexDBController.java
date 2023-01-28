package com.ycode.stock.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ycode.stock.daomodel.TradeInputs;
import com.ycode.stock.model.OpenIndexOutput;
import com.ycode.stock.model.OpenIndexResponse;
import com.ycode.stock.repository.TradeRepository;
import com.ycode.stock.utill.FileUtill;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@Api(value = "OpenIndexDBController")
@RestController
@RequestMapping("/api")
public class OpenIndexDBController {
	
	@Autowired
	TradeRepository tradeRepository;

	@GetMapping("/openIndexDB")
	public ResponseEntity<OpenIndexResponse> readDbValues(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws IOException, ParseException {
		
		List<TradeInputs> tradeInputs= new ArrayList<TradeInputs>();
		
		tradeRepository.findAll().forEach(tradeInputs::add);
		HashMap<String, ArrayList<TradeInputs>> DBtradeList= FileUtill.parseDBOutput(tradeInputs);

		OpenIndexResponse response = new OpenIndexResponse();

		ArrayList<OpenIndexOutput> openIndex = FileUtill.calculateOpenIndex(DBtradeList);

		response.setOpenIndex(openIndex);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
