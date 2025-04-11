package com.example.credit_simulator.view;

import com.example.credit_simulator.model.LoanData;
import com.example.credit_simulator.model.LoanResult;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Component
public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    public LoanData getLoanInput() {
        LoanData data = new LoanData();

        System.out.print("Jenis Kendaraan (Mobil/Motor): ");
        data.setVehicleType(scanner.nextLine().trim());

        System.out.print("Kondisi Kendaraan (Baru/Bekas): ");
        data.setCondition(scanner.nextLine().trim());

        System.out.print("Tahun Kendaraan (4 digit): ");
        data.setYear(Integer.parseInt(scanner.nextLine()));

        System.out.print("Jumlah Pinjaman (<= 1 Miliar): ");
        data.setLoanAmount(Double.parseDouble(scanner.nextLine()));

        System.out.print("Tenor Pinjaman (1-6 tahun): ");
        data.setTenor(Integer.parseInt(scanner.nextLine()));

        System.out.print("Jumlah DP: ");
        data.setDownPayment(Double.parseDouble(scanner.nextLine()));

        return data;
    }

    public void showInstallmentResult(Map<Integer, LoanResult> resultMap) {
        System.out.println("\n--- Hasil Perhitungan Cicilan ---");
        for (LoanResult result : resultMap.values()) {
            System.out.printf("Tahun %d: Rp. %,.2f/bln, Suku Bunga: %.1f%%%n",
                    result.getYear(), result.getMonthlyInstallment(), result.getInterestRate());
        }
    }

    public void showError(String message) {
        System.out.println("ERROR: " + message);
    }
}
