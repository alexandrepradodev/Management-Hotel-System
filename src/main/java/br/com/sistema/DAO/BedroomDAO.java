package br.com.sistema.DAO;

import br.com.sistema.model.Bedroom;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public int getBedroomCapacity(Long id) {
        String jpql = "SELECT b.capacity FROM Bedroom b WHERE b.id = :id";
        return entityManager
                .createQuery(jpql, int.class).setParameter("id", id)
                .getSingleResult();
    }
    public List<Bedroom> getBedroomPerCapacity(int people) {
        String jpql = "SELECT b FROM Bedroom b WHERE b.capacity >= :people";
        return entityManager.createQuery(jpql, Bedroom.class).setParameter("people", people).getResultList();

    }

    public boolean checkTheBedroomCapacity(Long bedroomNumber, int people) {


            String jpql = "SELECT b.capacity FROM Bedroom b WHERE b.bedroomNumber = :bedroomNumber";

            int bedroomCapacity = entityManager.createQuery(jpql, int.class)
                    .setParameter("bedroomNumber", bedroomNumber)
                    .getSingleResult();

        return bedroomCapacity >= people;
    }

    public List<Long> getAllids() {
        String jpql = "SELECT b.bedroomNumber FROM Bedroom b";
        return entityManager.createQuery(jpql, Long.class).getResultList();
    }




}
