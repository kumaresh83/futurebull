package com.ycode.stock.model;

public class SameDayData {

	private String date;
	private String companyName;
	private Integer tillDateCallNet;
	private Integer tillDatePutNet;
	private Integer callLong;
	private Integer callShort;
	private Integer putLong;
	private Integer putShort;
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
	public Integer getCallLong() {
		return callLong;
	}
	public void setCallLong(Integer callLong) {
		this.callLong = callLong;
	}
	public Integer getCallShort() {
		return callShort;
	}
	public void setCallShort(Integer callShort) {
		this.callShort = callShort;
	}
	public Integer getPutLong() {
		return putLong;
	}
	public void setPutLong(Integer putLong) {
		this.putLong = putLong;
	}
	public Integer getPutShort() {
		return putShort;
	}
	public void setPutShort(Integer putShort) {
		this.putShort = putShort;
	}
}
