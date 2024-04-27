package com.pf.mgmt.db;

import com.pf.mgmt.utility.TradeBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class TradeServiceDAO {
    private Logger logger = LogManager.getLogger(TradeServiceDAO.class);
    private DatabaseConnector dbConnector;

    public long addTrade(TradeBean tradeBean) {
        long auditId = getNextAvailAuditId();
        if (auditId > 0){
            insertTrade(auditId, tradeBean);
        }
        return auditId;
    }

    private void insertTrade(long auditId, TradeBean tradeBean) {
        CallableStatement cs = null;
        Connection connection = null;

        try {
            connection = getDbConnector().getConnection();
            cs = connection.prepareCall("insert into audit(txn_ref, instrument_id, audit_date, trade_type, portfolio_id) values (?,?,?,?,?)");
            cs.setLong(1, auditId);
            cs.setLong(2, Long.valueOf(tradeBean.getInstrumentId()));
            cs.setDate(3, new Date(System.currentTimeMillis()));
            cs.setInt(4, tradeBean.getTradeType());
            cs.setLong(5, Long.valueOf(tradeBean.getPortfolioId()));
            cs.executeUpdate();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            getDbConnector().close(connection, cs, null);
        }
    }

    private long getNextAvailAuditId() {
        PreparedStatement prepStmnt = null;
        Connection connection = null;
        ResultSet resultSet = null;
        long auditId = -1;

        try {
            connection = getDbConnector().getConnection();
            prepStmnt = connection.prepareCall("select max(id)+1 as audit_id from audit");
            resultSet = prepStmnt.executeQuery();
            if (resultSet.next()){
                auditId = resultSet.getLong("audit_id");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            getDbConnector().close(connection, prepStmnt, resultSet);
        }
        return auditId;
    }

    public void setDbConnector(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public DatabaseConnector getDbConnector() {
        return dbConnector;
    }

    public boolean validatePosition(TradeBean tradeBean) {
        CallableStatement cs = null;
        Connection connection = null;
        ResultSet resultSet = null;
        boolean result = false;

        try {
            connection = getDbConnector().getConnection();
            cs = connection.prepareCall("select units  from positions where portfolio_id = ? and instrument_id = ?");
            cs.setLong(1, Long.valueOf(tradeBean.getPortfolioId()));
            cs.setLong(2, Long.valueOf(tradeBean.getInstrumentId()));
            resultSet = cs.executeQuery();
            if (resultSet.next() && resultSet.getInt("units") >= tradeBean.getUnits()) {
                result = true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            getDbConnector().close(connection, cs, resultSet);
        }
        return result;
    }
}
