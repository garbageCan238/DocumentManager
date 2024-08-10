package com.github.ryamal.documentmanager.entity;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PaymentRequest extends BaseDocument {
    private BigDecimal amount;
    private String employee;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "number='" + number + '\'' +
                ", date=" + date +
                ", user='" + user + '\'' +
                ", amount=" + amount +
                ", employee='" + employee + '\'' +
                '}';
    }
}
