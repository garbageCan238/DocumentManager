package com.github.ryamal.documentmanager.entity;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PaymentSlipEntity extends AbstractDocumentEntity {
    private String counterparty;
    private BigDecimal amount;
    private String currency;
    private BigDecimal exchangeRate;
    private BigDecimal commission;

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    @Override
    public String toString() {
        return "PaymentSlip{" +
                "number='" + number + '\'' +
                ", date=" + date +
                ", user='" + user + '\'' +
                ", counterparty='" + counterparty + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", commission=" + commission +
                '}';
    }
}
