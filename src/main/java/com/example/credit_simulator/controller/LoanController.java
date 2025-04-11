package com.example.credit_simulator.controller;

import com.example.credit_simulator.model.LoanData;
import com.example.credit_simulator.model.LoanResult;
import com.example.credit_simulator.service.LoanService;
import com.example.credit_simulator.validator.InputValidator;
import com.example.credit_simulator.view.ConsoleView;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoanController {
    private final ConsoleView view;
    private  LoanService loanService;

    public LoanController(ConsoleView view, LoanService loanService) {
        this.view = view;
        this.loanService = loanService;
    }

    public void run() {
        LoanData data = view.getLoanInput();
        //final validation
        String validationError = InputValidator.validate(data);
        if (validationError != null) {
            view.showError(validationError);
            return;
        }

        Map<Integer, LoanResult> results = loanService.calculateInstallments(data);
        view.showInstallmentResult(results);
    }
}
