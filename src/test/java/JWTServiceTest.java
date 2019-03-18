import com.auth0.jwt.interfaces.DecodedJWT;
import domain.models.Call;
import domain.models.Employee;
import domain.services.JWTService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JWTServiceTest {
    private JWTService service;

    @Before
    public void construct() {
        service = new JWTService();
    }

    @Test
    public void createJWT () {
        String token = service.createJWT(null);
        System.out.println(token);
    }

    @Test
    public void verifyJWT () {
        DecodedJWT token = service.verifyJWT(service.createJWT(null));

        System.out.println(token.getHeader());
        System.out.println(token.getPayload());
        System.out.println(token.getSignature());
        System.out.println(token.getToken());

        assertNotNull(token);
    }

    @Test
    public void verifyClaimsJWT () {
//        Call c = new Call();
//        c.setDuration(10);
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("call", c);

        DecodedJWT token = service.verifyJWT("eyJlbXBsb3llZSI6eyJjYWxscyI6W10sImNvbXBhbnkiOm51bGwsImNyZWF0ZWQiOjE1NTI5MTQ3MTQ1NDIsImVtYWlsIjoidGVzdDFAdGVzdC5jb20iLCJmaXJzdG5hbWUiOiJzYW5kZXIiLCJpZCI6NywibGFzdG5hbWUiOiJ2YW4gaG9vZmYiLCJwYXNzd29yZCI6InBtV2tXU0JDTDUxQmZraG43OXhQdUtCS0h6Ly9INkIrbVk2RzkvZWlldU09IiwidXBkYXRlZCI6MTU1MjkxNDcxNDU0Mn0sImFsZyI6IkhTMjU2IiwidHlwIjoiSldUIn0.eyJpc3MiOiJjYWxsY2VudGVyIn0.3ZbKZvHL52-eRS8HoNIjEAx_Y4sXMiy_fjUqYmGjwpU");
        assertNotNull(token);

        boolean claimCreated = !token.getHeaderClaim("employee").isNull();
        assertTrue(claimCreated);

        if (claimCreated) {
            Employee result = token.getHeaderClaim("employee").as(Employee.class);
            assertEquals("test1@test.com", result.getEmail());
        }

    }

    @Test
    public void decodeJWT () {
        DecodedJWT token = service.decodeJWT(service.createJWT(null));

        System.out.println(token.getHeader());
        System.out.println(token.getPayload());
        System.out.println(token.getSignature());
        System.out.println(token.getToken());

        assertNotNull(token);
    }


}
