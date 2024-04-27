package com.pf.mgmt.web;

import com.pf.mgmt.handler.RestRequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@Component
@Controller
@RequestMapping(value = "/TradeService/")
public class TradeServiceController {
    private Logger logger = LogManager.getLogger(TradeServiceController.class);

    @Autowired
    private RestRequestHandler restRequestHandler;

    @RequestMapping(value = "/trade", method = RequestMethod.POST)
    @Consumes("application/json")
    @Produces("application/json")
    @ResponseBody
    public String addTrade(@RequestBody String json) {
        logger.info("Web Request Received to addTrade. " + json);
        return restRequestHandler.addTrade(json);
    }
}
