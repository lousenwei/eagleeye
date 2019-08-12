package com.eagleeye.nopayclean.blh.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.taobao.TaoBaoClientUtil;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.util.DateUtil;
import com.eagleeye.eservice.blh.impl.TradeSoldBlhImpl;
import com.eagleeye.nopayclean.blh.INoPayCleanBlh;
import com.eagleeye.nopayclean.dao.impl.NoPayCleanDetailDAO;
import com.eagleeye.nopayclean.eo.NoPayCleanDetailEO;
import com.eagleeye.nopayclean.eo.NoPayCleanDetailEOId;
import com.eagleeye.schedule.constant.ScheduleConstants;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ArticleSub;
import com.taobao.api.domain.ArticleUserSubscribe;
import com.taobao.api.request.VasSubscSearchRequest;
import com.taobao.api.request.VasSubscribeGetRequest;
import com.taobao.api.response.VasSubscSearchResponse;
import com.taobao.api.response.VasSubscribeGetResponse;

public class NoPayCleanBlhImpl implements INoPayCleanBlh {
	private Logger log = Logger.getLogger(TradeSoldBlhImpl.class);

	NoPayCleanDetailDAO noPayCleanDetailDao;

	@Override
	public List<ArticleSub> loadNoPayCleanPersons() {
		// TODO Auto-generated method stub
		// 封装taobaorequest
		TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_XML);
		VasSubscSearchRequest req = new VasSubscSearchRequest();
		req.setArticleCode(EagleConstants.PAYMENT_CODE);
		Date dateTime = DateUtil.getPreviousDay(
				DateUtil.getSimpleDate(new Date()), 20);
		Date start = dateTime;
		Date end = DateUtil.getNextDay(dateTime, 1);
		req.setStartDeadline(start);
		req.setEndDeadline(end);
		req.setPageSize(40L);
		req.setItemCode(EagleConstants.PAYMENT_ITEM_CODE);
		Boolean hasNext = true;
		Long pageNum = 1L;
		try {
			while (hasNext) {
				req.setPageNo(pageNum);
				VasSubscSearchResponse response = client.execute(req);
				List<ArticleSub> articleSubs = new ArrayList();
				if (response != null) {
					articleSubs = response.getArticleSubs();
				}
				// 最多重跑3次
				if ((response == null || response.getErrorCode() != null)
						&& TaoBaoUtil.checkReRunErrorCode(response
								.getErrorCode())) {
					int count = 0;
					Boolean needReRun = true;
					while (count < 3 && needReRun) {
						// 线程休眠半秒
						Thread.sleep(500);
						// count自增
						count++;
						response = client.execute(req);
						if (response != null
								&& response.getArticleSubs() != null) {
							articleSubs = response.getArticleSubs();
							needReRun = false;
						}
					}
					log.error(pageNum + " " + start + "-" + end
							+ " nopayresults Rerun time:" + count);
				}
				// 是否存在下一页
				if (response == null || response.getErrorCode() != null
						|| (response.getTotalItem() - pageNum * 40) <= 0) {
					hasNext = false;
					if (response == null) {
						log.error(pageNum + " " + start + "-" + end
								+ " full nopayresults response null!");
						break;
					}
					if (response != null && response.getErrorCode() != null) {
						log.error(pageNum + " " + start + "-" + end + " "
								+ response.getMsg());
						break;
					}
				}
				// 遍历存数据
				if (articleSubs != null && !articleSubs.isEmpty()) {
					return articleSubs;
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}

	@Override
	public void verifyNoPayCleanPersons(List<ArticleSub> articleSubs) {
		// TODO Auto-generated method stub
		if (articleSubs != null && !articleSubs.isEmpty()) {
			List<NoPayCleanDetailEO> noPayCleans = new ArrayList();
			for (ArticleSub tempArt : articleSubs) {
				List<ArticleUserSubscribe> temps = new ArrayList();
				if (tempArt.getNick() != null && !tempArt.getNick().isEmpty()) {
					// 封装taobaorequest
					TaobaoClient client = TaoBaoClientUtil
							.getDefaultTaoBaoClient(EagleConstants.FORMAT_XML);
					VasSubscribeGetRequest req = new VasSubscribeGetRequest();
					req.setNick(tempArt.getNick());
					req.setArticleCode(EagleConstants.PAYMENT_CODE);
					Boolean isExpired = false;
					try {
						VasSubscribeGetResponse response = client.execute(req);
						if (response != null && response.getErrorCode() == null) {
							temps = response.getArticleUserSubscribes();
							isExpired = true;
						}
						// 最多重跑3次
						if ((response == null || response.getErrorCode() != null)
								&& TaoBaoUtil.checkReRunErrorCode(response
										.getErrorCode())) {
							int count = 0;
							Boolean needReRun = true;
							while (count < 3 && needReRun) {
								// 线程休眠半秒
								Thread.sleep(500);
								// count自增
								count++;
								response = client.execute(req);
								if (response != null
										&& response.getArticleUserSubscribes() != null) {
									temps = response.getArticleUserSubscribes();
									isExpired = true;
									needReRun = false;
								}
							}
							log.error(tempArt.getNick() + " "
									+ tempArt.getArticleCode()
									+ " actual nopayresult Rerun time:" + count);
						}
						// 代表过期
						if (temps == null && isExpired) {
							NoPayCleanDetailEO noPayCleanDetail = new NoPayCleanDetailEO();
							NoPayCleanDetailEOId id = new NoPayCleanDetailEOId();
							id.setCreateDate(new Date());
							id.setManagerId(tempArt.getNick());
							noPayCleanDetail
									.setStatus(ScheduleConstants.SCHEDULE_INIT_STATUS);
							noPayCleanDetail.setId(id);
							noPayCleanDetail.setDeadLine(tempArt.getDeadline());
							noPayCleans.add(noPayCleanDetail);
							log.info(tempArt.getNick() + " "
									+ tempArt.getArticleCode()
									+ " type:expired");
						}
					} catch (Exception e) {
						log.error(tempArt.getNick() + " "
								+ tempArt.getArticleCode() + "\n" + e);
					}
				}
			}
			if (noPayCleans != null && !noPayCleans.isEmpty()) {
				// save
				noPayCleanDetailDao.saveAllObject(noPayCleans);
			}
		}
	}

	/**
	 * 全量清除一个用户信息
	 * 
	 * @param noPayCleanDetail
	 */
	public void deleteAllDataBySchedule(NoPayCleanDetailEO noPayCleanDetail) {
		noPayCleanDetailDao.deleteAllDataBySchedule(noPayCleanDetail);
	}

	/**
	 * 获取所有未处理的过期人员列表
	 * 
	 * @return
	 */
	public List<NoPayCleanDetailEO> getAllNoUndoPayCleanDetails() {
		return noPayCleanDetailDao.getAllNoUndoPayCleanDetails();
	}

	/**
	 * 更新待处理过期人员列表并保存
	 * 
	 * @param noPayCleanDetailList
	 */
	public void updateAll(List<NoPayCleanDetailEO> noPayCleanDetailList) {
		noPayCleanDetailDao.saveOrUpdateBatch(noPayCleanDetailList);
	}

	public NoPayCleanDetailDAO getNoPayCleanDetailDao() {
		return noPayCleanDetailDao;
	}

	public void setNoPayCleanDetailDao(NoPayCleanDetailDAO noPayCleanDetailDao) {
		this.noPayCleanDetailDao = noPayCleanDetailDao;
	}
}
