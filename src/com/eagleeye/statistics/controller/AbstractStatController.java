package com.eagleeye.statistics.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.dom4j.DocumentException;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.QueryParametersMo;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.user.bsh.IGroupMemberBsh;
import com.eagleeye.user.eo.GroupMemberEO;

public abstract class AbstractStatController {

	protected QueryParametersMo queryParametersMo = new QueryParametersMo();

	protected List<GroupMemberEO> members = new ArrayList();

	protected IGroupMemberBsh groupMemberBsh;

	protected String managerId;

	public AbstractStatController() {
		super();
		groupMemberBsh = (IGroupMemberBsh) EagleEyeServiceLocator.getBean("groupMemberBsh");
		managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		if (managerId != null) {
			queryParametersMo.setStaffId(managerId);
			members = groupMemberBsh.getGroupMemebersByManagerId(managerId);
		} else {
			queryParametersMo.setStaffId("test");
		}
		// Date endDate = DateUtil.getSimpleDate(new Date());
		// Date startDate = DateUtil.getActualMinimumDate(endDate);
		// queryParametersMo.setStart(startDate);
		// queryParametersMo.setEnd(endDate);
	}

	public abstract void loadData();

	/**
	 * 导出EXCEL功能
	 * 
	 * @param document
	 */
	public void postProcessXLS(Object document) throws IOException, DocumentException {
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
			sheet.autoSizeColumn((short) i);
		}
	}

	protected abstract String getHeadString(int i);

	public QueryParametersMo getQueryParametersMo() {
		return queryParametersMo;
	}

	public void setQueryParametersMo(QueryParametersMo queryParametersMo) {
		this.queryParametersMo = queryParametersMo;
	}

	public List<GroupMemberEO> getMembers() {
		return members;
	}

	public void setMembers(List<GroupMemberEO> members) {
		this.members = members;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
