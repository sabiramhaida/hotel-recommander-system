

package Dao;

        import DatabasePackage.HibernateOGMUtil;
        import Model.User;
        import org.bson.types.ObjectId;

        import javax.persistence.EntityManager;
        import javax.persistence.EntityManagerFactory;
        import javax.persistence.Id;
        import java.util.List;

public class User_DAO {

    private EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    public void insertUser(String username, String country , String city, String fullName, String password ) throws ClassNotFoundException {
        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User() ;
        user.setCity(city);
        user.setUserName(username);
        user.setFullName(fullName);
        user.setCountry(country);
        user.setPassword(password);

        entityManager.persist( user );
        entityManager.getTransaction().commit();

        HibernateOGMUtil.closeEntityManagerFactory(entityManagerFactory);
    }

    public User getUser(ObjectId id) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM User as u where u._id = :id";
        List<User> users = entityManager.createQuery( query , User.class )
                .setParameter("id",id)
                .getResultList();
        if(users.size() == 0) return null;
        else return users.iterator().next();
    }

    public List<User> getUser(String usernam, String passw) throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM User as u where u.userName = :usernam and u.password = :passw";
        List<User> User = entityManager.createQuery( query , User.class )
                .setParameter("usernam",usernam)
                .setParameter("passw",passw)
                .getResultList();
        if(User.size() == 0) return null;
        else return User;
    }

    public List<User> getUsers() throws ClassNotFoundException {

        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String query = "FROM User as u";
        List<User> hotels = entityManager.createQuery( query , User.class )
                .getResultList();
        if(hotels.size() == 0) return null;
        else return hotels;
    }

    public int isRegistred(String usename) throws ClassNotFoundException {
        entityManagerFactory = HibernateOGMUtil.setUpEntityManagerFactory();

        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        String query = "FROM User as u where u.userName = :usernam ";
        List<User> User = entityManager.createQuery( query , User.class )
                .setParameter("usernam",usename)
                .getResultList();
        if(User.size() == 0) return 0;
        else return 1;
    }
}
