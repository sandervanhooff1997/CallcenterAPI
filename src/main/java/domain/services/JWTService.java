package domain.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import domain.models.Employee;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Local
@Stateless

public class JWTService {
    private static final String secret = "secret";
    private static final String issuer = "callcenter";
    private static final int EXPIRE_MINUTES = 20;
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
                .withClaim("id", e.getId())
                .withClaim("email", e.getEmail())
                .withClaim("password", e.getPassword())
                .withExpiresAt(getExpireDate(EXPIRE_MINUTES))
                .sign(algorithm);
    }

    public void verifyJWT(String token) throws JWTVerificationException {
        DecodedJWT decoded = verifier.verify(token);

        if (decoded.getClaim("id").isNull() || decoded.getClaim("email").isNull() || decoded.getClaim("password").isNull())
            throw new JWTVerificationException("Invalid claim data");
    }

    private Date getExpireDate(int minutesToAdd) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, minutesToAdd);
        return c.getTime();
    }
}
