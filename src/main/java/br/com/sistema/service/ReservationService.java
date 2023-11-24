package br.com.sistema.service;

import br.com.sistema.DAO.BedroomDAO;
import br.com.sistema.DAO.GuestDAO;
import br.com.sistema.DAO.ReservationDAO;
import br.com.sistema.model.Bedroom;
import br.com.sistema.model.Guest;
import br.com.sistema.model.Reservation;
import br.com.sistema.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public void makeReservation() {

        System.out.print("\nNúmero do quarto que deseja fazer a reserva: ");
        Long bedroomNumber = scanner.nextLong();

        System.out.print("Digite o Id do cliente que deseja fazer a reserva: ");
        Long guestId = scanner.nextLong();

        System.out.print("Digite a quantidade de adultos: ");
        int adults = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a quantidade de crianças: ");
        int children = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Data do check-in (DD/MM/AAAA): ");
        LocalDate checkIn = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);

        System.out.print("Data do check-out (DD/MM/AAAA): ");
        LocalDate checkOut = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);

        EntityManager entityManager = JPAUtil.getEntityManager();
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);
        BedroomDAO bedroomDAO = new BedroomDAO(entityManager);
        GuestDAO guestDAO = new GuestDAO(entityManager);

        Reservation reservation1 = new Reservation();
        BigDecimal dailyRate = bedroomDAO.getDailyRatePerId(bedroomNumber);
        Bedroom bedroom = bedroomDAO.getBedroomPerId(bedroomNumber);
        Guest guest = guestDAO.getGuestPerId(guestId);
        int calculateDays = reservation1.calculateDays(checkIn, checkOut);
        BigDecimal stayValue = reservation1.calculateStay(dailyRate, calculateDays, children, adults);

        Reservation reservation = new Reservation(bedroom, guest, children, adults, checkIn, checkOut, stayValue);

        entityManager.getTransaction().begin();
        reservationDAO.save(reservation);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void showAllReservations() {
        ReservationDAO reservationDAO = new ReservationDAO(JPAUtil.getEntityManager());
        List<Reservation> reservations = reservationDAO.getAllReservation();

        for (Reservation reservation : reservations) {
            System.out.println(reservation.stringBuilder());
            System.out.println();
        }
    }
}
