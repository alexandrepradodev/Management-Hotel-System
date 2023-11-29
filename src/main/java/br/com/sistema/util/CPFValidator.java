package br.com.sistema.util;

public class ValidateCPF {
    public static class CPFValidator {
        public static boolean validateCPF(String cpf) {

            cpf = cpf.replaceAll("[^0-9]", "");

            if (cpf.length() != 11) {
                return false;
            }

            if (cpf.matches("(\\d)\\1{10}")) {
                return false;
            }

            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += (10 - i) * (cpf.charAt(i) - '0');
            }
            int firstDigit = 11 - (sum % 11);
            if (firstDigit > 9) {
                firstDigit = 0;
            }
            if (cpf.charAt(9) - '0' != firstDigit) {
                return false;
            }

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += (11 - i) * (cpf.charAt(i) - '0');
            }
            int secondDigit = 11 - (sum % 11);
            if (secondDigit > 9) {
                secondDigit = 0;
            }
            if (cpf.charAt(10) - '0' != secondDigit) {
                return false;
            }

            return true;
        }

    }


}
