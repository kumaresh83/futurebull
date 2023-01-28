package com.ycode.stock.model;

public class CompanyOpenIndex {

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
	public Integer getIntraDayCallNet() {
		return intraDayCallNet;
	}
	public void setIntraDayCallNet(Integer intraDayCallNet) {
		this.intraDayCallNet = intraDayCallNet;
	}
	public Integer getIntraDayPutNet() {
		return intraDayPutNet;
	}
	public void setIntraDayPutNet(Integer intraDayPutNet) {
		this.intraDayPutNet = intraDayPutNet;
	}
	private String companyName;
	private Integer tillDateCallNet;
	private Integer tillDatePutNet;
	private Integer intraDayCallNet;
	private Integer intraDayPutNet;
}
