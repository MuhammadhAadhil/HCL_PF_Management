package com.pf.mgmt.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;

public class JsonHandlerTest {
    private static Logger logger = LogManager.getLogger(JsonHandlerTest.class);
    private static ApplicationContext springContext;
    private static JsonHandler jsonHandler;

    @BeforeAll
    public static void initiateContext(){
        String contextFileName = "src\\test\\resources\\applicationContext.xml";
        try {
            springContext = new FileSystemXmlApplicationContext(contextFileName);
            jsonHandler = (JsonHandler) springContext.getBean("jsonHandler");
        } catch (Exception e) {
            logger.error("Initialize Context - ipo-context loader error: " + e.getMessage(), e);
        }
    }

    @Test
    public void convertBackFlowTest() {
        TradeBean tradeBean = new TradeBean();
        String json = jsonHandler.convertObjectToJSON(tradeBean);
        Assertions.assertEquals(tradeBean, jsonHandler.parserJSON(json, TradeBean.class));
    }

    @Test
    public void convertStringTest() {
        TradeBean tradeBean = new TradeBean();
        Assertions.assertNotNull(jsonHandler.convertObjectToJSON(tradeBean));
    }
}
