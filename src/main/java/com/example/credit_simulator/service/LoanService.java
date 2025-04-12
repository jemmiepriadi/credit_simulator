package com.example.credit_simulator.service;

import com.example.credit_simulator.model.LoanData;
import com.example.credit_simulator.model.LoanResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public interface LoanService {
    public Map<Integer, LoanResult> calculateInstallments(LoanData data);
    public Mono<String> fetchLoanData();
}
