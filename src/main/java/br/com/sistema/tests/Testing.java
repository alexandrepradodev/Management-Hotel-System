package br.com.sistema.tests;

import br.com.sistema.util.HotelStats;
import br.com.sistema.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.Locale;

public class Testing {



    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        EntityManager entityManager = JPAUtil.getEntityManager();

        HotelStats hotelStats = new HotelStats(entityManager);

        StringBuilder stringBuilder = hotelStats.stringBuilder();

        System.out.println(stringBuilder);





    }
}
