package com.ycode.stock.model;

import java.util.ArrayList;

public class OpenIndexOutput {
private String date;

private ArrayList<CompanyOpenIndex> companyOpenIndex;

public ArrayList<CompanyOpenIndex> getCompanyOpenIndex() {
	return companyOpenIndex;
}
public void setCompanyOpenIndex(ArrayList<CompanyOpenIndex> companyOpenIndex) {
	this.companyOpenIndex = companyOpenIndex;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}


}
