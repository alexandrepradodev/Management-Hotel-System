package br.com.sistema.service;

import br.com.sistema.BusinessRuleException;
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
    private LocalDate now = LocalDate.now();



    public void makeReservation() {

        try {

            EntityManager entityManager = JPAUtil.getEntityManager();
            GuestDAO guestDAO = new GuestDAO(entityManager);
            ReservationDAO reservationDAO = new ReservationDAO(entityManager);
            BedroomDAO bedroomDAO = new BedroomDAO(entityManager);


            List<Guest> guests = guestDAO.getAllGuests();

            for (Guest guest : guests) {
                System.out.println(guest.stringBuilder());
                System.out.println();
            }

            System.out.print("Digite o Id do cliente que deseja fazer a reserva: ");
            Long guestId = scanner.nextLong();
            if (!guestDAO.getAllIds().contains(guestId)) {
                throw new BusinessRuleException("O id digitado não corresponde a nenhum dos hóspedes");
            }

            System.out.print("Digite a quantidade de adultos: ");
            int adults = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite a quantidade de crianças: ");
            int children = scanner.nextInt();
            scanner.nextLine();

            int people = adults + children;
            int contCapacity = 0;

            System.out.printf("\nQuarto disponível para %d ou mais pessoas\n", people);

            for (Bedroom bedroom : bedroomDAO.showBedroomPerCapacity(people)) {

                System.out.println("\n" + bedroom.stringBuilder());

                if (bedroom.getCapacity() >= people) {
                    contCapacity ++;
                }
            }
            if (contCapacity == 0) {
                throw new BusinessRuleException("Desculpe, mas não temos quartos " +
                        "disponíveis para essa quantidade de pessoas");
            }


            System.out.print("\nNúmero do quarto que deseja fazer a reserva: ");
            Long bedroomNumber = scanner.nextLong();

            if (!bedroomDAO.getAllids().contains(bedroomNumber)) {
                throw new BusinessRuleException("Esse número de quarto não existe");

            } else if (!bedroomDAO.showBedroomPerCapacity(people).contains(bedroomNumber)) {
                throw new BusinessRuleException("Esse quarto não comporta o número de pessoas da sua reserva");
            }

            scanner.nextLine();
            System.out.print("Data do check-in (DD/MM/AAAA): ");
            LocalDate checkIn = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);
            if (checkIn.isBefore(now)) {
                throw new BusinessRuleException("A data de check-in precisa ser a de hoje ou depois");
            }

            System.out.print("Data do check-out (DD/MM/AAAA): ");
            LocalDate checkOut = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);
            if (checkOut.isBefore(checkIn)) {
                throw new BusinessRuleException("A data de check-out precisa ser a" +
                        " data do check-in ou uma data posterior");
            }

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
        } catch (RuntimeException e) {
            throw new BusinessRuleException(e.getMessage());

        }
    }

    public static void showAllReservations() {

        EntityManager entityManager = JPAUtil.getEntityManager();
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);
        List<Reservation> reservations = reservationDAO.getAllReservation();

        for (Reservation reservation : reservations) {
            System.out.println(reservation.stringBuilder());
            System.out.println();
        }
    }
    public static void cancelReservation() {

        EntityManager entityManager = JPAUtil.getEntityManager();
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);

        List<Reservation> reservationList = reservationDAO.getAllReservation();

        for (Reservation reservation : reservationList) {
            System.out.print(reservation.stringBuilder2());
            System.out.println("\n");
        }

        System.out.print("Digite o id da reserva que deseja cancelar: ");
        Long reservationId = scanner.nextLong();

        entityManager.getTransaction().begin();
        reservationDAO.cancelReservation(reservationId);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

}
