package br.com.sistema.DAO;

import br.com.sistema.model.Bedroom;

import javax.persistence.EntityManager;

public class BedroomDAO {

    private EntityManager entityManager;

    public BedroomDAO() {
    }

    public BedroomDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Bedroom bedroom) {
        entityManager.persist(bedroom);
    }
}
