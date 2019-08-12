package com.eagleeye.statistics.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.QueryParametersMo;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.statistics.bsh.IRankStatisticsBsh;
import com.eagleeye.statistics.mo.rank.RankCustomerRefundAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankCustomerSaleAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsRefundAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleAmountResultMO;
import com.eagleeye.statistics.mo.rank.RankGoodsSaleNumResultMO;

@ManagedBean(name = "statRank")
@ViewScoped
public class StatRankController {

	QueryParametersMo queryParametersMo = new QueryParametersMo();

	IRankStatisticsBsh rankStatisticsBsh;

	/******************** 商品销售数量排行指标数据结构 ********************/
	RankGoodsSaleNumResultMO rankGoodsSaleNumResultMo = new RankGoodsSaleNumResultMO();

	/******************** 商品销售金额排行指标数据结构 ********************/
	RankGoodsSaleAmountResultMO rankGoodsSaleAmountResultMo = new RankGoodsSaleAmountResultMO();

	/******************** 商品退款金额排行指标数据结构 ********************/
	RankGoodsRefundAmountResultMO rankGoodsRefundAmountResultMo = new RankGoodsRefundAmountResultMO();

	/******************** 客户购买金额排行指标数据结构 ********************/
	RankCustomerSaleAmountResultMO rankCustomerSaleAmountResultMo = new RankCustomerSaleAmountResultMO();

	/******************** 客户退款金额排行指标数据结构 ********************/
	RankCustomerRefundAmountResultMO rankCustomerRefundAmountResultMo = new RankCustomerRefundAmountResultMO();

	private String managerId;

	public StatRankController() {
		rankStatisticsBsh = (IRankStatisticsBsh) EagleEyeServiceLocator
				.getBean("rankStatisticsBsh");
		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);
	}

	public void getRankGoodsSaleNums() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}
		rankGoodsSaleNumResultMo = rankStatisticsBsh.getRankGoodsSaleNumMO(
				managerId, queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime());
	}

	public void getRankGoodsSaleAmounts() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}		
		rankGoodsSaleAmountResultMo = rankStatisticsBsh
				.getRankGoodsSaleAmountMO(managerId,
						queryParametersMo.getStartTime(),
						queryParametersMo.getEndTime());
	}

	public void getRankGoodsRefundAmounts() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}		
		rankGoodsRefundAmountResultMo = rankStatisticsBsh.getRankGoodsRefundMO(
				managerId, queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime());
	}

	public void getRankCustomerSaleAmounts() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}
		rankCustomerSaleAmountResultMo = rankStatisticsBsh
				.getRankCustomerSaleAmountMO(managerId,
						queryParametersMo.getStartTime(),
						queryParametersMo.getEndTime());
	}

	public void getRankCustomerRefundAmounts() {
		// 根据类型获取查询条件
		TaoBaoUtil.getPreviousQueryMoByType(queryParametersMo);
		if (!TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime())) {
			return;
		}
		
		rankCustomerRefundAmountResultMo = rankStatisticsBsh
				.getRankCustomerRefundMO(managerId,
						queryParametersMo.getStartTime(),
						queryParametersMo.getEndTime());
	}

	public QueryParametersMo getQueryParametersMo() {
		return queryParametersMo;
	}

	public void setQueryParametersMo(QueryParametersMo queryParametersMo) {
		this.queryParametersMo = queryParametersMo;
	}

	public RankGoodsSaleNumResultMO getRankGoodsSaleNumResultMo() {
		return rankGoodsSaleNumResultMo;
	}

	public void setRankGoodsSaleNumResultMo(
			RankGoodsSaleNumResultMO rankGoodsSaleNumResultMo) {
		this.rankGoodsSaleNumResultMo = rankGoodsSaleNumResultMo;
	}

	public RankGoodsSaleAmountResultMO getRankGoodsSaleAmountResultMo() {
		return rankGoodsSaleAmountResultMo;
	}

	public void setRankGoodsSaleAmountResultMo(
			RankGoodsSaleAmountResultMO rankGoodsSaleAmountResultMo) {
		this.rankGoodsSaleAmountResultMo = rankGoodsSaleAmountResultMo;
	}

	public RankGoodsRefundAmountResultMO getRankGoodsRefundAmountResultMo() {
		return rankGoodsRefundAmountResultMo;
	}

	public void setRankGoodsRefundAmountResultMo(
			RankGoodsRefundAmountResultMO rankGoodsRefundAmountResultMo) {
		this.rankGoodsRefundAmountResultMo = rankGoodsRefundAmountResultMo;
	}

	public RankCustomerSaleAmountResultMO getRankCustomerSaleAmountResultMo() {
		return rankCustomerSaleAmountResultMo;
	}

	public void setRankCustomerSaleAmountResultMo(
			RankCustomerSaleAmountResultMO rankCustomerSaleAmountResultMo) {
		this.rankCustomerSaleAmountResultMo = rankCustomerSaleAmountResultMo;
	}

	public RankCustomerRefundAmountResultMO getRankCustomerRefundAmountResultMo() {
		return rankCustomerRefundAmountResultMo;
	}

	public void setRankCustomerRefundAmountResultMo(
			RankCustomerRefundAmountResultMO rankCustomerRefundAmountResultMo) {
		this.rankCustomerRefundAmountResultMo = rankCustomerRefundAmountResultMo;
	}
}
