

package Dao;

import DatabasePackage.HibernateOGMUtil;
import Model.User;
import Model.UserActivity;
import org.bson.types.ObjectId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class UserActivity_Dao {

    private EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    public void insertUserActivity(String username, String hotelname , String activity, Date activityDate ) throws ClassNotFoundException {
        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        UserActivity newActivity = new UserActivity() ;
        newActivity.setUserName(username);
        newActivity.setHotelName(hotelname);
        newActivity.setDate(activityDate);
        newActivity.setActivity(activity);

        entityManager.persist( newActivity );
        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);
    }

    public User getUserfromActivity( String username) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM User as u where u.userName = :username";
        List<User> users = entityManager.createQuery( query , User.class )
                .setParameter("username",username)
                .getResultList();
        if(users.size() == 0) return null;
        else return users.iterator().next();
    }



}
