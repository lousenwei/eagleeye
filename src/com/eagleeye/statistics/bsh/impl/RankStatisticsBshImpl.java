package com.eagleeye.statistics.bsh.impl;

import java.util.Date;

import org.apache.log4j.Logger;

import com.eagleeye.statistics.blh.IRankStatisticsBlh;
import com.eagleeye.statistics.bsh.IRankStatisticsBsh;
import com.eagleeye.statistics.mo.rank.RankCustomerRefundAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankCustomerSaleAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsRefundAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleNumResultMO;

public class RankStatisticsBshImpl implements IRankStatisticsBsh {
	private Logger log = Logger.getLogger(RankStatisticsBshImpl.class);

	IRankStatisticsBlh rankStatisticsBlh;

	@Override
	public RankGoodsSaleNumResultMO getRankGoodsSaleNumMO(String managerId,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return rankStatisticsBlh.getRankGoodsSaleNumMO(managerId, startDate,
				endDate);
	}

	@Override
	public RankGoodsSaleAmountResultMO getRankGoodsSaleAmountMO(
			String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return rankStatisticsBlh.getRankGoodsSaleAmountMO(managerId, startDate,
				endDate);
	}

	@Override
	public RankGoodsRefundAmountResultMO getRankGoodsRefundMO(String managerId,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return rankStatisticsBlh.getRankGoodsRefundMO(managerId, startDate,
				endDate);
	}

	@Override
	public RankCustomerSaleAmountResultMO getRankCustomerSaleAmountMO(
			String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return rankStatisticsBlh.getRankCustomerSaleAmountMO(managerId,
				startDate, endDate);
	}

	@Override
	public RankCustomerRefundAmountResultMO getRankCustomerRefundMO(
			String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return rankStatisticsBlh.getRankCustomerRefundMO(managerId, startDate,
				endDate);
	}

	public IRankStatisticsBlh getRankStatisticsBlh() {
		return rankStatisticsBlh;
	}

	public void setRankStatisticsBlh(IRankStatisticsBlh rankStatisticsBlh) {
		this.rankStatisticsBlh = rankStatisticsBlh;
	}

}
