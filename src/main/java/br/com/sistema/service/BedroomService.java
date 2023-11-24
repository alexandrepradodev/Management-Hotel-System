package br.com.sistema.service;

import br.com.sistema.DAO.BedroomDAO;
import br.com.sistema.DAO.TierDAO;
import br.com.sistema.model.Bedroom;
import br.com.sistema.model.Tier;
import br.com.sistema.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BedroomService {

    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void newBedroom() {
        System.out.print("\nCapacidade total do quarto: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Valor da diária: ");
        BigDecimal dailyRate = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("Nível do quarto: ");
        String tierName = scanner.nextLine();
        Tier tier = new Tier(tierName);

        EntityManager entityManager = JPAUtil.getEntityManager();
        BedroomDAO bedroomDAO = new BedroomDAO(entityManager);
        Bedroom bedroom = new Bedroom(capacity, dailyRate, tier);
        TierDAO tierDAO = new TierDAO(entityManager);

        entityManager.getTransaction().begin();
        tierDAO.save(tier);
        bedroomDAO.save(bedroom);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
