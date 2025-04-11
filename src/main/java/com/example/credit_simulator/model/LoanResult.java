package com.example.credit_simulator.model;

import lombok.Data;

@Data
public class LoanResult {
    private int year;
    private double monthlyInstallment;
    private double interestRate;


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMonthlyInstallment() {
        return monthlyInstallment;
    }

    public void setMonthlyInstallment(double monthlyInstallment) {
        this.monthlyInstallment = monthlyInstallment;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
