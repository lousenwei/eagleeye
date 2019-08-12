/**
 * 
 */
package com.eagleeye.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.Messages;
import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.spring.EagleEyeServiceLocator;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.user.bsh.IReadLockBsh;
import com.eagleeye.user.eo.ReadLockMappingEO;
import com.eagleeye.user.eo.ReadLockMappingEOId;

/**
 * @author wilson
 * 
 */
@ManagedBean(name = "readLockConfig")
@RequestScoped
public class ReadLockSettingController {

	private TreeNode root;

	private TreeNode[] selectedNodes;

	private String managerId;

	private Map<String, TreeNode> menuMap = new HashMap<String, TreeNode>();

	private IReadLockBsh readLockBsh;

	public ReadLockSettingController() {
		super();
		// TODO Auto-generated constructor stub
		initReadLockMenu();
		managerId = (String) SessionManager.getSessionByKey(EagleConstants.TOPMANAGERID);
		readLockBsh = (IReadLockBsh) EagleEyeServiceLocator.getBean("readLockBsh");
		initSelectedNodes();
	}

	public void saveSelectedNodes() {
		List readLockList = new ArrayList();
		if (selectedNodes != null) {
			for (TreeNode node : selectedNodes) {
				Object url = TaoBaoUtil.getMenuUrlMap().get(node.getData());
				if (url != null) {
					ReadLockMappingEOId id = new ReadLockMappingEOId(managerId, String.valueOf(TaoBaoUtil
							.getMenuUrlMap().get(node.getData())));
					ReadLockMappingEO tempEO = new ReadLockMappingEO(id, new Date());
					tempEO.setMenuName(String.valueOf(node.getData()));
					readLockList.add(tempEO);
				}
			}
		}
		// step 1 delete exists
		readLockBsh.deleteReadLocks(managerId);
		// step 2 save all
		if (readLockList != null && !readLockList.isEmpty())
			readLockBsh.saveReadLocks(readLockList);
		// step 3 clean
		selectedNodes = null;
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.getString("read_lock_setting_success_brief"),
						Messages.getString("read_lock_setting_success_detail")));

	}

	public void initSelectedNodes() {
		// load
		List<ReadLockMappingEO> readLockList = readLockBsh.getReadLocks(managerId);
		if (readLockList != null) {
			for (ReadLockMappingEO node : readLockList) {
				TreeNode temp = menuMap.get(node.getMenuName());
				if (temp != null) {
					temp.setSelected(true);
				}
			}
		}

	}

	private void initReadLockMenu() {
		root = new DefaultTreeNode("root", null);
		// 店铺绩效
		TreeNode statShopTopNode = new DefaultTreeNode("店铺绩效模块", root);
		menuMap.put("店铺经营状况", new DefaultTreeNode("店铺经营状况", statShopTopNode));
		menuMap.put("店铺业绩图表", new DefaultTreeNode("店铺业绩图表", statShopTopNode));
		menuMap.put("店铺业绩件数图表", new DefaultTreeNode("店铺业绩件数图表", statShopTopNode));
		menuMap.put("店铺客单价", new DefaultTreeNode("店铺客单价", statShopTopNode));
		menuMap.put("店铺转换率", new DefaultTreeNode("店铺转换率", statShopTopNode));
		menuMap.put("店铺工作量", new DefaultTreeNode("店铺工作量", statShopTopNode));
		menuMap.put("店铺平均响应", new DefaultTreeNode("店铺平均响应", statShopTopNode));
		menuMap.put("店铺退款率", new DefaultTreeNode("店铺退款率", statShopTopNode));
		// 客服明细
		TreeNode statStaffDtlTopNode = new DefaultTreeNode("客服明细", root);
		menuMap.put("客服业绩金额明细", new DefaultTreeNode("客服业绩金额明细", statStaffDtlTopNode));
		menuMap.put("客服业绩件数明细", new DefaultTreeNode("客服业绩件数明细", statStaffDtlTopNode));
		menuMap.put("客服首次响应时间明细", new DefaultTreeNode("客服首次响应时间明细", statStaffDtlTopNode));
		menuMap.put("客服工作量明细", new DefaultTreeNode("客服工作量明细", statStaffDtlTopNode));
		menuMap.put("客服转换率明细", new DefaultTreeNode("客服转换率明细", statStaffDtlTopNode));
		menuMap.put("客服客单价明细", new DefaultTreeNode("客服客单价明细", statStaffDtlTopNode));
		menuMap.put("客服退款金额率明细", new DefaultTreeNode("客服退款金额率明细", statStaffDtlTopNode));
		menuMap.put("客服在线时间明细", new DefaultTreeNode("客服在线时间明细", statStaffDtlTopNode));
		// 客服绩效
		TreeNode statStaffTopNode = new DefaultTreeNode("客服比较模块", root);
		menuMap.put("客服业绩状况", new DefaultTreeNode("客服业绩状况", statStaffTopNode));
		menuMap.put("客服交易数目状况", new DefaultTreeNode("客服交易数目状况", statStaffTopNode));
		menuMap.put("客服平均响应时间", new DefaultTreeNode("客服平均响应时间", statStaffTopNode));
		menuMap.put("客服工作量", new DefaultTreeNode("客服工作量", statStaffTopNode));
		menuMap.put("客服转换率", new DefaultTreeNode("客服转换率", statStaffTopNode));
		menuMap.put("客服客单价", new DefaultTreeNode("客服客单价", statStaffTopNode));
		menuMap.put("客服退款率", new DefaultTreeNode("客服退款率", statStaffTopNode));
		menuMap.put("客服在线时间", new DefaultTreeNode("客服在线时间", statStaffTopNode));
		// 排行榜
		TreeNode statRankTopNode = new DefaultTreeNode("销售排行模块", root);
		menuMap.put("商品销售金额排行榜", new DefaultTreeNode("商品销售金额排行榜", statRankTopNode));
		menuMap.put("商品销售数量排行榜", new DefaultTreeNode("商品销售数量排行榜", statRankTopNode));
		menuMap.put("商品退款金额排行榜", new DefaultTreeNode("商品退款金额排行榜", statRankTopNode));
		menuMap.put("客户购买金额排行榜", new DefaultTreeNode("客户购买金额排行榜", statRankTopNode));
		menuMap.put("客户退款金额排行榜", new DefaultTreeNode("客户退款金额排行榜", statRankTopNode));
		// 交易明细
		TreeNode statTradeDetailTopNode = new DefaultTreeNode("交易管理模块", root);
		menuMap.put("已付款待发货交易", new DefaultTreeNode("已付款待发货交易", statTradeDetailTopNode));
		menuMap.put("已发货待确认交易", new DefaultTreeNode("已发货待确认交易", statTradeDetailTopNode));
		menuMap.put("已完成交易", new DefaultTreeNode("已完成交易", statTradeDetailTopNode));
		// 中差评管理
		TreeNode statTradeRateTopNode = new DefaultTreeNode("中差评管理模块", root);
		menuMap.put("中差评管理", new DefaultTreeNode("中差评管理", statTradeRateTopNode));
		// 工资结算
		TreeNode statSalaryTopNode = new DefaultTreeNode("工资结算模块", root);
		menuMap.put("通用工资模版设置", new DefaultTreeNode("通用工资模版设置", statSalaryTopNode));
		menuMap.put("个性化工资模板设置", new DefaultTreeNode("个性化工资模板设置", statSalaryTopNode));
		menuMap.put("客服工资模板指定", new DefaultTreeNode("客服工资模板指定", statSalaryTopNode));
		menuMap.put("工资结算", new DefaultTreeNode("工资结算", statSalaryTopNode));
		menuMap.put("工资已结算历史查询", new DefaultTreeNode("工资已结算历史查询", statSalaryTopNode));
		// 系统设置
		TreeNode statSettingTopNode = new DefaultTreeNode("系统设置模块", root);
		menuMap.put("客服管理", new DefaultTreeNode("客服管理", statSettingTopNode));
		menuMap.put("商品过滤管理", new DefaultTreeNode("商品过滤管理", statSettingTopNode));
		menuMap.put("业绩归属方式管理", new DefaultTreeNode("业绩归属方式管理", statSettingTopNode));
		menuMap.put("密码解锁及重置", new DefaultTreeNode("密码解锁及重置", statSettingTopNode));
		menuMap.put("查看权限设置", new DefaultTreeNode("查看权限设置", statSettingTopNode));
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

}
