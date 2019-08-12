package com.eagleeye.salary.blh;

import java.util.Date;
import java.util.List;

import com.eagleeye.salary.eo.SalarySettlementEO;
import com.eagleeye.salary.mo.SalarySettlementQueryMO;

public interface ISalarySettlementBlh {

	/**
	 * 根据查询条件获取已结算工资明细
	 * 
	 * @param queryMo
	 * @return
	 */
	public List<SalarySettlementEO> getSalarySettlementByMo(
			SalarySettlementQueryMO queryMo);

	/**
	 * 保存结算金额
	 * 
	 * @param salarySettlement
	 */
	public void saveSalarySettlement(SalarySettlementEO salarySettlement);
	
	public List<SalarySettlementEO> getSalarySettlementByManagerIdAndTime(
			String managerId, Date start, Date end) ;

}
