package com.eagleeye.statistics.controller.tradeDtl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.eagleeye.eservice.constant.EServiceConstants;
import com.eagleeye.statistics.mo.staff.StatStaffTradeDetailMo;

@ManagedBean(name = "statSentTrade")
@ViewScoped
public class StatSentTradeDetailController extends AbstractStatTradeDetailController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1611882064216777994L;
	
	public StatSentTradeDetailController(){
		super();
		List tradeStatus = new ArrayList();
		tradeStatus.add(EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS);
		queryParametersMo.setStatus(tradeStatus);
		statStaffTradeDetailMo = new StatStaffTradeDetailMo(queryParametersMo);
	}
	
	public void loadSentTrade() {
		super.load(EServiceConstants.TRADE_STATUS_WAIT_BUYER_CONFIRM_GOODS);
	}
}
