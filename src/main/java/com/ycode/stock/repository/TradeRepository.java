package com.ycode.stock.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ycode.stock.daomodel.TradeInputs;

@Repository
public interface TradeRepository extends JpaRepository<TradeInputs, Integer> {
	List<TradeInputs> findByClientType(String clientType);
	List<TradeInputs> findByClientTypeAndTradeDate(String clientType,Date tradeDate);
	List<TradeInputs> findByTradeDate(Date tradeDate);
	List<TradeInputs> findByTradeDateBetweenAndClientType(Date startDate,Date endDate,String clientType );
}
