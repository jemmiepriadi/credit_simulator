package com.example.credit_simulator.service.impl;

import com.example.credit_simulator.model.LoanData;
import com.example.credit_simulator.model.LoanResult;
import com.example.credit_simulator.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class LoanServiceImpl implements LoanService {

    @Autowired
    private WebClient webClient;

    public Map<Integer, LoanResult> calculateInstallments(LoanData data) {
        Map<Integer, LoanResult> results = new LinkedHashMap<>();
        double baseInterest = getBaseInterest(data.getVehicleType());
        double currentInterest = baseInterest;
        double principal = data.getLoanAmount() - data.getDownPayment();

        // Starting from year 1, apply the pattern
        int tenorLeft=data.getTenor();
        for (int year = 1; year <= data.getTenor(); year++) {
            if (year > 1) {
                // Apply pattern: every 2 years +0.5%, else +0.1%
                if ((year - 2) % 2 == 0) {
                    currentInterest += 0.1;
                } else {
                    currentInterest += 0.5;
                }
            }

            double yearlyRate = currentInterest / 100.0;
            double totalPrincipal = principal*(1+yearlyRate);
            double yearlyInstallment =totalPrincipal/tenorLeft;
            double monthlyInstallment = yearlyInstallment/12;

            LoanResult loanResult = new LoanResult();
            loanResult.setYear(year);
            loanResult.setMonthlyInstallment(monthlyInstallment);
            loanResult.setInterestRate(currentInterest);

            results.put(year, loanResult);
            principal = totalPrincipal-yearlyInstallment;
            tenorLeft--;
        }

        return results;
    }

    @Override
    public Mono<String> fetchLoanData() {
        return webClient.get()
                .uri("/v3/621e2742-309f-493e-b9d4-ff168282b69e")
                .retrieve()
                .bodyToMono(String.class);
    }

    private double getBaseInterest(String type) {
        return type.equalsIgnoreCase("mobil") ? 8.0 : 9.0;
    }
}
