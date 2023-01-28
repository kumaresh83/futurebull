package com.ycode.stock.model;

import java.util.ArrayList;

public class OpenIndexResponse {
	
	public ArrayList<OpenIndexOutput> getOpenIndex() {
		return openIndex;
	}

	public void setOpenIndex(ArrayList<OpenIndexOutput> openIndex) {
		this.openIndex = openIndex;
	}

	private ArrayList<OpenIndexOutput> openIndex;

}
