package br.com.sistema.DAO;

import br.com.sistema.model.Tier;

import javax.persistence.EntityManager;

public class TierDAO {

    private EntityManager entityManager;

    public TierDAO() {
    }

    public TierDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Tier tier) {
        entityManager.persist(tier);
    }
}
