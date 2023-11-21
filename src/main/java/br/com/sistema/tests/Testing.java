package br.com.sistema.tests;

import br.com.sistema.util.AgeCalculator;

import java.time.LocalDate;

public class Testing {

    public static void main(String[] args) {



        AgeCalculator ageCalculator = new AgeCalculator();
        int test = ageCalculator.calculateAge(LocalDate.now(), LocalDate.now());
        System.out.println(test);
    }
}
