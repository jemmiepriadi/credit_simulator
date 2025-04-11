package com.example.credit_simulator.loan;

import com.example.credit_simulator.model.LoanData;
import com.example.credit_simulator.model.LoanResult;
import com.example.credit_simulator.service.LoanService;
import com.example.credit_simulator.service.impl.LoanServiceImpl;
import com.example.credit_simulator.view.ConsoleView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "app.cli.enabled=false")
@ExtendWith(MockitoExtension.class)
public class LoanCalculationTest {

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    public void testCalculateInstallments_Mobil_NewVehicle() {

        LoanData data = new LoanData();
        data.setVehicleType("mobil");
        data.setLoanAmount(100000000);
        data.setDownPayment(70000000);
        data.setTenor(3);

        Map<Integer, LoanResult> results = loanService.calculateInstallments(data);

        // Base interest for "mobil" is 8.0%
        // Yearly interest should go: 8.0%, 8.1%, 8.6%

        assertThat(results).hasSize(3);

        LoanResult year1 = results.get(1);
        LoanResult year2 = results.get(2);
        LoanResult year3 = results.get(3);

        assertThat(year1.getInterestRate()).isEqualTo(8.0);
        assertThat(year2.getInterestRate()).isEqualTo(8.1);
        assertThat(year3.getInterestRate()).isEqualTo(8.6);

        assertThat(year1.getMonthlyInstallment()).isGreaterThan(0);
        assertThat(year2.getMonthlyInstallment()).isGreaterThan(year1.getMonthlyInstallment());
        assertThat(year3.getMonthlyInstallment()).isGreaterThan(year2.getMonthlyInstallment());
    }

    @Test
    public void testCalculateInstallments_Motor_BiggerBaseInterest() {

        LoanData data = new LoanData();
        data.setVehicleType("motor");
        data.setLoanAmount(30000000);
        data.setDownPayment(5000000);
        data.setTenor(2);

        Map<Integer, LoanResult> results = loanService.calculateInstallments(data);

        // Base interest for "motor" is 9.0%
        assertThat(results).hasSize(2);

        assertThat(results.get(1).getInterestRate()).isEqualTo(9.0);
        assertThat(results.get(2).getInterestRate()).isEqualTo(9.1);
    }
}
