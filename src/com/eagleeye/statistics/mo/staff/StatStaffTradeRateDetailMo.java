package com.eagleeye.statistics.mo.staff;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.eagleeye.common.query.PageResult;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.eservice.bsh.ITradeRateBsh;
import com.eagleeye.eservice.eo.TradeRateEO;
import com.eagleeye.eservice.mo.TradeRateQueryParametersMo;

public class StatStaffTradeRateDetailMo extends LazyDataModel<TradeRateEO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6565219710179164032L;
	private TradeRateQueryParametersMo queryMo;
	private ITradeRateBsh tradeRateBsh;
	private PageResult result;

	public StatStaffTradeRateDetailMo(
			TradeRateQueryParametersMo tradeRateQueryParams) {
		super();
		this.queryMo = tradeRateQueryParams;
		tradeRateBsh = (ITradeRateBsh) EagleEyeServiceLocator
				.getBean("tradeRateBsh");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TradeRateEO getRowData(String rowKey) {
		// System.out.println(this.getRowData().getTid());
		if (result != null && !result.getListResult().isEmpty()) {
			for (TradeRateEO tradeRate : (List<TradeRateEO>) result
					.getListResult()) {
				if (String.valueOf(tradeRate.getId().getOid()).equals(rowKey))
					return tradeRate;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(TradeRateEO tradeRate) {
		return tradeRate.getId().getOid();
	}

	@Override
	public List<TradeRateEO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		// TODO Auto-generated method stub
		result = tradeRateBsh.getTradeRateEOsPagnationByManagerIdAndTimePreiod(
				queryMo, first, pageSize);
		if (result != null) {
			this.setRowCount(result.getCountTotal());
			List<TradeRateEO> resultList = result.getListResult();
			if (resultList != null && !resultList.isEmpty()) {
				return resultList;
			}
		} else {
			this.setRowCount(0);
		}
		return null;
	}

	public TradeRateQueryParametersMo getQueryMo() {
		return queryMo;
	}

	public void setQueryMo(TradeRateQueryParametersMo queryMo) {
		this.queryMo = queryMo;
	}

}
