package br.com.sistema.util;

import java.math.BigDecimal;

public class StayValue {

    public static BigDecimal calculateStay(int people, char tier) {
        BigDecimal peopleBigDecimal = new BigDecimal(people);
        BigDecimal padraoPrice = new BigDecimal(100);
        BigDecimal masterPrice = new BigDecimal(200);

        if (Character.toUpperCase(tier) == 'P') {
            return padraoPrice.multiply(peopleBigDecimal);
        } else {
            return masterPrice.multiply(peopleBigDecimal);
        }


    }
}
