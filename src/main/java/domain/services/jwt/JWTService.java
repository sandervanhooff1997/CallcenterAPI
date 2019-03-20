package domain.services.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import domain.models.Employee;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.Date;

@Local
@Stateless
public class JWTService implements IJWTService {
    private static final String secret = "secret";
    private static final String issuer = "callcenter";
    private static final int DAYS_TO_ADD = 1;
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);
    private JWTVerifier verifier;

    public JWTService() {
        verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }

    public String createJWT(Employee e) throws JWTCreationException {
        return JWT.create()
                .withIssuer(issuer)
                .withClaim("email", e.getEmail())
                .withClaim("firstname", e.getFirstname())
                .withClaim("lastname", e.getLastname())
                .withExpiresAt(getExpireDate(DAYS_TO_ADD))
                .sign(algorithm);
    }

    public void verifyJWT(String token) throws JWTVerificationException {
        DecodedJWT decoded = verifier.verify(token);

        if (decoded.getClaim("email").isNull() || decoded.getClaim("firstname").isNull() || decoded.getClaim("lastname").isNull())
            throw new JWTVerificationException("Invalid claim data");

        // expired
        if (decoded.getExpiresAt().compareTo(new Date()) < 0)
            throw new JWTVerificationException("Token expired");
    }

    public Date getExpireDate(int daysToAdd) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, daysToAdd);
        return c.getTime();
    }
}
