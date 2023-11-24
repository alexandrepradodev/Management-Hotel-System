package br.com.sistema.DAO;

import br.com.sistema.model.Bedroom;
import br.com.sistema.model.Guest;
import br.com.sistema.model.Reservation;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
    private EntityManager entityManager;

    public GuestDAO() {
    }

    public GuestDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Guest guest) {
        entityManager.persist(guest);
    }

    public Guest getGuestPerId(Long id) {
        String jpql = "SELECT g FROM Guest g WHERE id = :id";
        return entityManager
                .createQuery(jpql, Guest.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    public List<Guest> getAllGuests() {
        String jpql = "SELECT g FROM Guest g";

        return entityManager
                .createQuery(jpql, Guest.class)
                .getResultList();
    }
    public void removeGuestPerId(Long id) {
        Guest guest = entityManager.find(Guest.class, id);
        String jpql = "SELECT r.id FROM Reservation r WHERE r.guest.id = :id";
        List<Long> reservationIds = entityManager
                .createQuery(jpql, Long.class)
                .setParameter("id", id)
                .getResultList();

        List<Long> idListForremoval = new ArrayList<>();
        for (Long reservationId : reservationIds) {
            entityManager.find(Reservation.class, reservationId);
            idListForremoval.add(reservationId);
        }

        String jpqlAllReservations = "SELECT r FROM Reservation r";
        List<Reservation> allReservations = entityManager
                .createQuery(jpqlAllReservations, Reservation.class)
                .getResultList();

        if (guest != null) {

            entityManager.remove(guest);

                for (Long reservationId : idListForremoval) {
                    Reservation reservationForRemoval = entityManager.find(Reservation.class, reservationId);
                    entityManager.remove(reservationForRemoval);
                }

            System.out.println("\nExclusão realizada com sucesso!");
        }


    }
    public List<Long> getAllIds() {

        String jpql = "SELECT g.id FROM Guest g";
        List<Long> ids = entityManager.createQuery(jpql, Long.class).getResultList();
        return ids;

    }
}
