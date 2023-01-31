package com.ycode.stock.model;

public class MonthData {

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getTillDateCallNet() {
		return tillDateCallNet;
	}
	public void setTillDateCallNet(Integer tillDateCallNet) {
		this.tillDateCallNet = tillDateCallNet;
	}
	public Integer getTillDatePutNet() {
		return tillDatePutNet;
	}
	public void setTillDatePutNet(Integer tillDatePutNet) {
		this.tillDatePutNet = tillDatePutNet;
	}
	private String date;
	private String companyName;
	private Integer tillDateCallNet;
	private Integer tillDatePutNet;

}
