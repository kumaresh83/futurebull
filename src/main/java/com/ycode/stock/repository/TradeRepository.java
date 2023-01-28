package com.ycode.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ycode.stock.daomodel.TradeInputs;

@Repository
public interface TradeRepository extends JpaRepository<TradeInputs, Integer> {
	
}
