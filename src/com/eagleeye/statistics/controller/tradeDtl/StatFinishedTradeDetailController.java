package com.eagleeye.statistics.controller.tradeDtl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.eagleeye.eservice.constant.EServiceConstants;
import com.eagleeye.statistics.mo.staff.StatStaffTradeDetailMo;

@ManagedBean(name = "statFinishedTrade")
@ViewScoped
public class StatFinishedTradeDetailController extends AbstractStatTradeDetailController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2970142406183331964L;

	public StatFinishedTradeDetailController(){
		super();
		List tradeStatus = new ArrayList();
		tradeStatus.add(EServiceConstants.TRADE_STATUS_TRADE_FINISHED);
		queryParametersMo.setStatus(tradeStatus);
		statStaffTradeDetailMo = new StatStaffTradeDetailMo(queryParametersMo);
	}
	

	public void loadFinishedTrade() {
		super.load(EServiceConstants.TRADE_STATUS_TRADE_FINISHED);
	}
}
