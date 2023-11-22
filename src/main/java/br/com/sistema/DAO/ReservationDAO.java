package br.com.sistema.DAO;

import br.com.sistema.model.Reservation;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class ReservationDAO {

    private EntityManager entityManager;

    public ReservationDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Reservation reservation) {
        entityManager.persist(reservation);
    }


}
