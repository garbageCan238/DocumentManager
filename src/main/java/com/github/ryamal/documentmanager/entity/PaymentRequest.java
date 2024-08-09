package com.github.ryamal.documentmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class PaymentRequest {
    @Id
    private String number;
    private LocalDate date;
    @Column(name="'user'")
    private String user;
    private BigDecimal amount;
    private String employee;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

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
