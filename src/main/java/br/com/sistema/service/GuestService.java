package br.com.sistema.service;

import br.com.sistema.BusinessRuleException;
import br.com.sistema.DAO.GuestDAO;
import br.com.sistema.model.Guest;
import br.com.sistema.util.AgeCalculator;
import br.com.sistema.util.JPAUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class GuestService {

    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static EntityManager entityManager = JPAUtil.getEntityManager();

    public static void newGuest() {

        try {

            System.out.print("\nNome do hóspede: ");
            String name = scanner.nextLine();
            if (name.isEmpty() || name.matches(".*\\d+.*")) {
                throw new IllegalArgumentException("O nome do hóspede não pode estar vazio ou conter números");
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

            Guest guest = new Guest(name, birthday, age);
            GuestDAO guestDAO = new GuestDAO(entityManager);

            entityManager.getTransaction().begin();
            guestDAO.save(guest);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (RuntimeException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }

    public static void showAllGuests() {

        GuestDAO guestDAO = new GuestDAO(entityManager);
        List<Guest> guests = guestDAO.getAllGuests();

        for (Guest guest: guests) {
            System.out.println(guest.stringBuilder());
            System.out.println();

        }
    }
    public static void removeGuest(){

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
