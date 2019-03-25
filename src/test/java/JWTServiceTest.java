import domain.models.Employee;
import domain.services.JWTService;
import org.junit.Before;
import org.junit.Test;

public class JWTServiceTest {
    private JWTService service;

    @Before
    public void construct() {
        service = new JWTService();
    }

    @Test
    public void createJWTEmployee () {
        Employee e = new Employee();
        e.setEmail("test1@test.com");
        e.setPassword("pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=");

        String jwt = service.createJWT(e);

        System.out.println(jwt);
    }

    @Test
    public void verifyClaimsJWT () {
        service.verifyJWT("eyJlbXBsb3llZSI6eyJjYWxscyI6W10sImNvbXBhbnkiOm51bGwsImNyZWF0ZWQiOjE1NTI5MTQ3MTQ1NDIsImVtYWlsIjoidGVzdDFAdGVzdC5jb20iLCJmaXJzdG5hbWUiOiJzYW5kZXIiLCJpZCI6NywibGFzdG5hbWUiOiJ2YW4gaG9vZmYiLCJwYXNzd29yZCI6InBtV2tXU0JDTDUxQmZraG43OXhQdUtCS0h6Ly9INkIrbVk2RzkvZWlldU09IiwidXBkYXRlZCI6MTU1MjkxNDcxNDU0Mn0sImFsZyI6IkhTMjU2IiwidHlwIjoiSldUIn0.eyJpc3MiOiJjYWxsY2VudGVyIn0.3ZbKZvHL52-eRS8HoNIjEAx_Y4sXMiy_fjUqYmGjwpU");
    }
}
