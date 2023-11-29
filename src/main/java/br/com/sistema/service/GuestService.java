package br.com.sistema.service;

import br.com.sistema.exceptions.BusinessRuleException;
import br.com.sistema.DAO.GuestDAO;
import br.com.sistema.model.Guest;
import br.com.sistema.util.AgeCalculator;
import br.com.sistema.util.EmailValidator;
import br.com.sistema.util.JPAUtil;
import br.com.sistema.util.CPFValidator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class GuestService {

    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public static void newGuest() {

        EntityManager entityManager = JPAUtil.getEntityManager();

        try {

            System.out.print("\nNome do hóspede: ");
            String name = scanner.nextLine();
            GuestDAO guestDAO = new GuestDAO(entityManager);

            // Depois de name.matches vem um regex que identifica se há números no nome do cliente. Se existir,
            // um erro será lançado

            if (name.isEmpty() || name.matches(".*\\d+.*")) {
                throw new IllegalArgumentException("O nome do hóspede não pode estar vazio ou conter números");
            }

            System.out.print("CPF do hóspede: ");
            String cpf = scanner.nextLine();

            if (!CPFValidator.validateCPF(cpf)) {
                throw new BusinessRuleException("O CPF digitado é inválido.");
            }

            cpf = cpf.replaceAll("[^0-9]", "");

            if (guestDAO.checkIfCPFExists().contains(cpf)) {
                throw new BusinessRuleException("O CPF digitado já pertence a algum hóspede");
            }

            System.out.print("Email do hóspede: ");
            String email = scanner.nextLine();

            if (guestDAO.checkIfEmailExists().contains(email)) {
                throw new BusinessRuleException("O email digitado já pertence a algum hóspede");
            }

            if (!EmailValidator.validateEmail(email)) {
                throw new BusinessRuleException("Esse e-mail possui um formato inválido");
            }

            System.out.print("Data de nascimento: ");
            LocalDate birthday = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);

            if (birthday.isAfter(LocalDate.now()) || birthday.isEqual(LocalDate.now())) {
                throw new IllegalArgumentException("A data de nascimento precisa ser anterior à data atual");
            }

            AgeCalculator ageCalculator = new AgeCalculator();

            int age = ageCalculator.calculateAge(birthday, LocalDate.now());
            if (age <= 14) {
                throw new IllegalArgumentException("Para ter cadastro no sistema," +
                        " o hóspede precisa ter mais de 14 anos");
            }

            Guest guest = new Guest(name, cpf, email, birthday, age);

            entityManager.getTransaction().begin();
            guestDAO.save(guest);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (RuntimeException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    public static void showAllGuests() {

        EntityManager entityManager = JPAUtil.getEntityManager();
        GuestDAO guestDAO = new GuestDAO(entityManager);
        List<Guest> guests = guestDAO.getAllGuests();

        for (Guest guest: guests) {
            System.out.println(guest.stringBuilder());
            System.out.println();

        }
    }
    public static void removeGuest(){

        EntityManager entityManager = JPAUtil.getEntityManager();
        showAllGuests();

        System.out.print("Digite o Id do cliente que deseja remover: ");
        Long guestId = scanner.nextLong();
        GuestDAO guestDAO = new GuestDAO(entityManager);

        if (guestDAO.getAllIds().contains(guestId)) {
            entityManager.getTransaction().begin();
            guestDAO.removeGuestPerId(guestId);
            entityManager.getTransaction().commit();
            entityManager.close();
        } else {
            throw new IllegalArgumentException("O id digitado não corresponde a nenhum dos hóspedes");
        }


    }
}
