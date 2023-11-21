package br.com.sistema.DAO;

import br.com.sistema.model.Guest;

import javax.persistence.EntityManager;

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
}
