package com.example.credit_simulator.validator;

import com.example.credit_simulator.model.LoanData;

import java.time.Year;

public class InputValidator {
    public static String validate(LoanData data) {
        int currentYear = Year.now().getValue();

        if (!data.getVehicleType().equalsIgnoreCase("mobil") &&
                !data.getVehicleType().equalsIgnoreCase("motor")) {
            return "Jenis kendaraan harus Mobil atau Motor.";
        }

        if (!data.getCondition().equalsIgnoreCase("baru") &&
                !data.getCondition().equalsIgnoreCase("bekas")) {
            return "Kondisi harus Baru atau Bekas.";
        }

        if (data.getCondition().equalsIgnoreCase("baru") &&
                data.getYear() < (currentYear - 1)) {
            return "Kendaraan baru tidak boleh lebih lama dari tahun " + (currentYear - 1);
        }

        if (data.getTenor() < 1 || data.getTenor() > 6) {
            return "Tenor harus antara 1 hingga 6 tahun.";
        }

        double minDpPercentage = data.getCondition().equalsIgnoreCase("baru") ? 0.35 : 0.25;
        double requiredDp = data.getLoanAmount() * minDpPercentage;

        if (data.getDownPayment() < requiredDp) {
            return "Jumlah DP harus minimal " + (int)(minDpPercentage * 100) + "% dari pinjaman.";
        }

        if (data.getLoanAmount() > 1_000_000_000) {
            return "Jumlah pinjaman tidak boleh lebih dari 1 miliar.";
        }

        return null; // valid
    }
}
