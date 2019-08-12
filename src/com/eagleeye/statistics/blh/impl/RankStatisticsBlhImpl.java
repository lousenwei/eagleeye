/**
 * 
 */
package com.eagleeye.statistics.blh.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eagleeye.statistics.blh.IRankStatisticsBlh;
import com.eagleeye.statistics.dao.RankStatisticsDAO;
import com.eagleeye.statistics.mo.rank.RankCustomerRefundAmountMO;
import com.eagleeye.statistics.mo.rank.RankCustomerRefundAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankCustomerSaleAmountMO;
import com.eagleeye.statistics.mo.rank.RankCustomerSaleAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsRefundAmountMO;
import com.eagleeye.statistics.mo.rank.RankGoodsRefundAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleAmountMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleNumMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleNumResultMO;

/**
 * @author lousenwei
 * 
 */
public class RankStatisticsBlhImpl implements IRankStatisticsBlh {
	private Logger log = Logger.getLogger(RankStatisticsBlhImpl.class);

	RankStatisticsDAO rankStatisticsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.eagleeye.statistics.blh.IRankStatisticsBlh#getRankGoodsSaleNumMO(
	 * java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public RankGoodsSaleNumResultMO getRankGoodsSaleNumMO(String managerId,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		RankGoodsSaleNumResultMO rankGoodsSaleNumResultMo = new RankGoodsSaleNumResultMO();
		List<RankGoodsSaleNumMO> rankGoodsSaleNumMoList = new ArrayList();
		// 得到数据
		List<Map> rankGoodsSaleNumList = (List<Map>) rankStatisticsDao
				.getRankGoodsSaleNumMO(managerId, startDate, endDate);
		// 数据模型转换
		if (rankGoodsSaleNumList != null && !rankGoodsSaleNumList.isEmpty()) {
			for (Map rankGoodsSaleNum : rankGoodsSaleNumList) {
				RankGoodsSaleNumMO rankGoodsSaleNumMo = new RankGoodsSaleNumMO();
				// o.title,o.num_iid,sum(num) total
				String title = (String) rankGoodsSaleNum.get("title");
				Long numIid = (Long) rankGoodsSaleNum.get("num_iid");
				BigDecimal total = (BigDecimal) rankGoodsSaleNum.get("total");
				String picPath = (String) rankGoodsSaleNum.get("pic_path");
				rankGoodsSaleNumMo.setNumIid(numIid);
				rankGoodsSaleNumMo.setTitle(title);
				rankGoodsSaleNumMo.setTotalNum(total);
				rankGoodsSaleNumMo.setPicPath(picPath);
				rankGoodsSaleNumMoList.add(rankGoodsSaleNumMo);
			}
			if (rankGoodsSaleNumMoList != null
					&& !rankGoodsSaleNumMoList.isEmpty()) {
				rankGoodsSaleNumResultMo
						.setRankGoodsSaleNumMoList(rankGoodsSaleNumMoList);
				return rankGoodsSaleNumResultMo;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.eagleeye.statistics.blh.IRankStatisticsBlh#getRankGoodsSaleAmountMO
	 * (java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public RankGoodsSaleAmountResultMO getRankGoodsSaleAmountMO(
			String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		RankGoodsSaleAmountResultMO rankGoodsSaleAmountResultMo = new RankGoodsSaleAmountResultMO();
		List<RankGoodsSaleAmountMO> rankGoodsSaleAmountMoList = new ArrayList();
		// 得到数据
		List<Map> rankGoodsSaleAmountList = (List<Map>) rankStatisticsDao
				.getRankGoodsSaleAmountMO(managerId, startDate, endDate);
		// 数据模型转换
		if (rankGoodsSaleAmountList != null
				&& !rankGoodsSaleAmountList.isEmpty()) {
			for (Map rankGoodsSaleAmount : rankGoodsSaleAmountList) {
				RankGoodsSaleAmountMO rankGoodsSaleAmountMo = new RankGoodsSaleAmountMO();
				// o.title,o.num_iid,sum(total_fee) total
				String title = (String) rankGoodsSaleAmount.get("title");
				Long numIid = (Long) rankGoodsSaleAmount.get("num_iid");
				BigDecimal total = (BigDecimal) rankGoodsSaleAmount
						.get("total");
				String picPath = (String) rankGoodsSaleAmount.get("pic_path");
				rankGoodsSaleAmountMo.setNumIid(numIid);
				rankGoodsSaleAmountMo.setTitle(title);
				rankGoodsSaleAmountMo.setTotalAmount(total);
				rankGoodsSaleAmountMo.setPicPath(picPath);
				rankGoodsSaleAmountMoList.add(rankGoodsSaleAmountMo);
			}
			if (rankGoodsSaleAmountMoList != null
					&& !rankGoodsSaleAmountMoList.isEmpty()) {
				rankGoodsSaleAmountResultMo
						.setRankGoodsSaleAmountMoList(rankGoodsSaleAmountMoList);
				return rankGoodsSaleAmountResultMo;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.eagleeye.statistics.blh.IRankStatisticsBlh#getRankGoodsRefundMO(java
	 * .lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public RankGoodsRefundAmountResultMO getRankGoodsRefundMO(String managerId,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		RankGoodsRefundAmountResultMO rankGoodsRefundAmountResultMo = new RankGoodsRefundAmountResultMO();
		List<RankGoodsRefundAmountMO> rankGoodsRefundAmountMoList = new ArrayList();
		// 得到数据
		List<Map> rankGoodsRefundAmountList = (List<Map>) rankStatisticsDao
				.getRankGoodsRefundMO(managerId, startDate, endDate);
		// 数据模型转换
		if (rankGoodsRefundAmountList != null
				&& !rankGoodsRefundAmountList.isEmpty()) {
			for (Map rankGoodsRefundAmount : rankGoodsRefundAmountList) {
				RankGoodsRefundAmountMO rankGoodsRefundAmountMo = new RankGoodsRefundAmountMO();
				// o.title,o.num_iid,sum(total_fee) total
				String title = (String) rankGoodsRefundAmount.get("title");
				Long numIid = (Long) rankGoodsRefundAmount.get("num_iid");
				BigDecimal total = (BigDecimal) rankGoodsRefundAmount
						.get("total");
				String picPath = (String) rankGoodsRefundAmount.get("pic_path");
				rankGoodsRefundAmountMo.setNumIid(numIid);
				rankGoodsRefundAmountMo.setTitle(title);
				rankGoodsRefundAmountMo.setTotalRefundAmount(total);
				rankGoodsRefundAmountMo.setPicPath(picPath);
				rankGoodsRefundAmountMoList.add(rankGoodsRefundAmountMo);
			}
			if (rankGoodsRefundAmountMoList != null
					&& !rankGoodsRefundAmountMoList.isEmpty()) {
				rankGoodsRefundAmountResultMo
						.setRankGoodsRefundNumMoList(rankGoodsRefundAmountMoList);
				return rankGoodsRefundAmountResultMo;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.eagleeye.statistics.blh.IRankStatisticsBlh#getRankCustomerSaleAmountMO
	 * (java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public RankCustomerSaleAmountResultMO getRankCustomerSaleAmountMO(
			String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		RankCustomerSaleAmountResultMO rankCustomerSaleAmountResultMo = new RankCustomerSaleAmountResultMO();
		List<RankCustomerSaleAmountMO> rankCustomerSaleAmountMoList = new ArrayList();
		// 得到数据
		List<Map> rankCustomerSaleAmountList = (List<Map>) rankStatisticsDao
				.getRankCustomerSaleAmountMO(managerId, startDate, endDate);
		// 数据模型转换
		if (rankCustomerSaleAmountList != null
				&& !rankCustomerSaleAmountList.isEmpty()) {
			for (Map rankCustomerSaleAmount : rankCustomerSaleAmountList) {
				RankCustomerSaleAmountMO rankCustomerSaleAmountMo = new RankCustomerSaleAmountMO();
				// buyer_nick,sum(total_fee) total
				String buyerNick = (String) rankCustomerSaleAmount
						.get("buyer_nick");
				BigDecimal total = (BigDecimal) rankCustomerSaleAmount
						.get("total");
				rankCustomerSaleAmountMo.setBuyerNick(buyerNick);
				rankCustomerSaleAmountMo.setTotalAmount(total);
				rankCustomerSaleAmountMoList.add(rankCustomerSaleAmountMo);
			}
			if (rankCustomerSaleAmountMoList != null
					&& !rankCustomerSaleAmountMoList.isEmpty()) {
				rankCustomerSaleAmountResultMo
						.setRankCustomerSaleAmountMoList(rankCustomerSaleAmountMoList);
				return rankCustomerSaleAmountResultMo;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.eagleeye.statistics.blh.IRankStatisticsBlh#getRankCustomerRefundMO
	 * (java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public RankCustomerRefundAmountResultMO getRankCustomerRefundMO(
			String managerId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		RankCustomerRefundAmountResultMO rankCustomerRefundAmountResultMo = new RankCustomerRefundAmountResultMO();
		List<RankCustomerRefundAmountMO> rankCustomerRefundAmountMoList = new ArrayList();
		// 得到数据
		List<Map> rankCustomerRefundAmountList = (List<Map>) rankStatisticsDao
				.getRankCustomerRefundMO(managerId, startDate, endDate);
		// 数据模型转换
		if (rankCustomerRefundAmountList != null
				&& !rankCustomerRefundAmountList.isEmpty()) {
			for (Map rankCustomerRefundAmount : rankCustomerRefundAmountList) {
				RankCustomerRefundAmountMO rankCustomerRefundAmountMo = new RankCustomerRefundAmountMO();
				// r.buyer_nick,sum(r.refund_fee) total
				String buyerNick = (String) rankCustomerRefundAmount
						.get("buyer_nick");
				BigDecimal total = (BigDecimal) rankCustomerRefundAmount
						.get("total");
				rankCustomerRefundAmountMo.setBuyerNick(buyerNick);
				rankCustomerRefundAmountMo.setTotalRefundAmount(total);
				rankCustomerRefundAmountMoList.add(rankCustomerRefundAmountMo);
			}
			if (rankCustomerRefundAmountMoList != null
					&& !rankCustomerRefundAmountMoList.isEmpty()) {
				rankCustomerRefundAmountResultMo
						.setRankCustomerRefundAmountMoList(rankCustomerRefundAmountMoList);
				return rankCustomerRefundAmountResultMo;
			}
		}
		return null;
	}

	/**
	 * 得到销售额客服排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffAchievementByTimePeriod(String managerId,
			Date startDate, Date endDate) {
		return rankStatisticsDao.getRankStaffAchievementByTimePeriod(managerId,
				startDate, endDate, false);
	}

	/**
	 * 得到客服退款排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffRefundByTimePeriod(String managerId,
			Date startDate, Date endDate) {
		return rankStatisticsDao.getRankStaffRefundByTimePeriod(managerId,
				startDate, endDate, false);
	}

	/**
	 * 得到客服平均回复时间排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffAvgWaitTimeByTimePeriod(String managerId,
			Date startDate, Date endDate) {
		return rankStatisticsDao.getRankStaffAvgWaitTimeByTimePeriod(managerId,
				startDate, endDate, false);
	}

	/**
	 * 得到客服工作量排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffWorkloadByTimePreiod(String managerId,
			Date startDate, Date endDate) {
		return rankStatisticsDao.getRankStaffWorkloadByTimePreiod(managerId,
				startDate, endDate, false);
	}

	/**
	 * 得到客服转换率排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffExchangeRateByTimePreiod(String managerId,
			Date startDate, Date endDate) {
		return rankStatisticsDao.getRankStaffExchangeRateByTimePreiod(
				managerId, startDate, endDate, false);
	}

	/**
	 * 得到客单价排行
	 * 
	 * @param managerId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List getRankStaffManBuyByTimePreiod(String managerId,
			Date startDate, Date endDate) {
		return rankStatisticsDao.getRankStaffManBuyByTimePreiod(managerId,
				startDate, endDate, false);
	}

	public RankStatisticsDAO getRankStatisticsDao() {
		return rankStatisticsDao;
	}

	public void setRankStatisticsDao(RankStatisticsDAO rankStatisticsDao) {
		this.rankStatisticsDao = rankStatisticsDao;
	}

}
