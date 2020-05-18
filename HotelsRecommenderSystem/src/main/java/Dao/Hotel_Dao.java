package Dao;

import DatabasePackage.HibernateOGMUtil;
import Model.Hotel;
import org.bson.types.ObjectId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Hotel_Dao {

    private EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    public void insertUser(String username, String email , String fullName, String password ) throws ClassNotFoundException {
        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Hotel hotel = new Hotel();

        entityManager.persist( hotel );

        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);
    }

    public Hotel getHotel(ObjectId id_hotel) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Hotel as h where h._id = :id";
        List<Hotel> hotels = entityManager.createQuery( query , Hotel.class )
                .setParameter("id",id_hotel)
                .getResultList();
        if(hotels.size() == 0) return null;
        else return hotels.iterator().next();
    }

    public List<Hotel> getHotelsByName(List<String> names) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Hotel as h where h.name in :listOfNames";
        List<Hotel> hotels = entityManager.createQuery( query , Hotel.class )
                .setParameter("listOfNames",names)
                .getResultList();
        if(hotels.size() == 0) return null;
        else return hotels;
    }

    public List<Hotel> getHotels() throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM Hotel as h";
        List<Hotel> hotels = entityManager.createQuery( query , Hotel.class )
                .getResultList();
        if(hotels.size() == 0) return null;
        else return hotels;
    }
}
