import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import domain.models.Call;
import domain.services.AuthService;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class AuthServiceTest {
    private AuthService service;

    @Before
    public void construct() {
        service = new AuthService();
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
        Call c = new Call();
        c.setDuration(10);

        Map<String, Object> claims = new HashMap<>();
        claims.put("call", c);

        DecodedJWT token = service.verifyJWT(service.createJWT(claims));
        assertNotNull(token);

        boolean claimCreated = !token.getHeaderClaim("call").isNull();
        assertTrue(claimCreated);

        if (claimCreated) {
            Call result = token.getHeaderClaim("call").as(Call.class);
            assertEquals(result.getDuration(), 10);
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
