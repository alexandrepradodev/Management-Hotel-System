package br.com.sistema.util;

import br.com.sistema.DAO.BedroomDAO;
import br.com.sistema.DAO.GuestDAO;
import br.com.sistema.DAO.ReservationDAO;
import br.com.sistema.model.Bedroom;
import br.com.sistema.model.Reservation;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class HotelStats {

    private EntityManager entityManager;

    public HotelStats() {
    }

    public HotelStats(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BigDecimal sumOfStaysValue(){

        String jpql = "SELECT SUM(r.stayValue) FROM Reservation r";
        return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public int stayQuantity() {

        ReservationDAO reservationDAO = new ReservationDAO(entityManager);
        return reservationDAO.getAllReservation().size();
    }
    public int guestQuantity() {
        GuestDAO guestDAO = new GuestDAO(entityManager);
        return guestDAO.getAllIds().size();
    }

    public int bedroomQuantity() {
        BedroomDAO bedroomDAO = new BedroomDAO(entityManager);
        return bedroomDAO.getAllids().size();
    }
    public Double stayAVG() {
        String jpql = "SELECT AVG(r.stayValue) FROM Reservation r";
        return entityManager.createQuery(jpql, Double.class)
                .getSingleResult();
    }
    public BigDecimal moreExpensiveStay() {
        String jpql = "SELECT MAX(r.stayValue) FROM Reservation r";
        return entityManager.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }
    public BigDecimal cheapestStay() {
        String jpql = "SELECT MIN(r.stayValue) FROM Reservation r";
        return entityManager.createQuery(jpql, BigDecimal.class)
                .getSingleResult();

    }




    public StringBuilder stringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nSoma total das estadias: R$ ").append(sumOfStaysValue());
        stringBuilder.append("\nNúmero de reservas: ").append(stayQuantity());
        stringBuilder.append("\nNúmero de hóspedes cadastrados: ").append(guestQuantity());
        stringBuilder.append("\nNúmero de quartos cadastrados: ").append(bedroomQuantity());
        stringBuilder.append("\nMédia de preço de reserva: R$ ").append(String.format("%.2f", stayAVG()));
        stringBuilder.append("\nReserva mais cara: R$").append(String.format("%.2f", moreExpensiveStay()));
        stringBuilder.append("\nReserva mais barata: R$").append(String.format("%.2f", cheapestStay()));
        return stringBuilder;
    }
}
