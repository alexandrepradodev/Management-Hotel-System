package br.com.sistema.service;

import br.com.sistema.util.HotelStats;
import br.com.sistema.util.JPAUtil;

import javax.persistence.EntityManager;

public class HotelStatsService {

    public static void showHotelStats() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        HotelStats hotelStats = new HotelStats(entityManager);
        System.out.println(hotelStats.stringBuilder());
    }
}
