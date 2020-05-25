

package Dao;

import DatabasePackage.HibernateOGMUtil;
import Model.User;
import Model.UserActivity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Timestamp;
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
        newActivity.setUsername(username);
        newActivity.setHotel_name(hotelname);
        newActivity.setDate( 45555);
        newActivity.setActivity(activity);

        entityManager.persist( newActivity );
        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);
    }

    public UserActivity getUserActivityCount(String username) throws ClassNotFoundException {
        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM UserActivity as u where u.username = :username";
        List<UserActivity> activities = entityManager.createQuery( query , UserActivity.class )
                .setParameter("username",username)
                .getResultList();
        if(activities.size() == 0) return null;
        else return activities.iterator().next();
    }

    public User getUserfromActivity( String username) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM User as u where u.username = :username";
        List<User> users = entityManager.createQuery( query , User.class )
                .setParameter("username",username)
                .getResultList();
        if(users.size() == 0) return null;
        else return users.iterator().next();
    }



}
