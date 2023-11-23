package br.com.sistema.DAO;

import br.com.sistema.model.Bedroom;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

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

    public Bedroom getBedroomPerId(Long id) {
        String jpql = "SELECT b FROM Bedroom b WHERE b.id = :id";
        return entityManager
                .createQuery(jpql, Bedroom.class)
                .setParameter("id", id)
                .getSingleResult();


    }

    public BigDecimal getDailyRatePerId(Long id) {
        String jpql = "SELECT b.dailyRate FROM Bedroom b WHERE b.id = :id";
        return entityManager
                .createQuery(jpql, BigDecimal.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    public List<Bedroom> showAllBedrooms() {
        String jpql = "SELECT b FROM Bedroom b";
        return entityManager.createQuery(jpql, Bedroom.class).getResultList();
    }
}
