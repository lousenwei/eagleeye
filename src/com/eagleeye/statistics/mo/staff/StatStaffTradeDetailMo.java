package com.eagleeye.statistics.mo.staff;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.eagleeye.common.query.PageResult;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.eservice.bsh.ITradeSoldBsh;
import com.eagleeye.eservice.eo.TradeEO;
import com.eagleeye.eservice.mo.TradeQueryParametersMo;

public class StatStaffTradeDetailMo extends LazyDataModel<TradeEO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5930293408635148286L;

	private TradeQueryParametersMo queryMo;
	private ITradeSoldBsh tradeSoldBsh;
	private PageResult result;

	public StatStaffTradeDetailMo(TradeQueryParametersMo tradeQueryParams) {
		super();
		this.queryMo = tradeQueryParams;
		tradeSoldBsh = (ITradeSoldBsh) EagleEyeServiceLocator
				.getBean("tradeSoldBsh");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TradeEO getRowData(String rowKey) {
		// System.out.println(this.getRowData().getTid());
		if (result != null && !result.getListResult().isEmpty()) {
			for (TradeEO trade : (List<TradeEO>) result.getListResult()) {
				if (trade.getTid().toString().equals(rowKey))
					return trade;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(TradeEO trade) {
		return trade.getTid();
	}

	@Override
	public List<TradeEO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		// TODO Auto-generated method stub
		result = tradeSoldBsh.getTradeEOsPagnationByManagerIdAndTimePreiod(
				queryMo, first, pageSize);
		if (result != null) {
			this.setRowCount(result.getCountTotal());
			List<TradeEO> resultList = result.getListResult();
			if (resultList != null && !resultList.isEmpty()) {
				return resultList;
			}
		} else {
			this.setRowCount(0);
		}
		return null;
	}

	public TradeQueryParametersMo getQueryMo() {
		return queryMo;
	}

	public void setQueryMo(TradeQueryParametersMo queryMo) {
		this.queryMo = queryMo;
	}

}
