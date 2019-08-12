package com.eagleeye.salary.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.dom4j.DocumentException;

import com.Messages;
import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.QueryParametersMo;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.salary.bsh.ISalaryCalculateBsh;
import com.eagleeye.salary.bsh.ISalaryConfigBsh;
import com.eagleeye.salary.bsh.ISalarySettlementBsh;
import com.eagleeye.salary.eo.SalaryConfigEO;
import com.eagleeye.salary.eo.SalarySettlementEO;
import com.eagleeye.salary.eo.SalarySettlementEOId;
import com.eagleeye.salary.mo.SalaryMO;
import com.eagleeye.salary.mo.SalaryResultMO;

@ManagedBean(name = "salaryCalculate")
@ViewScoped
public class SalaryCalculateController {
	private Logger log = Logger.getLogger(SalaryCalculateController.class);

	ISalaryConfigBsh salaryConfigBsh;
	ISalaryCalculateBsh salaryCalculateBsh;
	ISalarySettlementBsh salarySettlementBsh;

	QueryParametersMo queryParametersMo = new QueryParametersMo();

	/******************** 通用工资配置数据结构 ********************/
	SalaryConfigEO salaryConfig;
	/******************** 工资计算结果数据结构 ****************/
	SalaryResultMO salaryResult = new SalaryResultMO();
	/******************** 选中的明细工资数据结构 ****************/
	SalaryMO selectedStaff = new SalaryMO();

	SalarySettlementEO salarySettlement;

	List<SalarySettlementEO> salarySettlementHistory = new ArrayList();

	Boolean needConfig = false;

	String managerId;

	public SalaryCalculateController() {
		super();
		// TODO Auto-generated constructor stub
		salaryConfigBsh = (ISalaryConfigBsh) EagleEyeServiceLocator
				.getBean("salaryConfigBsh");
		salaryCalculateBsh = (ISalaryCalculateBsh) EagleEyeServiceLocator
				.getBean("salaryCalculateBsh");
		salarySettlementBsh = (ISalarySettlementBsh) EagleEyeServiceLocator
				.getBean("salarySettlementBsh");
		managerId = (String) SessionManager
				.getSessionByKey(EagleConstants.TOPMANAGERID);

		salaryConfig = salaryConfigBsh
				.getSalaryConfigByManagerIdandTemplateName(managerId,
						EagleConstants.GENERAL_PAYMENT_TEMPLATE);
		if (salaryConfig == null) {
			needConfig = true;
		}
		// else {
		// queryParametersMo.setStartTime(DateUtil
		// .getActualMinimumDate(new Date()));
		// queryParametersMo.setEndTime(new Date());
		// this.calculateSalaryByTime();
		// }
	}

	/**
	 * 计算前一个月工资
	 */
	public void calculateSalaryThisMonth() {
		if (managerId != null && salaryConfig != null) {
			Date startDate = DateUtil.getSimpleDate(DateUtil
					.getPriorMonthDate(new Date()));
			Date endDate = DateUtil.getActualMaximumDate(startDate);
			queryParametersMo.setEndTime(endDate);
			queryParametersMo.setStartTime(startDate);
			salaryResult = salaryCalculateBsh
					.calculateSalaryByTimeAndManagerId(managerId, startDate,
							endDate, salaryConfig);
			this.getSalarySettlementsHistory();
		} else if (salaryConfig == null) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									Messages.getString("salary_calculate_warning_brief.0"),
									Messages.getString("salary_calculate_warning_detail.0")));
		}
	}

	/**
	 * 计算工资结果方法
	 */
	public void calculateSalaryByTime() {
		if (managerId != null && queryParametersMo.getStartTime() != null
				&& queryParametersMo.getEndTime() != null
				&& salaryConfig != null)
			TaoBaoUtil.valideDateGap(queryParametersMo.getStartTime(),
					queryParametersMo.getEndTime());
		salaryResult = salaryCalculateBsh.calculateSalaryByTimeAndManagerId(
				managerId, queryParametersMo.getStartTime(),
				queryParametersMo.getEndTime(), salaryConfig);
		getSalarySettlementsHistory();
	}

	/**
	 * 工资落地
	 */
	public void settleSalayMo() {
		if (selectedStaff != null) {
			salarySettlement = new SalarySettlementEO();
			try {
				BeanUtils.copyProperties(salarySettlement, selectedStaff);
				SalarySettlementEOId id = new SalarySettlementEOId();
				id.setManagerId(selectedStaff.getManagerId());
				id.setStaffId(selectedStaff.getServiceStaffId());
				id.setStart(selectedStaff.getStart());
				id.setEnd(selectedStaff.getEnd());
				salarySettlement.setId(id);
				salarySettlementBsh.saveSalarySettlement(salarySettlement);
				this.getSalarySettlementsHistory();
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_INFO,
										Messages.getString("salary_calulate_success_brief"),
										Messages.getString("salary_calulate_success_detail")));
			} catch (Exception e) {
				log.error(selectedStaff.getManagerId() + " "
						+ selectedStaff.getServiceStaffId() + " "
						+ selectedStaff.getStart() + " "
						+ selectedStaff.getEnd());
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										Messages.getString("salary_calculate_warning_brief.1"),
										Messages.getString("salary_calculate_warning_detail.1")));
			}

		}
	}

	/**
	 * 更新最终工资
	 */
	public void updateFinal() {
		BigDecimal finalAmount = selectedStaff.getFinalSalaryTemp().add(
				selectedStaff.getAdjustAmount());
		selectedStaff.setFinalSalaryResult(finalAmount);
	}

	/**
	 * 得到历史数据
	 */
	public void getSalarySettlementsHistory() {
		if (managerId != null && queryParametersMo.getStartTime() != null
				&& queryParametersMo.getEndTime() != null) {
			salarySettlementHistory = salarySettlementBsh
					.getSalarySettlementByManagerIdAndTime(managerId,
							queryParametersMo.getStartTime(),
							queryParametersMo.getEndTime());
		}
	}

	/**
	 * 导出EXCEL功能
	 * 
	 * @param document
	 */
	public void postProcessXLS(Object document) throws IOException,
			DocumentException {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			HSSFCell cell = header.getCell(i);
			cell.setCellValue(this.getHeadString(i));
			cell.setCellStyle(cellStyle);
		}
		sheet.autoSizeColumn((short) 0); // 调整第一列宽度
		sheet.autoSizeColumn((short) 1); // 调整第二列宽度
		sheet.autoSizeColumn((short) 2); // 调整第三列宽度
		sheet.autoSizeColumn((short) 3); // 调整第四列宽度
		sheet.autoSizeColumn((short) 4); // 调整第五列宽度
		sheet.autoSizeColumn((short) 5); // 调整第六列宽度
		sheet.autoSizeColumn((short) 6); // 调整第七列宽度
	}

	private String getHeadString(int i) {
		switch (i) {
		case 0:
			return "客服帐号";
		case 1:
			return "基础工资（元）";
		case 2:
			return "基线下提成（元）";
		case 3:
			return "基线上提成（元）";
		case 4:
			return "奖励制度（元）";
		case 5:
			return "惩罚制度（元）";
		case 6:
			return "最终工资（元）";
		}
		return null;
	}

	public SalaryResultMO getSalaryResult() {
		return salaryResult;
	}

	public void setSalaryResult(SalaryResultMO salaryResult) {
		this.salaryResult = salaryResult;
	}

	public QueryParametersMo getQueryParametersMo() {
		return queryParametersMo;
	}

	public void setQueryParametersMo(QueryParametersMo queryParametersMo) {
		this.queryParametersMo = queryParametersMo;
	}

	public SalaryMO getSelectedStaff() {
		return selectedStaff;
	}

	public void setSelectedStaff(SalaryMO selectedStaff) {
		this.selectedStaff = selectedStaff;
	}

	public Boolean getNeedConfig() {
		return needConfig;
	}

	public void setNeedConfig(Boolean needConfig) {
		this.needConfig = needConfig;
	}

	public List<SalarySettlementEO> getSalarySettlementHistory() {
		return salarySettlementHistory;
	}

	public void setSalarySettlementHistory(
			List<SalarySettlementEO> salarySettlementHistory) {
		this.salarySettlementHistory = salarySettlementHistory;
	}

	public SalaryConfigEO getSalaryConfig() {
		return salaryConfig;
	}

	public void setSalaryConfig(SalaryConfigEO salaryConfig) {
		this.salaryConfig = salaryConfig;
	}

}
