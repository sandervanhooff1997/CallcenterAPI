package domain.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Map;

@Local
@Stateless
public class JWTService {
    private static final String secret = "secret";
    private static final String issuer = "callcenter";
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);
    private JWTVerifier verifier;

    public JWTService() {
        verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }

    public String createJWT(Map<String, Object> claims) {
        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withHeader(claims)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public DecodedJWT verifyJWT(String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public DecodedJWT decodeJWT(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
