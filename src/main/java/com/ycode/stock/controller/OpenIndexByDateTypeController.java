package com.ycode.stock.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
import com.ycode.stock.model.MonthData;
import com.ycode.stock.model.OpenIndexDateTypeResponse;
import com.ycode.stock.model.OpenIndexOutput;
import com.ycode.stock.model.OpenIndexResponse;
import com.ycode.stock.model.SameDayData;
import com.ycode.stock.repository.TradeRepository;
import com.ycode.stock.utill.FileUtill;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@Api(value = "OpenIndexByDateTypeController")
@RestController
@RequestMapping("/api")
public class OpenIndexByDateTypeController {

	@Autowired
	TradeRepository tradeRepository;

	@GetMapping("/openIndexByDateType")
	public ResponseEntity<OpenIndexDateTypeResponse> readDbValuesByDateType(
			@RequestParam("clientType") String clientType, @RequestParam("startDate") String startDate)
			throws IOException, ParseException {

		List<TradeInputs> sameDayTradeInputs = new ArrayList<TradeInputs>();
		List<TradeInputs> monthTradeInputs = new ArrayList<TradeInputs>();

		LocalDate lastDayOfMonth = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				.with(TemporalAdjusters.lastDayOfMonth());

		LocalDate firstDayOfMonth = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				.with(TemporalAdjusters.firstDayOfMonth());

		tradeRepository.findByClientTypeAndTradeDate(clientType, java.sql.Date.valueOf(startDate))
				.forEach(sameDayTradeInputs::add);
		tradeRepository.findByTradeDateBetweenAndClientType(java.sql.Date.valueOf(firstDayOfMonth.toString()),
				java.sql.Date.valueOf(lastDayOfMonth.toString()),clientType).forEach(monthTradeInputs::add);

		//SameDayData sameDayData = FileUtill.parsesameDayData(sameDayTradeInputs);
		//ArrayList<MonthData> monthData = FileUtill.parseMonthData(monthTradeInputs);

		OpenIndexDateTypeResponse response = new OpenIndexDateTypeResponse();
		response.setSameDayData(FileUtill.parsesameDayData(sameDayTradeInputs));
		response.setMonthData(FileUtill.parseMonthData(monthTradeInputs));

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
