package com.eagleeye.statistics.bsh.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.statistics.blh.IBelongTypeBlh;
import com.eagleeye.statistics.bsh.IBelongTypeBsh;
import com.eagleeye.statistics.eo.BelongTypeEO;

public class BelongTypeBshImpl implements IBelongTypeBsh {
	private Logger log = Logger.getLogger(BelongTypeBshImpl.class);

	IBelongTypeBlh belongTypeBlh;

	@Override
	public void saveItem(BelongTypeEO type) {
		// TODO Auto-generated method stub
		belongTypeBlh.saveItem(type);
	}

	@Override
	public BelongTypeEO getBelongTypeById(String managerId) {
		// TODO Auto-generated method stub
		return belongTypeBlh.getBelongTypeById(managerId);
	}

	@Override
	public String getBelongTypeByManagerId(String managerId) {
		// TODO Auto-generated method stub
		return belongTypeBlh.getBelongTypeByManagerId(managerId);
	}

	@Override
	public void deleteByItemId(String managerId) {
		// TODO Auto-generated method stub
		belongTypeBlh.deleteByItemId(managerId);
	}

	@Override
	public void runTradePersonalStatFirstContactByPayDay(String managerId,
			String sessionKey, List<TradeEO> needAssignTrades) throws Exception {
		// TODO Auto-generated method stub
		belongTypeBlh.runTradePersonalStatFirstContactByPayDay(managerId,
				sessionKey, needAssignTrades);
	}

	@Override
	public void runTradePersonalStatLastContactByPayDay(String managerId,
			String sessionKey, List<TradeEO> needAssignTrades) throws Exception {
		// TODO Auto-generated method stub
		belongTypeBlh.runTradePersonalStatLastContactByPayDay(managerId,
				sessionKey, needAssignTrades);
	}

	@Override
	public void runTradePersonalStatFirstContactByCreateDay(String managerId,
			String sessionKey, List<TradeEO> needAssignTrades) throws Exception {
		// TODO Auto-generated method stub
		belongTypeBlh.runTradePersonalStatFirstContactByCreateDay(managerId,
				sessionKey, needAssignTrades);
	}

	@Override
	public void runTradePersonalStatLastContactByCreateDay(String managerId,
			String sessionKey, List<TradeEO> needAssignTrades) throws Exception {
		// TODO Auto-generated method stub
		belongTypeBlh.runTradePersonalStatLastContactByCreateDay(managerId,
				sessionKey, needAssignTrades);
	}

	public IBelongTypeBlh getBelongTypeBlh() {
		return belongTypeBlh;
	}

	public void setBelongTypeBlh(IBelongTypeBlh belongTypeBlh) {
		this.belongTypeBlh = belongTypeBlh;
	}

}
