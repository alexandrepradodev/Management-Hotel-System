package br.com.sistema.util;

public class StayCalculator {

    public StayCalculator() {
    }

    public double calculateStay(Double valuePerDay, Integer days, Integer adults, Integer children) {
        return days * valuePerDay + days * adults * 75.0 + days * children * 50.0;
    }
}
