package br.com.sistema.service;

import br.com.sistema.BusinessRuleException;
import br.com.sistema.DAO.BedroomDAO;
import br.com.sistema.DAO.TierDAO;
import br.com.sistema.model.Bedroom;
import br.com.sistema.model.Tier;
import br.com.sistema.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BedroomService {

    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static EntityManager entityManager = JPAUtil.getEntityManager();

    public static void newBedroom(){

        try {


            System.out.print("\nCapacidade total do quarto: ");
            int capacity = scanner.nextInt();
            if (capacity <= 0) {
                throw new IllegalArgumentException("A capacidade total do quarto precisa ser maior do que zero.");
            }
            scanner.nextLine();

            System.out.print("Valor da diária: ");
            BigDecimal dailyRate = scanner.nextBigDecimal();
            if (dailyRate.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O valor da diária precisa ser maior que zero reais");
            }
            scanner.nextLine();

            System.out.print("Nível do quarto: ");
            String tierName = scanner.nextLine();
            Tier tier = new Tier(tierName);
            if (tierName.isEmpty()) {
                throw new IllegalArgumentException("O nível do quarto não pode ficar vazio");
            }

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
        BedroomDAO bedroomDAO = new BedroomDAO(entityManager);
        List<Bedroom> bedrooms = bedroomDAO.showAllBedrooms();

        for (Bedroom bedroom : bedrooms) {
            System.out.println(bedroom.stringBuilder());
            System.out.println();
        }
    }
}
