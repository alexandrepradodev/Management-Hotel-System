package br.com.sistema.util;

import br.com.sistema.DAO.ReservationDAO;
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



    public StringBuilder stringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nSoma total das estadias: R$ ").append(sumOfStaysValue());
        stringBuilder.append("\nNúmero de reservas: ").append(stayQuantity());
        return stringBuilder;
    }
}
