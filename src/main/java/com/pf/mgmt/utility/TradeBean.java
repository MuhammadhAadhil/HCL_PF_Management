package com.pf.mgmt.utility;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class TradeBean implements Serializable {

    @JsonProperty("PortfolioID")
    private String portfolioId;

    @JsonProperty("InstrumentId")
    private String instrumentId;

    @JsonProperty("Units")
    private int units;

    @JsonProperty("Price")
    private BigDecimal price;

    @JsonProperty("TransactionRef")
    private long auditId;

    @JsonProperty("TradeType")
    private int tradeType = TradeType.BUY.tradeType;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getAuditId() {
        return auditId;
    }

    public void setAuditId(long auditId) {
        this.auditId = auditId;
    }

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public enum TradeType {
        BUY(1),
        SELL(2);

        private int tradeType;

        TradeType(int tradeType) {
            this.tradeType = tradeType;
        }

        public int getTradeType() {
            return tradeType;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeBean tradeBean = (TradeBean) o;
        return units == tradeBean.units &&
                auditId == tradeBean.auditId &&
                tradeType == tradeBean.tradeType &&
                Objects.equals(portfolioId, tradeBean.portfolioId) &&
                Objects.equals(instrumentId, tradeBean.instrumentId) &&
                Objects.equals(price, tradeBean.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portfolioId, instrumentId, units, price, auditId, tradeType);
    }
}
