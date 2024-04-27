package com.pf.mgmt.db;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private Logger logger = LogManager.getLogger(DatabaseConnector.class);

    private String dBURL;
    private String dBUser;
    private String dBPwd;
    private PooledConnection pc;

    public DatabaseConnector() {
    }

    private PooledConnection createPoolConnection() {
        logger.debug("New Pooled called by Thread: " + Thread.currentThread().getName());
        try {
            OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
            ocpds.setDriverType("oci");
            ocpds.setURL(dBURL);
            ocpds.setUser(dBUser);
            ocpds.setPassword(dBPwd);
            pc = ocpds.getPooledConnection();
        } catch (Exception e) {
            logger.error("Error in creating the DB connection.." + e, e);
        }
        return pc;
    }


    public void setdBURL(String dBURL) {
        this.dBURL = dBURL;
    }

    public String getdBURL() {
        return dBURL;
    }

    public void setdBUser(String dBUser) {
        this.dBUser = dBUser;
    }

    public String getdBUser() {
        return dBUser;
    }

    public void setdBPwd(String dBPwd) {
        this.dBPwd = dBPwd;
    }

    public String getdBPwd() {
        return dBPwd;
    }

    public void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            logger.error("Error closing RS.." + e, e);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            logger.error("Error closing stmt.." + e, e);
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            logger.error(conn.hashCode() + "-Error closing conn.." + e, e);
        }
    }

    public Connection getConnection() throws Exception {
        Connection connection;
        if (pc == null) {
            pc = createPoolConnection();
        }
        if (pc != null) {
            connection = pc.getConnection();
        } else {
            throw new SQLException("Database connection is not createable");
        }
        return connection;
    }
}
