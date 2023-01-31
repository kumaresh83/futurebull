package com.ycode.stock.model;

import java.util.ArrayList;

public class OpenIndexDateTypeResponse {
	
private ArrayList<MonthData> monthData;
private SameDayData sameDayData;
public ArrayList<MonthData> getMonthData() {
	return monthData;
}
public void setMonthData(ArrayList<MonthData> monthData) {
	this.monthData = monthData;
}
public SameDayData getSameDayData() {
	return sameDayData;
}
public void setSameDayData(SameDayData sameDayData) {
	this.sameDayData = sameDayData;
}

}
