package com.example.credit_simulator.service.impl;

import com.example.credit_simulator.model.LoanData;
import com.example.credit_simulator.model.LoanResult;
import com.example.credit_simulator.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class LoanServiceImpl implements LoanService {
    public Map<Integer, LoanResult> calculateInstallments(LoanData data) {
        Map<Integer, LoanResult> results = new LinkedHashMap<>();
        double baseInterest = getBaseInterest(data.getVehicleType());
        double principal = data.getLoanAmount() - data.getDownPayment();
        int currentYear = 1;

        for (int year = 1; year <= data.getTenor(); year++) {
            double interest = baseInterest + ((year - 1) / 2) * 0.5 + ((year - 1) * 0.1);
            double yearlyRate = interest / 100.0;
            double monthlyInstallment = calculateMonthlyInstallment(principal, yearlyRate, data.getTenor());

            LoanResult  loanResult = new LoanResult();
            loanResult.setYear(year);
            loanResult.setMonthlyInstallment(monthlyInstallment);
            loanResult.setInterestRate(interest);
            results.put(year, loanResult);
        }

        return results;
    }

    private double calculateMonthlyInstallment(double principal, double yearlyRate, int tenorYears) {
        int n = tenorYears * 12;
        double monthlyRate = yearlyRate / 12.0;
        return (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -n));
    }

    private double getBaseInterest(String type) {
        return type.equalsIgnoreCase("mobil") ? 8.0 : 9.0;
    }
}
