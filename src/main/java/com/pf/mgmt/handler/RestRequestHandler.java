package com.pf.mgmt.handler;

import com.pf.mgmt.utility.TradeBean;
import com.pf.mgmt.db.TradeServiceDAO;
import com.pf.mgmt.utility.JsonHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestRequestHandler {
    private Logger logger = LogManager.getLogger(RestRequestHandler.class);
    private JsonHandler jsonHandler;
    private TradeServiceDAO tradeServiceDAO;

    public RestRequestHandler() {
        super();
    }

    public String addTrade(String json) {
        TradeBean tradeBean = jsonHandler.parserJSON(json, TradeBean.class);
        if (tradeBean.getTradeType() == TradeBean.TradeType.BUY.getTradeType() || tradeServiceDAO.validatePosition(tradeBean)){
            long transactionRef = tradeServiceDAO.addTrade(tradeBean);
            if (transactionRef > 0 /*&& tradeServiceDAO.addPosition(tradeBean) > 0*/) {
                tradeBean.setAuditId(transactionRef);
            }
        }

        return jsonHandler.convertObjectToJSON(tradeBean);
    }

    public void setJsonHandler(JsonHandler jsonHandler) {
        this.jsonHandler = jsonHandler;
    }

    public JsonHandler getJsonHandler() {
        return jsonHandler;
    }

    public void setTradeServiceDAO(TradeServiceDAO tradeServiceDAO) {
        this.tradeServiceDAO = tradeServiceDAO;
    }

    public TradeServiceDAO getTradeServiceDAO() {
        return tradeServiceDAO;
    }
}
