package com.example.credit_simulator.service;

import com.example.credit_simulator.model.LoanData;
import com.example.credit_simulator.model.LoanResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface LoanService {
    public Map<Integer, LoanResult> calculateInstallments(LoanData data);

}
