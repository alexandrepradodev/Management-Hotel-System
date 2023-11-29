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

        if (guest != null) {

            entityManager.remove(guest);
        }
    }
    public List<Long> getAllIds() {

        String jpql = "SELECT g.id FROM Guest g";
        List<Long> ids = entityManager.createQuery(jpql, Long.class).getResultList();
        return ids;

    }
    public List<String> checkIfCPFExists() {
        String jpql = "SELECT g.cpf FROM Guest g";
        return entityManager.createQuery(jpql, String.class).getResultList();
    }
    public List<String> checkIfEmailExists() {
        String jpql = "SELECT g.email FROM Guest g";
        return entityManager.createQuery(jpql, String.class).getResultList();
    }
}
