package br.com.sistema.service;

import br.com.sistema.DAO.BedroomDAO;
import br.com.sistema.DAO.TierDAO;
import br.com.sistema.exceptions.BusinessRuleException;
import br.com.sistema.model.Bedroom;
import br.com.sistema.model.Tier;
import br.com.sistema.util.JPAUtil;
import br.com.sistema.util.StayCalculator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BedroomService {

    private static final Scanner scanner = new Scanner(System.in);

    public static void newBedroom(){

        try {
            EntityManager entityManager = JPAUtil.getEntityManager();
            System.out.print("\nCapacidade total do quarto: ");
            int capacity = scanner.nextInt();
            if (capacity <= 0) {
                throw new IllegalArgumentException("A capacidade total do quarto precisa ser maior do que zero.");
            }
            scanner.nextLine();

            String tierName = " ";


            System.out.print("Quarto Padrão ou Master? (P/M): ");
            char tierChar = scanner.nextLine().charAt(0);

            if (Character.toUpperCase(tierChar) == 'P') {
                tierName = "padrao";

            } else if (Character.toUpperCase(tierChar) == 'M') {
                tierName = "master";
            }

            BigDecimal dailyRate = StayCalculator.calculateStay(capacity, tierChar);

            Tier tier = new Tier(tierName);



            BedroomDAO bedroomDAO = new BedroomDAO(entityManager);
            Bedroom bedroom = new Bedroom(capacity, dailyRate, tier);
            TierDAO tierDAO = new TierDAO(entityManager);

            entityManager.getTransaction().begin();
            tierDAO.save(tier);
            bedroomDAO.save(bedroom);
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (RuntimeException e) {
            throw new BusinessRuleException(e.getMessage());


        }
    }

    public static void showAllBedrooms() {

        EntityManager entityManager = JPAUtil.getEntityManager();
        BedroomDAO bedroomDAO = new BedroomDAO(entityManager);
        List<Bedroom> bedrooms = bedroomDAO.showAllBedrooms();

        for (Bedroom bedroom : bedrooms) {
            System.out.println(bedroom.stringBuilder());
            System.out.println();
        }
    }

}
