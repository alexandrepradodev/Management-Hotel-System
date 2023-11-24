package br.com.sistema.DAO;

import br.com.sistema.model.Reservation;

import javax.persistence.EntityManager;
import java.util.List;

public class ReservationDAO {

    private EntityManager entityManager;

    public ReservationDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Reservation reservation) {
        entityManager.persist(reservation);
    }

    public List<Reservation> getAllReservation() {
        String jpql = "SELECT r FROM Reservation r";
        return entityManager
                .createQuery(jpql, Reservation.class)
                .getResultList();
    }
    public void cancelReservation(Long id) {

        String jpql = "SELECT r FROM Reservation r";

        List<Reservation> reservationListForRemoval = entityManager
                .createQuery(jpql, Reservation.class)
                .getResultList();

        for (Reservation reservationForRemoval : reservationListForRemoval) {
            System.out.println(reservationForRemoval.stringBuilder());
            System.out.println();

        }

        Reservation reservationToRemove = entityManager.find(Reservation.class,id);
        entityManager.remove(reservationToRemove);
    }


}
