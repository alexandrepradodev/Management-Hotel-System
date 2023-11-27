package br.com.sistema.DAO;

import br.com.sistema.model.Bedroom;
import br.com.sistema.model.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
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

    public List<Long> getAllIds() {
        String jpql = "SELECT r.id FROM Reservation r";
        return entityManager.createQuery(jpql, Long.class).getResultList();

    }

    public List<Bedroom> getBedroomsAvaliable(LocalDate checkIn, LocalDate checkOut) {
        String jpql = "SELECT b FROM Bedroom b WHERE b.bedroomNumber NOT IN " +
                "(SELECT r.bedroom.bedroomNumber FROM Reservation r "
                + "WHERE r.checkOut > :checkIn AND r.checkIn < :checkOut)";

        TypedQuery<Bedroom> query = entityManager.createQuery(jpql, Bedroom.class)
                .setParameter("checkIn", checkIn)
                .setParameter("checkOut", checkOut);

        return query.getResultList();



    }


}
