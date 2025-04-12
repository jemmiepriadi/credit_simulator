package com.example.credit_simulator.view;

import com.example.credit_simulator.model.LoanData;
import com.example.credit_simulator.model.LoanResult;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

@Component
public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    public LoanData getLoanInput() {
        LoanData data = new LoanData();

        while (true) {
            System.out.print("Jenis Kendaraan (Mobil/Motor): ");
            String vehicleType = scanner.nextLine().trim().toLowerCase();
            if (vehicleType.equals("mobil") || vehicleType.equals("motor")) {
                data.setVehicleType(vehicleType);
                break;
            }
            System.out.println("Input tidak valid. Masukkan 'Mobil' atau 'Motor'.");
        }

        while (true) {
            System.out.print("Kondisi Kendaraan (Baru/Bekas): ");
            String condition = scanner.nextLine().trim().toLowerCase();
            if (condition.equals("baru") || condition.equals("bekas")) {
                data.setCondition(condition);
                break;
            }
            System.out.println("Input tidak valid. Masukkan 'Baru' atau 'Bekas'.");
        }

        while (true) {
            int currentYear = LocalDate.now().getYear();
            int minYear = currentYear - 1;
            try {
                System.out.print("Tahun Kendaraan: ");
                int year = Integer.parseInt(scanner.nextLine());

                if (year >= 1980 && year <= 2025) {
                    data.setYear(year);
                    if(data.getCondition().equalsIgnoreCase("Baru")&& year<minYear){
                        System.out.println("Kendaraan dengan kondisi 'NEW' tidak boleh memiliki tahun kurang dari " + minYear);
                    }
                    break;
                } else {
                    System.out.println("Tahun tidak valid. Masukkan antara 1980 hingga 2025.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }

        while (true) {
            try {
                System.out.print("Harga Kendaraan (di bawah <= 1 Milyar): ");
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount > 0) {
                    if(amount>1000000000){
                        System.out.println( "Jumlah pinjaman tidak boleh lebih dari 1 miliar.");
                        continue;
                    }
                    data.setLoanAmount(amount);
                    break;
                } else {
                    System.out.println("Jumlah pinjaman harus lebih dari 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }

        while (true) {
            try {
                System.out.print("Uang Muka: ");
                double minDpPercentage = data.getCondition().equalsIgnoreCase("baru") ? 0.35 : 0.25;
                double requiredDp = data.getLoanAmount() * minDpPercentage;

                double downPayment = Double.parseDouble(scanner.nextLine());
                if (downPayment < requiredDp) {
                    System.out.println("Jumlah DP harus minimal " + (int)(minDpPercentage * 100) + "% dari pinjaman.");
                    continue;
                }
                if (downPayment >= 0 && downPayment <= data.getLoanAmount()) {
                    data.setDownPayment(downPayment);
                    break;
                } else {
                    System.out.println("Uang muka harus antara 0 dan jumlah pinjaman.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }

        while (true) {
            try {
                System.out.print("Tenor (dalam tahun): ");
                int tenor = Integer.parseInt(scanner.nextLine());
                if(tenor<1 || tenor>6){
                    System.out.println("Tenor harus antara 1 hingga 6 tahun.");
                }else{
                    data.setTenor(tenor);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }

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
