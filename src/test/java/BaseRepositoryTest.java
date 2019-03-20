import domain.repositories.call.CallRepository;
import org.junit.BeforeClass;

import javax.naming.InitialContext;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

public class BaseRepositoryTest {
    private static InitialContext initialContext;
    protected static CallRepository repo;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        Properties p = new Properties();

        // Voeg nieuwe persistency informatie toe.
        p.put("myDatabase", "new://Resource?type=DataSource");
        p.put("myDatabase.JdbcDriver", "com.mysql.jdbc.Driver");
        p.put("myDatabase.JdbcUrl", "mysql://localhost:8889/callcenter");
        p.put("myDatabase.JdbcUser", "root");
        p.put("myDatabase.JdbcPass", "root");

        initialContext = new InitialContext(p);

        // Zoek de RegistrationManager op d.m.v. de interface
        repo = (CallRepository) initialContext.lookup("CallRepository");

        assertNotNull(repo);

    }

//    protected static EntityManagerFactory emf;
//    protected EntityManager em;
//
//    @BeforeClass
//    public static void createEntityManagerFactory() {
//        emf = Persistence.createEntityManagerFactory("callcenterTestPU");
//    }
//
//    @AfterClass
//    public static void closeEntityManagerFactory() {
//        emf.close();
//    }
//
//    @Before
//    public void beginTransaction() {
//        em = emf.createEntityManager();
//        em.getTransaction().begin();
//    }
//
//    @After
//    public void rollbackTransaction() {
//        if (em.getTransaction().isActive()) {
//            em.getTransaction().rollback();
//        }
//
//        if (em.isOpen()) {
//            em.close();
//        }
//    }
}
