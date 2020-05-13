package DatabasePackage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateOGMUtil {

    @BeforeClass
    public static EntityManagerFactory setUpEntityManagerFactory() throws ClassNotFoundException {
        return Persistence.createEntityManagerFactory( "test" );
    }

    @AfterClass
    public static void closeEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        entityManagerFactory.close();
    }
}
