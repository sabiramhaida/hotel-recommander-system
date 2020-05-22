package Dao;

import DatabasePackage.HibernateOGMUtil;
import Model.Hotel;
import org.bson.types.ObjectId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hotel_Dao {

    private EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    public void insertUser(String username, String email, String fullName, String password) throws ClassNotFoundException {
        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Hotel hotel = new Hotel();

        entityManager.persist(hotel);

        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);
    }

    public Hotel getHotel(ObjectId id_hotel) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Hotel as h where h._id = :id";
        List<Hotel> hotels = entityManager.createQuery(query, Hotel.class)
                .setParameter("id", id_hotel)
                .getResultList();
        if (hotels.size() == 0) return null;
        else return hotels.iterator().next();
    }

    public List<Hotel> getHotelsByName(List<String> names) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        //List<Hotel> hotels = new ArrayList<Hotel>();

        String query = "FROM Hotel as h where h.name in :listOfNames";
        List<Hotel> hotels = entityManager.createQuery( query , Hotel.class ).setParameter("listOfNames",names).getResultList();
        Collections.sort(hotels, new SortHotel(names));
        if (hotels.size() == 0) return null;
        else return hotels;
    }

    public List<Hotel> getHotelsByCountry(String country) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Hotel as h where h.Country = :country";
        List<Hotel> hotels = entityManager.createQuery(query, Hotel.class)
                .setParameter("country", country)
                .getResultList();
        if (hotels.size() == 0) return null;
        else return hotels;
    }

    public List<Hotel> getHotelsByPrice(Double min, Double max) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Hotel as h where h.price BETWEEN :min and :max";
        List<Hotel> hotels = entityManager.createQuery(query, Hotel.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
        if (hotels.size() == 0) return null;
        else return hotels;
    }

    public List<Hotel> getHotelsByClass(Double min, Double max) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Hotel as h where h.Hotel_class BETWEEN :min and :max";
        List<Hotel> hotels = entityManager.createQuery(query, Hotel.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
        if (hotels.size() == 0) return null;
        else return hotels;
    }

    public List<Hotel> getHotels() throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Hotel as h";
        List<Hotel> hotels = entityManager.createQuery(query, Hotel.class)
                .getResultList();
        if (hotels.size() == 0) return null;
        else return hotels;
    }
}

class SortHotel implements Comparator<Hotel> {
    private List<String> names;

    SortHotel(List<String> names) {
        this.names = names;
    }

    public int compare(Hotel a, Hotel b) {
        if (names.indexOf(a.getName()) < names.indexOf(b.getName())) {
            System.out.println(names.indexOf(a.getName()));
            return 1;
        } else {
            return -1;
        }
    }
}

