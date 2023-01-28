package com.ycode.stock.daomodel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@javax.persistence.Entity
@javax.persistence.Table(name = "tradeinputs")
public class TradeInputs {
	
	@Id
	   @GeneratedValue
	   private Long ID;
	
	public String getClientType() {
		return CLIENT_TYPE;
	}
	public void setClientType(String clientType) {
		this.CLIENT_TYPE = clientType;
	}
	public Date getTradeDate() {
		return TRADE_DATE;
	}
	public void setTradeDate(Date tradeDate) {
		this.TRADE_DATE = tradeDate;
	}
	public int getFutureIndexLong() {
		return FUTURE_INDEX_LONG;
	}
	public void setFutureIndexLong(int futureIndexLong) {
		this.FUTURE_INDEX_LONG = futureIndexLong;
	}
	public int getFutureIndexShort() {
		return FUTURE_INDEX_SHORT;
	}
	public void setFutureIndexShort(int futureIndexShort) {
		this.FUTURE_INDEX_SHORT = futureIndexShort;
	}
	public int getFutureStockLong() {
		return FUTUTRE_STOCK_LONG;
	}
	public void setFutureStockLong(int futureStockLong) {
		this.FUTUTRE_STOCK_LONG = futureStockLong;
	}
	public int getFutureStockShort() {
		return FUTURE_STOCK_SHORT;
	}
	public void setFutureStockShort(int futureStockShort) {
		this.FUTURE_STOCK_SHORT = futureStockShort;
	}
	public int getOptionIndexCallLong() {
		return OPTION_INDEX_CALL_LONG;
	}
	public void setOptionIndexCallLong(int optionIndexCallLong) {
		this.OPTION_INDEX_CALL_LONG = optionIndexCallLong;
	}
	public int getOptionIndexPutLong() {
		return OPTION_INDEX_PUT_LONG;
	}
	public void setOptionIndexPutLong(int optionIndexPutLong) {
		this.OPTION_INDEX_PUT_LONG = optionIndexPutLong;
	}
	public int getOptionIndexCallShort() {
		return OPTION_INDEX_CALL_SHORT;
	}
	public void setOptionIndexCallShort(int optionIndexCallShort) {
		this.OPTION_INDEX_CALL_SHORT = optionIndexCallShort;
	}
	public int getOptionIndexPutShort() {
		return OPTION_INDEX_PUT_SHORT;
	}
	public void setOptionIndexPutShort(int optionIndexPutShort) {
		this.OPTION_INDEX_PUT_SHORT = optionIndexPutShort;
	}
	public int getOptionStockCallLong() {
		return OPTION_STOCK_CALL_LONG;
	}
	public void setOptionStockCallLong(int optionStockCallLong) {
		this.OPTION_STOCK_CALL_LONG = optionStockCallLong;
	}
	public int getOptionStockPutLong() {
		return OPTION_STOCK_PUT_LONG;
	}
	public void setOptionStockPutLong(int optionStockPutLong) {
		this.OPTION_STOCK_PUT_LONG = optionStockPutLong;
	}
	public int getOptionStockCallShort() {
		return OPTION_STOCK_CALL_SHORT;
	}
	public void setOptionStockCallShort(int optionStockCallShort) {
		this.OPTION_STOCK_CALL_SHORT = optionStockCallShort;
	}
	public int getOptionStockPutShort() {
		return OPTION_STOCK_PUT_SHORT;
	}
	public void setOptionStockPutShort(int optionStockPutShort) {
		this.OPTION_STOCK_PUT_SHORT = optionStockPutShort;
	}
	public int getTotalLongContracts() {
		return TOTAL_LONG_CONTRACTS;
	}
	public void setTotalLongContracts(int totalLongContracts) {
		this.TOTAL_LONG_CONTRACTS = totalLongContracts;
	}
	public int getTotalShortContracts() {
		return TOTAL_SHORT_CONTRACTS;
	}
	public void setTotalShortContracts(int totalShortContracts) {
		this.TOTAL_SHORT_CONTRACTS = totalShortContracts;
	}
	
	public Long getId() {
		return ID;
	}
	public void setId(Long id) {
		this.ID = id;
	}

	private String CLIENT_TYPE;
	private Date TRADE_DATE;
	private int FUTURE_INDEX_LONG;
	private int FUTURE_INDEX_SHORT;
	private int FUTUTRE_STOCK_LONG;
	private int FUTURE_STOCK_SHORT;
	private int OPTION_INDEX_CALL_LONG;
	private int OPTION_INDEX_PUT_LONG;
	private int OPTION_INDEX_CALL_SHORT;
	private int OPTION_INDEX_PUT_SHORT;
	private int OPTION_STOCK_CALL_LONG;
	private int OPTION_STOCK_PUT_LONG;
	private int OPTION_STOCK_CALL_SHORT;
	private int OPTION_STOCK_PUT_SHORT;
	private int TOTAL_LONG_CONTRACTS;
	private int TOTAL_SHORT_CONTRACTS;

}
