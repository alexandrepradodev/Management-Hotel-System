package br.com.sistema.DAO;

import br.com.sistema.model.Bedroom;
import br.com.sistema.model.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Bedroom> criteriaQuery = criteriaBuilder.createQuery(Bedroom.class);
        Root<Bedroom> bedroomRoot = criteriaQuery.from(Bedroom.class);
        criteriaQuery.select(bedroomRoot);

        Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        Root<Reservation> reservationRoot = subquery.from(Reservation.class);
        subquery.select(reservationRoot.get("bedroom").get("bedroomNumber"));

        Predicate checkOutPredicate = criteriaBuilder.greaterThan(reservationRoot.get("checkOut"), checkIn);
        Predicate checkInPredicate = criteriaBuilder.lessThan(reservationRoot.get("checkIn"), checkOut);
        Predicate subqueryPredicate = criteriaBuilder.and(checkOutPredicate, checkInPredicate);
        subquery.where(subqueryPredicate);

        Expression<Long> bedroomNumberExpression = bedroomRoot.get("bedroomNumber");
        Predicate notInPredicate = criteriaBuilder.not(bedroomNumberExpression.in(subquery));

        criteriaQuery.where(notInPredicate);

        TypedQuery<Bedroom> query = entityManager.createQuery(criteriaQuery);
        List<Bedroom> bedroomList = query.getResultList();
        return bedroomList;



    }


}
