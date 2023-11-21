package br.com.sistema.util;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {

    public int calculateAge(LocalDate birhdate, LocalDate current) {
        Period period = Period.between(birhdate, current);
        return period.getYears();
    }
}
