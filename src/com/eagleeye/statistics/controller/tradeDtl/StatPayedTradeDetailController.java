package com.eagleeye.statistics.controller.tradeDtl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.eagleeye.eservice.constant.EServiceConstants;
import com.eagleeye.statistics.mo.staff.StatStaffTradeDetailMo;


@ManagedBean(name = "statPayedTrade")
@ViewScoped
public class StatPayedTradeDetailController extends AbstractStatTradeDetailController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8094370094599974926L;

	public StatPayedTradeDetailController(){
		super();
		List tradeStatus = new ArrayList();
		tradeStatus.add(EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS);
		queryParametersMo.setStatus(tradeStatus);
		statStaffTradeDetailMo = new StatStaffTradeDetailMo(queryParametersMo);
	}
	
	public void loadPayedTrade() {
		super.load(EServiceConstants.TRADE_STATUS_WAIT_SELLER_SEND_GOODS);
	}
}
