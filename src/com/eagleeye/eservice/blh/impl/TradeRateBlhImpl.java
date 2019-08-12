package com.eagleeye.eservice.blh.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eagleeye.common.constant.EagleConstants;
import com.eagleeye.common.query.PageResult;
import com.eagleeye.common.taobao.TaoBaoClientUtil;
import com.eagleeye.common.taobao.TaoBaoUtil;
import com.eagleeye.common.taobao.eServiceUtils;
import com.eagleeye.common.web.SessionManager;
import com.eagleeye.eservice.blh.ITradeRateBlh;
import com.eagleeye.eservice.constant.EServiceConstants;
import com.eagleeye.eservice.dao.impl.TradeRateDAO;
import com.eagleeye.eservice.eo.TradeRateEO;
import com.eagleeye.eservice.eo.TradeRateEOId;
import com.eagleeye.eservice.mo.TradeRateQueryParametersMo;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TradeRate;
import com.taobao.api.request.TraderatesGetRequest;
import com.taobao.api.response.TraderatesGetResponse;

public class TradeRateBlhImpl implements ITradeRateBlh {
    private Logger log = Logger.getLogger(this.getClass());
    TradeRateDAO tradeRateDao;

    @Override
    public void loadTradeRateIncrementInfo(String managerId, Date startTime,
                                           Date endTime, String sessionKey, String rateType) throws Exception {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        // 得到sessionkey
        if (sessionKey == null)
            sessionKey = (String) SessionManager
                    .getSessionByKey(EagleConstants.TOPSESSIONKEY);
        if (sessionKey == null)
            throw new Exception(managerId + " no session key!");
        // 封装taobaorequest
        TaobaoClient client = TaoBaoClientUtil.getDefaultTaoBaoClient(EagleConstants.FORMAT_JSON);

        TraderatesGetRequest req = new TraderatesGetRequest();
        req.setFields(EServiceConstants.INTERFACES_TRADE_RATE_FIELDS);
        req.setStartDate(startTime);
        req.setEndDate(endTime);
        req.setRateType("get");
        req.setRole("buyer");
        req.setResult(rateType);
        req.setPageSize(40L);
        Boolean hasNext = true;
        Long pageNum = 1L;
        Map<TradeRateEOId, TradeRateEO> tradeRateEOs = new HashMap();
        while (hasNext) {
            req.setPageNo(pageNum);
            TraderatesGetResponse response = client.execute(req, sessionKey);
            List<TradeRate> tradeRates = new ArrayList();
            if (response != null) {
                tradeRates = response.getTradeRates();
            }
            // 最多重跑3次
            if ((response == null || response.getErrorCode() != null)
                    && TaoBaoUtil.checkReRunErrorCode(response.getErrorCode())) {
                int count = 0;
                Boolean needReRun = true;
                while (count < 3 && needReRun) {
                    // 线程休眠半秒
                    Thread.sleep(500);
                    // count自增
                    count++;
                    response = client.execute(req, sessionKey);
                    if (response != null && response.getTradeRates() != null) {
                        tradeRates = response.getTradeRates();
                        needReRun = false;
                    }
                }
                log.error(managerId + " " + sessionKey + " " + startTime + "-"
                        + endTime + " TradeRate Rerun time:" + count);
            }
            // 是否存在下一页
            if (response == null || response.getErrorCode() != null
                    || (response.getTotalResults() - pageNum * 40) <= 0) {
                hasNext = false;
                if (response == null) {
                    log.error(managerId + " " + sessionKey + " " + startTime
                            + "-" + endTime + " TradeRate response null!");
                    break;
                }
                if (response != null && response.getErrorCode() != null) {
                    log.error(managerId + " " + sessionKey + " " + startTime
                            + "-" + endTime + " " + response.getMsg());
                    break;
                }
            }
            // 遍历存数据
            if (tradeRates != null && !tradeRates.isEmpty()) {
                for (TradeRate temp : tradeRates) {
                    TradeRateEO tradeRateEO = (TradeRateEO) tradeRateDao
                            .getData(
                                    TradeRateEO.class,
                                    new TradeRateEOId(temp.getTid(), temp
                                            .getOid()));
                    if (tradeRateEO == null) {
                        tradeRateEO = new TradeRateEO();
                        TradeRateEOId id = new TradeRateEOId();
                        tradeRateEO.setId(id);
                    }
                    eServiceUtils.copyTradeRateToTradeRateEO(temp, tradeRateEO);
                    tradeRateEOs.put(tradeRateEO.getId(), tradeRateEO);
                }
            }
            log.info(managerId + "评价类型：" + rateType + "， 交易评价信息PAGE" + pageNum);
            pageNum++;
        }
        if (!tradeRateEOs.isEmpty()) {
            // TODO:save tradeEOs
            tradeRateDao.saveOrUpdateBatch(tradeRateEOs.values());
            log.info(managerId + "增量交易评价信息添加成功");
        }
    }

    /**
     * 保存tradeRate
     *
     * @param tradeRate
     */
    public void updateTradeRateEO(TradeRateEO tradeRate) {
        tradeRateDao.updateData(tradeRate);
    }

    /**
     * 返回交易评价数据分页
     *
     * @param queryMo
     * @param startPage
     * @param pageSize
     * @return
     */
    public PageResult getTradeRateEOsPagnationByManagerIdAndTimePreiod(
            TradeRateQueryParametersMo queryMo, int startNum, int pageSize) {
        return tradeRateDao.getTradeRateEOsPagnationByManagerIdAndTimePreiod(
                queryMo, startNum, pageSize);
    }

    public TradeRateDAO getTradeRateDao() {
        return tradeRateDao;
    }

    public void setTradeRateDao(TradeRateDAO tradeRateDao) {
        this.tradeRateDao = tradeRateDao;
    }

}
