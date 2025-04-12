package com.example.credit_simulator.controller;

import com.example.credit_simulator.model.LoanData;
import com.example.credit_simulator.model.LoanResult;
import com.example.credit_simulator.service.LoanService;
import com.example.credit_simulator.validator.InputValidator;
import com.example.credit_simulator.view.ConsoleView;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Component
public class LoanController {
    private final ConsoleView view;
    private  LoanService loanService;
    private final Scanner scanner = new Scanner(System.in);

    public LoanController(ConsoleView view, LoanService loanService) {
        this.view = view;
        this.loanService = loanService;
    }

    public void run() {
        System.out.println("testing CI/CD");
        while(true) {
            LoanData data = view.getLoanInput();
            //final validation
            String validationError = InputValidator.validate(data);
            if (validationError != null) {
                view.showError(validationError);
                return;
            }

            Map<Integer, LoanResult> results = loanService.calculateInstallments(data);
            view.showInstallmentResult(results);

            //prompt user if they want to re-enter data and re-calculate
            System.out.print("\nDo you want to enter new data? (yes/no): ");
            String userResponse = scanner.nextLine().trim().toLowerCase();

            if (!userResponse.equals("yes")) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }
        }
    }
}
