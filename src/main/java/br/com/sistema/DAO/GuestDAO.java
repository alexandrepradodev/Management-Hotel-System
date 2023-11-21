package br.com.sistema.DAO;

import javax.persistence.EntityManager;

public class GuestDAO {
    private EntityManager entityManager;

    public GuestDAO() {
    }

    public GuestDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
