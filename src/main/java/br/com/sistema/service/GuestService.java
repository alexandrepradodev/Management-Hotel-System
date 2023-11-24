package br.com.sistema.service;

import br.com.sistema.DAO.GuestDAO;
import br.com.sistema.model.Guest;
import br.com.sistema.util.AgeCalculator;
import br.com.sistema.util.JPAUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GuestService {

    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void newGuest() {
        System.out.print("\nNome do hóspede: ");
        String name = scanner.nextLine();

        System.out.print("Data de nascimento: ");
        LocalDate birthday = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);

        AgeCalculator ageCalculator = new AgeCalculator();

        int age = ageCalculator.calculateAge(birthday, LocalDate.now());

        Guest guest = new Guest(name, birthday, age);
        EntityManager entityManager = JPAUtil.getEntityManager();
        GuestDAO guestDAO = new GuestDAO(entityManager);

        entityManager.getTransaction().begin();
        guestDAO.save(guest);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
